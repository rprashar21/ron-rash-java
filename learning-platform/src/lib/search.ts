import Fuse from 'fuse.js';
import type { ManifestEntry } from '@/types/manifest';

export type SearchResult = Pick<ManifestEntry, 'id' | 'title' | 'topic' | 'slug' | 'tags' | 'excerpt' | 'type'>;

let fuse: Fuse<SearchResult> | null = null;

export function initSearch(entries: SearchResult[]): void {
  fuse = new Fuse(entries, {
    keys: [
      { name: 'title', weight: 0.5 },
      { name: 'tags', weight: 0.3 },
      { name: 'excerpt', weight: 0.2 },
    ],
    threshold: 0.4,
    includeScore: true,
    minMatchCharLength: 2,
  });
}

export function search(query: string, limit = 10): SearchResult[] {
  if (!fuse || !query.trim()) return [];
  return fuse
    .search(query, { limit })
    .map((result) => result.item);
}
