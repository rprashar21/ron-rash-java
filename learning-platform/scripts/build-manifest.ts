import * as fs from 'fs';
import * as path from 'path';
import * as crypto from 'crypto';
import type { Manifest, ManifestEntry, FileType } from '../src/types/manifest';

const REPO_ROOT = path.resolve(__dirname, '../..');
const OUTPUT_PATH = path.resolve(__dirname, '../content-manifest.json');
const MAX_EXCERPT_BYTES = 10 * 1024; // 10KB cap to avoid OOM on large Java files

const TOPIC_MAP: Record<string, string> = {
  src: 'java',
  notes: 'notes',
  'Ai Engineering': 'ai-engineering',
  'data-engineering': 'data-engineering',
  'dev-ops': 'dev-ops',
  cloud: 'cloud',
  frontend: 'frontend',
  IntegrationTests: 'integration-tests',
  crypto: 'crypto',
};

const EXCLUDE_DIRS = new Set([
  '.git',
  '.idea',
  '.claude',
  'learning-platform',
  'node_modules',
]);

const EXCLUDE_FILES = new Set(['pom.xml', 'ronrashjav.iml', 'default.properties', 'README.md']);

const EXTENSION_TYPE_MAP: Record<string, FileType> = {
  '.md': 'markdown',
  '.java': 'code',
  '.py': 'code',
  '.tf': 'code',
  '.sh': 'code',
  '.sql': 'code',
  '.yml': 'code',
  '.yaml': 'code',
  '.ts': 'code',
  '.tsx': 'code',
  '.js': 'code',
  '.jsx': 'code',
  '.txt': 'text',
  '.png': 'image',
  '.jpg': 'image',
  '.jpeg': 'image',
  '.gif': 'image',
  '.svg': 'image',
};

const LANGUAGE_MAP: Record<string, string> = {
  '.java': 'java',
  '.py': 'python',
  '.tf': 'hcl',
  '.sh': 'bash',
  '.sql': 'sql',
  '.yml': 'yaml',
  '.yaml': 'yaml',
  '.ts': 'typescript',
  '.tsx': 'typescript',
  '.js': 'javascript',
  '.jsx': 'javascript',
};

function slugifySegment(segment: string): string {
  return segment
    .trim()
    .toLowerCase()
    .replace(/\s+/g, '-')
    .replace(/[^a-z0-9\-_.]/g, '')
    .replace(/-+/g, '-');
}

function titleFromFilename(filePath: string): string {
  const base = path.basename(filePath, path.extname(filePath));
  const withoutPrefix = base.replace(/^\d+[-_.]/, '');
  const spaced = withoutPrefix.replace(/[-_]/g, ' ').trim();
  return spaced.replace(/\b\w/g, (c) => c.toUpperCase()) || base;
}

function getFileType(filePath: string): { type: FileType; language?: string } {
  const ext = path.extname(filePath).toLowerCase();
  if (!ext) return { type: 'text' };
  const type = EXTENSION_TYPE_MAP[ext];
  if (!type) return { type: 'text' };
  return { type, language: LANGUAGE_MAP[ext] };
}

function generateExcerpt(filePath: string, type: FileType): string {
  if (type === 'image') return '';
  try {
    const stat = fs.statSync(filePath);
    if (stat.size > MAX_EXCERPT_BYTES * 50) return ''; // skip files > 500KB
    const fd = fs.openSync(filePath, 'r');
    const buf = Buffer.alloc(MAX_EXCERPT_BYTES);
    const bytesRead = fs.readSync(fd, buf, 0, MAX_EXCERPT_BYTES, 0);
    fs.closeSync(fd);
    const raw = buf.slice(0, bytesRead).toString('utf-8');
    const cleaned = raw
      .replace(/^#+\s+/gm, '')
      .replace(/```[\s\S]*?```/g, '')
      .replace(/\|[^\n]*\|/g, '')
      .replace(/[^\x20-\x7E\n\t]/g, '') // strip non-printable chars
      .trim();
    return cleaned.slice(0, 200).replace(/\s+/g, ' ');
  } catch {
    return '';
  }
}

function tagsFromSegments(segments: string[]): string[] {
  return segments
    .map((s) => slugifySegment(s))
    .filter((s) => s.length > 1);
}

function walkDir(
  dirPath: string,
  topic: string,
  segments: string[],
  entries: ManifestEntry[]
): void {
  let items: string[];
  try {
    items = fs.readdirSync(dirPath);
  } catch {
    return;
  }

  for (const item of items) {
    const fullPath = path.join(dirPath, item);
    const itemTrimmed = item.trim();

    let stat: fs.Stats;
    try {
      stat = fs.statSync(fullPath);
    } catch {
      continue;
    }

    if (stat.isDirectory()) {
      if (EXCLUDE_DIRS.has(itemTrimmed) || itemTrimmed.startsWith('.')) continue;
      walkDir(fullPath, topic, [...segments, item], entries);
    } else if (stat.isFile()) {
      if (EXCLUDE_FILES.has(itemTrimmed)) continue;
      if (itemTrimmed.startsWith('.')) continue;

      const ext = path.extname(item).toLowerCase();
      // Skip binary and config files we don't know how to render
      const SKIP_EXTENSIONS = new Set([
        '.iml', '.xml', '.class', '.jar', '.zip',
        '.sample', '.lock', '.map',
      ]);
      if (SKIP_EXTENSIONS.has(ext)) continue;

      const { type, language } = getFileType(fullPath);
      const repoRelativePath = path.relative(REPO_ROOT, fullPath);

      // Strip extension from the filename segment for clean URLs
      const itemWithoutExt = ext ? item.slice(0, -ext.length) : item;
      const slugSegments = [...segments, itemWithoutExt]
        .map(slugifySegment)
        .filter(Boolean);
      const slug = slugSegments.join('/');

      if (!slug) continue;

      const id = crypto
        .createHash('sha1')
        .update(repoRelativePath)
        .digest('hex')
        .slice(0, 12);

      const tags = tagsFromSegments(segments);
      const excerpt = generateExcerpt(fullPath, type);

      entries.push({
        id,
        title: titleFromFilename(item),
        topic,
        slug,
        path: repoRelativePath,
        type,
        ...(language ? { language } : {}),
        tags,
        excerpt,
      });
    }
  }
}

function main(): void {
  const entries: ManifestEntry[] = [];

  for (const [dirName, topicSlug] of Object.entries(TOPIC_MAP)) {
    const dirPath = path.join(REPO_ROOT, dirName);
    if (!fs.existsSync(dirPath)) {
      console.warn(`[manifest] Skipping missing directory: ${dirName}`);
      continue;
    }
    walkDir(dirPath, topicSlug, [], entries);
  }

  // Sort: by topic, then by slug
  entries.sort((a, b) =>
    a.topic.localeCompare(b.topic) || a.slug.localeCompare(b.slug)
  );

  const manifest: Manifest = {
    generatedAt: new Date().toISOString(),
    totalFiles: entries.length,
    entries,
  };

  fs.writeFileSync(OUTPUT_PATH, JSON.stringify(manifest, null, 2), 'utf-8');

  const topicCounts: Record<string, number> = {};
  for (const e of entries) {
    topicCounts[e.topic] = (topicCounts[e.topic] ?? 0) + 1;
  }

  console.log(`[manifest] Generated ${entries.length} entries across ${Object.keys(topicCounts).length} topics → content-manifest.json`);
  for (const [topic, count] of Object.entries(topicCounts)) {
    console.log(`  ${topic}: ${count} files`);
  }
}

main();
