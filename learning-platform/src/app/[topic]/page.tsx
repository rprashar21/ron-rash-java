import { notFound } from 'next/navigation';
import Link from 'next/link';
import { getAllTopics, getTopicEntries } from '@/lib/manifest';
import TagBadge from '@/components/TagBadge';
import TopicFileList from '@/components/TopicFileList';

export async function generateStaticParams() {
  return getAllTopics().map((topic) => ({ topic }));
}

interface Props {
  params: Promise<{ topic: string }>;
}

export default async function TopicPage({ params }: Props) {
  const { topic } = await params;
  const entries = getTopicEntries(topic);

  if (entries.length === 0) notFound();

  // Collect all unique tags across this topic
  const allTags = [...new Set(entries.flatMap((e) => e.tags))].sort();

  return (
    <div>
      <div className="mb-6">
        <div className="text-sm text-slate-500 dark:text-slate-400 mb-2">
          <Link href="/" className="hover:text-blue-500">Home</Link>
          {' / '}
          <span className="text-slate-900 dark:text-white capitalize">{topic.replace(/-/g, ' ')}</span>
        </div>
        <h1 className="text-2xl font-bold text-slate-900 dark:text-white capitalize">
          {topic.replace(/-/g, ' ')}
        </h1>
        <p className="text-slate-500 dark:text-slate-400 mt-1">{entries.length} files</p>
      </div>

      {allTags.length > 0 && (
        <div className="flex flex-wrap gap-2 mb-6">
          {allTags.map((tag) => (
            <TagBadge key={tag} tag={tag} />
          ))}
        </div>
      )}

      <TopicFileList entries={entries} topic={topic} />
    </div>
  );
}
