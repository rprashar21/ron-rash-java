import { notFound } from 'next/navigation';
import Link from 'next/link';
import { getAllTopics, getTopicEntries, getEntry } from '@/lib/manifest';
import { readFileContent } from '@/lib/content';
import ContentRenderer from '@/components/ContentRenderer';
import TagBadge from '@/components/TagBadge';

export async function generateStaticParams() {
  const topics = getAllTopics();
  const params: { topic: string; slug: string[] }[] = [];

  for (const topic of topics) {
    const entries = getTopicEntries(topic).filter((e) => e.type !== 'image');
    for (const entry of entries) {
      params.push({ topic, slug: entry.slug.split('/') });
    }
  }

  return params;
}

interface Props {
  params: Promise<{ topic: string; slug: string[] }>;
}

export default async function FileViewerPage({ params }: Props) {
  const { topic, slug } = await params;
  const entry = getEntry(topic, slug);

  if (!entry) notFound();

  const content = readFileContent(entry);

  // Build breadcrumb from slug parts
  const crumbs = slug.slice(0, -1);

  return (
    <div>
      {/* Breadcrumb */}
      <nav className="text-sm text-slate-500 dark:text-slate-400 mb-6 flex flex-wrap items-center gap-1">
        <Link href="/" className="hover:text-blue-500">Home</Link>
        <span>/</span>
        <Link href={`/${topic}`} className="hover:text-blue-500 capitalize">
          {topic.replace(/-/g, ' ')}
        </Link>
        {crumbs.map((crumb, i) => (
          <span key={i} className="flex items-center gap-1">
            <span>/</span>
            <span className="capitalize">{crumb.replace(/-/g, ' ')}</span>
          </span>
        ))}
        <span>/</span>
        <span className="text-slate-900 dark:text-white">{entry.title}</span>
      </nav>

      {/* Title + meta */}
      <div className="mb-6">
        <h1 className="text-2xl font-bold text-slate-900 dark:text-white mb-3">
          {entry.title}
        </h1>
        <div className="flex flex-wrap items-center gap-3 text-sm">
          <span className="px-2 py-0.5 rounded bg-slate-100 dark:bg-slate-800 text-slate-600 dark:text-slate-400 font-mono">
            {entry.type}{entry.language ? ` · ${entry.language}` : ''}
          </span>
          {entry.tags.map((tag) => (
            <TagBadge key={tag} tag={tag} />
          ))}
        </div>
      </div>

      {/* Content */}
      <ContentRenderer entry={entry} content={content} />
    </div>
  );
}
