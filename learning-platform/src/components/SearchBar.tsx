'use client';

import { useState, useEffect, useRef, useCallback } from 'react';
import { useRouter } from 'next/navigation';
import { initSearch, search } from '@/lib/search';
import type { SearchResult } from '@/lib/search';

interface Props {
  entries: SearchResult[];
}

export default function SearchBar({ entries }: Props) {
  const [query, setQuery] = useState('');
  const [results, setResults] = useState<SearchResult[]>([]);
  const [activeIndex, setActiveIndex] = useState(-1);
  const [open, setOpen] = useState(false);
  const inputRef = useRef<HTMLInputElement>(null);
  const router = useRouter();

  useEffect(() => {
    initSearch(entries);
  }, [entries]);

  const runSearch = useCallback((q: string) => {
    const r = search(q);
    setResults(r);
    setActiveIndex(-1);
    setOpen(r.length > 0 && q.length > 0);
  }, []);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const q = e.target.value;
    setQuery(q);
    runSearch(q);
  };

  const navigate = (result: SearchResult) => {
    router.push(`/${result.topic}/${result.slug}`);
    setOpen(false);
    setQuery('');
  };

  const handleKeyDown = (e: React.KeyboardEvent) => {
    if (!open) return;
    if (e.key === 'ArrowDown') {
      e.preventDefault();
      setActiveIndex((i) => Math.min(i + 1, results.length - 1));
    } else if (e.key === 'ArrowUp') {
      e.preventDefault();
      setActiveIndex((i) => Math.max(i - 1, 0));
    } else if (e.key === 'Enter' && activeIndex >= 0) {
      e.preventDefault();
      navigate(results[activeIndex]);
    } else if (e.key === 'Escape') {
      setOpen(false);
    }
  };

  return (
    <div className="relative w-full">
      <div className="relative">
        <svg
          className="absolute left-3 top-1/2 -translate-y-1/2 w-4 h-4 text-slate-400"
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
        >
          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
        </svg>
        <input
          ref={inputRef}
          type="text"
          value={query}
          onChange={handleChange}
          onKeyDown={handleKeyDown}
          onFocus={() => query && runSearch(query)}
          onBlur={() => setTimeout(() => setOpen(false), 150)}
          placeholder="Search notes, topics, tags…"
          className="w-full pl-10 pr-4 py-2 text-sm bg-slate-100 dark:bg-slate-800 border border-slate-200 dark:border-slate-700 rounded-lg text-slate-900 dark:text-slate-100 placeholder-slate-400 focus:outline-none focus:ring-2 focus:ring-blue-500 dark:focus:ring-blue-500"
        />
      </div>

      {open && results.length > 0 && (
        <div className="absolute top-full mt-1 w-full bg-white dark:bg-slate-900 border border-slate-200 dark:border-slate-700 rounded-lg shadow-xl z-50 max-h-80 overflow-y-auto">
          {results.map((result, i) => (
            <button
              key={result.id}
              onClick={() => navigate(result)}
              className={`w-full text-left px-4 py-3 flex flex-col gap-0.5 hover:bg-slate-50 dark:hover:bg-slate-800 transition-colors border-b border-slate-100 dark:border-slate-800 last:border-0 ${
                i === activeIndex ? 'bg-slate-50 dark:bg-slate-800' : ''
              }`}
            >
              <span className="text-sm font-medium text-slate-900 dark:text-white truncate">
                {result.title}
              </span>
              <span className="text-xs text-slate-500 dark:text-slate-400 truncate">
                {result.topic} / {result.slug}
              </span>
              {result.excerpt && (
                <span className="text-xs text-slate-400 truncate">
                  {result.excerpt.slice(0, 80)}
                </span>
              )}
            </button>
          ))}
        </div>
      )}
    </div>
  );
}
