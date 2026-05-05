import Link from 'next/link';
import TagBadge from './TagBadge';

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

interface Props {
  topic: string;
  fileCount: number;
  tags: string[];
}

export default function TopicCard({ topic, fileCount, tags }: Props) {
  const icon = TOPIC_ICONS[topic] ?? '📁';
  const label = topic.replace(/-/g, ' ');

  return (
    <Link
      href={`/${topic}`}
      className="group block rounded-xl border border-slate-200 dark:border-slate-800 bg-white dark:bg-slate-900 p-5 hover:border-blue-400 dark:hover:border-blue-500 hover:shadow-md transition-all duration-200 hover:-translate-y-0.5"
    >
      <div className="flex items-start justify-between mb-3">
        <span className="text-3xl">{icon}</span>
        <span className="text-xs font-medium px-2 py-1 rounded-full bg-slate-100 dark:bg-slate-800 text-slate-500 dark:text-slate-400">
          {fileCount} files
        </span>
      </div>
      <h2 className="font-semibold text-slate-900 dark:text-white capitalize mb-2 group-hover:text-blue-600 dark:group-hover:text-blue-400 transition-colors">
        {label}
      </h2>
      <div className="flex flex-wrap gap-1">
        {tags.slice(0, 3).map((tag) => (
          <TagBadge key={tag} tag={tag} small />
        ))}
      </div>
    </Link>
  );
}
