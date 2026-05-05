import * as fs from 'fs';
import * as path from 'path';
import type { ManifestEntry } from '@/types/manifest';

// On Vercel, process.cwd() = learning-platform/. One level up is repo root.
const REPO_ROOT = path.resolve(process.cwd(), '..');

export function readFileContent(entry: ManifestEntry): string {
  if (entry.type === 'image') {
    // Return repo-relative path; the viewer renders this as an <img> src
    return entry.path;
  }
  const absPath = path.resolve(REPO_ROOT, entry.path);
  try {
    return fs.readFileSync(absPath, 'utf-8');
  } catch (err) {
    return `// Could not read file: ${entry.path}\n// ${String(err)}`;
  }
}
