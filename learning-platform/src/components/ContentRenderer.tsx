import ReactMarkdown from 'react-markdown';
import remarkGfm from 'remark-gfm';
import rehypeHighlight from 'rehype-highlight';
import rehypeRaw from 'rehype-raw';
import type { ManifestEntry } from '@/types/manifest';
import CodeBlock from './CodeBlock';

interface Props {
  entry: ManifestEntry;
  content: string;
}

export default function ContentRenderer({ entry, content }: Props) {
  if (entry.type === 'image') {
    return (
      <div className="flex justify-center">
        {/* eslint-disable-next-line @next/next/no-img-element */}
        <img
          src={`/api/image?path=${encodeURIComponent(content)}`}
          alt={entry.title}
          className="max-w-full rounded-lg border border-slate-200 dark:border-slate-700"
        />
      </div>
    );
  }

  if (entry.type === 'code') {
    return <CodeBlock code={content} language={entry.language} />;
  }

  if (entry.type === 'text') {
    return (
      <pre className="whitespace-pre-wrap font-mono text-sm leading-relaxed text-slate-700 dark:text-slate-300 bg-slate-50 dark:bg-slate-900 border border-slate-200 dark:border-slate-800 rounded-lg p-6 overflow-x-auto">
        {content}
      </pre>
    );
  }

  // markdown
  return (
    <div className="prose-content max-w-none">
      <ReactMarkdown
        remarkPlugins={[remarkGfm]}
        rehypePlugins={[rehypeRaw, rehypeHighlight]}
        components={{
          // Override code blocks to use our CodeBlock component
          code(props) {
            const { className, children, ...rest } = props;
            const match = /language-(\w+)/.exec(className ?? '');
            const isBlock = !!(match || (className && className.includes('hljs')));
            const codeStr = String(children).replace(/\n$/, '');

            if (isBlock) {
              return <CodeBlock code={codeStr} language={match?.[1]} />;
            }
            return (
              <code
                className="bg-slate-100 dark:bg-slate-800 text-slate-800 dark:text-emerald-300 px-1.5 py-0.5 rounded text-sm font-mono"
                {...rest}
              >
                {children}
              </code>
            );
          },
          // Wrap images safely
          img(props) {
            const { src, alt } = props;
            if (!src) return null;
            // eslint-disable-next-line @next/next/no-img-element
            return <img src={src} alt={alt ?? ''} className="max-w-full rounded-lg my-4" />;
          },
        }}
      >
        {content}
      </ReactMarkdown>
    </div>
  );
}
