import { getAllTopics, getTopicStats } from '@/lib/manifest';
import TopicCard from '@/components/TopicCard';

export default function DashboardPage() {
  const topics = getAllTopics();

  return (
    <div>
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-slate-900 dark:text-white mb-2">
          Knowledge Hub
        </h1>
        <p className="text-slate-500 dark:text-slate-400">
          {topics.length} topics · personal engineering learning vault
        </p>
      </div>

      <div className="grid grid-cols-1 sm:grid-cols-2 xl:grid-cols-3 gap-4">
        {topics.map((topic) => {
          const { count, tags } = getTopicStats(topic);
          return (
            <TopicCard key={topic} topic={topic} fileCount={count} tags={tags} />
          );
        })}
      </div>
    </div>
  );
}
