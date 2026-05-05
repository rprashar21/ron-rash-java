'use client';

import { useState } from 'react';
import Link from 'next/link';
import type { ManifestEntry } from '@/types/manifest';
import TagBadge from './TagBadge';

const TYPE_ICON: Record<string, string> = {
  markdown: '📄',
  code: '💻',
  text: '📋',
  image: '🖼️',
};

interface Props {
  entries: ManifestEntry[];
  topic: string;
}

export default function TopicFileList({ entries, topic }: Props) {
  const [activeTag, setActiveTag] = useState<string | null>(null);

  const filtered = activeTag
    ? entries.filter((e) => e.tags.includes(activeTag))
    : entries;

  // Collect unique tags
  const allTags = [...new Set(entries.flatMap((e) => e.tags))].sort();

  return (
    <div>
      {/* Tag filter row */}
      {allTags.length > 0 && (
        <div className="flex flex-wrap gap-2 mb-4">
          <button
            onClick={() => setActiveTag(null)}
            className={`text-xs px-2.5 py-1 rounded-full border transition-colors ${
              !activeTag
                ? 'border-blue-500 bg-blue-50 dark:bg-blue-900/20 text-blue-600 dark:text-blue-400'
                : 'border-slate-200 dark:border-slate-700 text-slate-500 dark:text-slate-400 hover:border-slate-400'
            }`}
          >
            All ({entries.length})
          </button>
          {allTags.map((tag) => (
            <TagBadge
              key={tag}
              tag={tag}
              onClick={() => setActiveTag(activeTag === tag ? null : tag)}
            />
          ))}
        </div>
      )}

      {/* File list */}
      <div className="space-y-1">
        {filtered.map((entry) => (
          <Link
            key={entry.id}
            href={`/${topic}/${entry.slug}`}
            className="flex items-start gap-3 p-3 rounded-lg hover:bg-slate-50 dark:hover:bg-slate-800/50 transition-colors group"
          >
            <span className="text-base mt-0.5 shrink-0">{TYPE_ICON[entry.type] ?? '📄'}</span>
            <div className="min-w-0 flex-1">
              <div className="text-sm font-medium text-slate-800 dark:text-slate-200 group-hover:text-blue-600 dark:group-hover:text-blue-400 transition-colors truncate">
                {entry.title}
              </div>
              {entry.excerpt && (
                <div className="text-xs text-slate-500 dark:text-slate-500 mt-0.5 truncate">
                  {entry.excerpt.slice(0, 100)}
                </div>
              )}
              {entry.tags.length > 0 && (
                <div className="flex flex-wrap gap-1 mt-1">
                  {entry.tags.slice(0, 3).map((tag) => (
                    <TagBadge key={tag} tag={tag} small />
                  ))}
                </div>
              )}
            </div>
          </Link>
        ))}
      </div>

      {filtered.length === 0 && (
        <p className="text-slate-500 dark:text-slate-400 text-sm py-8 text-center">
          No files found for this filter.
        </p>
      )}
    </div>
  );
}
