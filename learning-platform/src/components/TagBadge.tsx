interface Props {
  tag: string;
  small?: boolean;
  onClick?: () => void;
}

const TAG_COLORS: Record<string, string> = {
  java: 'bg-orange-100 text-orange-700 dark:bg-orange-900/30 dark:text-orange-400',
  notes: 'bg-green-100 text-green-700 dark:bg-green-900/30 dark:text-green-400',
  'ai-engineering': 'bg-purple-100 text-purple-700 dark:bg-purple-900/30 dark:text-purple-400',
  'data-engineering': 'bg-blue-100 text-blue-700 dark:bg-blue-900/30 dark:text-blue-400',
  'dev-ops': 'bg-red-100 text-red-700 dark:bg-red-900/30 dark:text-red-400',
  cloud: 'bg-sky-100 text-sky-700 dark:bg-sky-900/30 dark:text-sky-400',
  frontend: 'bg-yellow-100 text-yellow-700 dark:bg-yellow-900/30 dark:text-yellow-400',
  'integration-tests': 'bg-teal-100 text-teal-700 dark:bg-teal-900/30 dark:text-teal-400',
  crypto: 'bg-emerald-100 text-emerald-700 dark:bg-emerald-900/30 dark:text-emerald-400',
};

function getTagColor(tag: string): string {
  if (TAG_COLORS[tag]) return TAG_COLORS[tag];
  // Deterministic fallback based on first char
  const fallbacks = [
    'bg-slate-100 text-slate-600 dark:bg-slate-800 dark:text-slate-400',
    'bg-indigo-100 text-indigo-700 dark:bg-indigo-900/30 dark:text-indigo-400',
    'bg-pink-100 text-pink-700 dark:bg-pink-900/30 dark:text-pink-400',
  ];
  return fallbacks[tag.charCodeAt(0) % fallbacks.length];
}

export default function TagBadge({ tag, small = false, onClick }: Props) {
  const color = getTagColor(tag);
  const size = small ? 'text-xs px-1.5 py-0.5' : 'text-xs px-2 py-1';

  if (onClick) {
    return (
      <button
        onClick={onClick}
        className={`${size} ${color} rounded-full font-medium cursor-pointer hover:opacity-80 transition-opacity`}
      >
        {tag}
      </button>
    );
  }

  return (
    <span className={`${size} ${color} rounded-full font-medium`}>
      {tag}
    </span>
  );
}
