import type { Manifest, ManifestEntry } from '@/types/manifest';

// eslint-disable-next-line @typescript-eslint/no-require-imports
const manifestData = require('../../content-manifest.json') as Manifest;

export function loadManifest(): Manifest {
  return manifestData;
}

export function getEntry(topic: string, slugParts: string[]): ManifestEntry | undefined {
  const slug = slugParts.join('/');
  return manifestData.entries.find((e) => e.topic === topic && e.slug === slug);
}

export function getTopicEntries(topic: string): ManifestEntry[] {
  return manifestData.entries.filter((e) => e.topic === topic);
}

export function getAllTopics(): string[] {
  return [...new Set(manifestData.entries.map((e) => e.topic))].sort();
}

export function getTopicStats(topic: string): { count: number; tags: string[] } {
  const entries = getTopicEntries(topic);
  const tagFreq: Record<string, number> = {};
  for (const e of entries) {
    for (const tag of e.tags) {
      tagFreq[tag] = (tagFreq[tag] ?? 0) + 1;
    }
  }
  const tags = Object.entries(tagFreq)
    .sort((a, b) => b[1] - a[1])
    .slice(0, 5)
    .map(([tag]) => tag);
  return { count: entries.length, tags };
}
