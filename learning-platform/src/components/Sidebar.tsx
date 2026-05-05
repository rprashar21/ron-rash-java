'use client';

import { useState, useMemo } from 'react';
import Link from 'next/link';
import { usePathname } from 'next/navigation';
import type { Manifest, ManifestEntry } from '@/types/manifest';

interface TreeNode {
  label: string;
  slug: string;
  children: TreeNode[];
  entry?: ManifestEntry;
}

function buildTree(entries: ManifestEntry[]): TreeNode {
  const root: TreeNode = { label: '', slug: '', children: [] };

  for (const entry of entries) {
    const parts = entry.slug.split('/');
    let node = root;

    for (let i = 0; i < parts.length; i++) {
      const part = parts[i];
      const slug = parts.slice(0, i + 1).join('/');
      let child = node.children.find((c) => c.slug === `${entry.topic}/${slug}`);
      if (!child) {
        child = {
          label: part.replace(/-/g, ' '),
          slug: `${entry.topic}/${slug}`,
          children: [],
          entry: i === parts.length - 1 ? entry : undefined,
        };
        node.children.push(child);
      }
      node = child;
    }
  }

  return root;
}

const TOPIC_ICONS: Record<string, string> = {
  java: '☕',
  notes: '📝',
  'ai-engineering': '🤖',
  'data-engineering': '📊',
  'dev-ops': '⚙️',
  cloud: '☁️',
  frontend: '🌐',
  'integration-tests': '🧪',
  crypto: '₿',
};

interface TopicTreeProps {
  topic: string;
  entries: ManifestEntry[];
  currentPath: string;
}

function TopicTree({ topic, entries, currentPath }: TopicTreeProps) {
  const [expanded, setExpanded] = useState(() =>
    currentPath.startsWith(`/${topic}`)
  );
  const tree = useMemo(() => buildTree(entries), [entries]);
  const icon = TOPIC_ICONS[topic] ?? '📁';
  const isActive = currentPath === `/${topic}`;

  return (
    <div className="mb-1">
      <div className="flex items-center">
        <Link
          href={`/${topic}`}
          className={`flex-1 flex items-center gap-2 px-3 py-2 rounded-lg text-sm font-medium transition-colors ${
            isActive
              ? 'bg-blue-50 dark:bg-blue-900/30 text-blue-600 dark:text-blue-400'
              : 'text-slate-700 dark:text-slate-300 hover:bg-slate-100 dark:hover:bg-slate-800'
          }`}
        >
          <span>{icon}</span>
          <span className="capitalize truncate">{topic.replace(/-/g, ' ')}</span>
        </Link>
        <button
          onClick={() => setExpanded((e) => !e)}
          className="p-1 rounded text-slate-400 hover:text-slate-600 dark:hover:text-slate-200 transition-colors"
          aria-label={expanded ? 'Collapse' : 'Expand'}
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            className={`w-3 h-3 transition-transform ${expanded ? 'rotate-90' : ''}`}
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
          >
            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 5l7 7-7 7" />
          </svg>
        </button>
      </div>

      {expanded && (
        <div className="ml-3 pl-3 border-l border-slate-200 dark:border-slate-700 mt-1 max-h-64 overflow-y-auto">
          {tree.children.map((node) => (
            <TreeNodeItem key={node.slug} node={node} topic={topic} currentPath={currentPath} depth={0} />
          ))}
        </div>
      )}
    </div>
  );
}

interface TreeNodeItemProps {
  node: TreeNode;
  topic: string;
  currentPath: string;
  depth: number;
}

function TreeNodeItem({ node, topic, currentPath, depth }: TreeNodeItemProps) {
  const [expanded, setExpanded] = useState(false);
  const hasChildren = node.children.length > 0;
  const href = `/${node.slug}`;
  const isActive = currentPath === href;

  if (node.entry && !hasChildren) {
    return (
      <Link
        href={href}
        className={`block py-1 px-2 text-xs rounded transition-colors truncate ${
          isActive
            ? 'text-blue-600 dark:text-blue-400 bg-blue-50 dark:bg-blue-900/20'
            : 'text-slate-600 dark:text-slate-400 hover:text-slate-900 dark:hover:text-slate-200'
        }`}
        style={{ paddingLeft: `${depth * 8 + 8}px` }}
        title={node.label}
      >
        {node.label}
      </Link>
    );
  }

  return (
    <div>
      <button
        onClick={() => setExpanded((e) => !e)}
        className="w-full text-left py-1 px-2 text-xs text-slate-500 dark:text-slate-500 hover:text-slate-700 dark:hover:text-slate-300 flex items-center gap-1 rounded hover:bg-slate-50 dark:hover:bg-slate-800 transition-colors truncate"
        style={{ paddingLeft: `${depth * 8 + 8}px` }}
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          className={`w-2.5 h-2.5 shrink-0 transition-transform ${expanded ? 'rotate-90' : ''}`}
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
        >
          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 5l7 7-7 7" />
        </svg>
        <span className="truncate">{node.label}</span>
      </button>
      {expanded && node.children.map((child) => (
        <TreeNodeItem
          key={child.slug}
          node={child}
          topic={topic}
          currentPath={currentPath}
          depth={depth + 1}
        />
      ))}
    </div>
  );
}

interface Props {
  manifest: Manifest;
}

export default function Sidebar({ manifest }: Props) {
  const [mobileOpen, setMobileOpen] = useState(false);
  const currentPath = usePathname();

  // Group entries by topic
  const topicMap = useMemo(() => {
    const map: Record<string, ManifestEntry[]> = {};
    for (const entry of manifest.entries) {
      if (!map[entry.topic]) map[entry.topic] = [];
      map[entry.topic].push(entry);
    }
    return map;
  }, [manifest.entries]);

  const topics = Object.keys(topicMap).sort();

  const sidebarContent = (
    <nav className="p-4">
      <div className="mb-4 text-xs font-semibold text-slate-400 dark:text-slate-500 uppercase tracking-wider">
        Topics
      </div>
      {topics.map((topic) => (
        <TopicTree
          key={topic}
          topic={topic}
          entries={topicMap[topic]}
          currentPath={currentPath}
        />
      ))}
    </nav>
  );

  return (
    <>
      {/* Desktop sidebar */}
      <aside className="hidden lg:block fixed left-0 top-14 bottom-0 w-[260px] overflow-y-auto bg-white dark:bg-slate-900 border-r border-slate-200 dark:border-slate-800 z-40">
        {sidebarContent}
      </aside>

      {/* Mobile hamburger */}
      <button
        onClick={() => setMobileOpen(true)}
        className="lg:hidden fixed bottom-4 right-4 z-50 w-12 h-12 rounded-full bg-blue-600 text-white shadow-lg flex items-center justify-center"
        aria-label="Open navigation"
      >
        <svg xmlns="http://www.w3.org/2000/svg" className="w-5 h-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 6h16M4 12h16M4 18h16" />
        </svg>
      </button>

      {/* Mobile drawer */}
      {mobileOpen && (
        <>
          <div
            className="lg:hidden fixed inset-0 bg-black/50 z-50"
            onClick={() => setMobileOpen(false)}
          />
          <aside className="lg:hidden fixed left-0 top-0 bottom-0 w-72 bg-white dark:bg-slate-900 z-50 overflow-y-auto shadow-xl">
            <div className="flex items-center justify-between p-4 border-b border-slate-200 dark:border-slate-800">
              <span className="font-bold text-slate-900 dark:text-white">Navigation</span>
              <button onClick={() => setMobileOpen(false)} className="p-1 rounded text-slate-400 hover:text-slate-700 dark:hover:text-slate-200">
                <svg xmlns="http://www.w3.org/2000/svg" className="w-5 h-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
                </svg>
              </button>
            </div>
            {sidebarContent}
          </aside>
        </>
      )}
    </>
  );
}
