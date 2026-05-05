import type { Metadata } from 'next';
import { ThemeProvider } from 'next-themes';
import './globals.css';
import Sidebar from '@/components/Sidebar';
import SearchBar from '@/components/SearchBar';
import DarkModeToggle from '@/components/DarkModeToggle';
import { loadManifest } from '@/lib/manifest';
import type { SearchResult } from '@/lib/search';

export const metadata: Metadata = {
  title: 'Knowledge Hub',
  description: 'Personal engineering learning platform',
};

export default function RootLayout({ children }: { children: React.ReactNode }) {
  const manifest = loadManifest();

  const searchEntries: SearchResult[] = manifest.entries
    .filter((e) => e.type !== 'image')
    .map(({ id, title, topic, slug, tags, excerpt, type }) => ({
      id, title, topic, slug, tags, excerpt, type,
    }));

  return (
    <html lang="en" suppressHydrationWarning>
      <body className="bg-white dark:bg-slate-950 text-slate-900 dark:text-slate-100 min-h-screen">
        <ThemeProvider attribute="class" defaultTheme="dark" enableSystem={false}>
          {/* Top nav bar */}
          <header className="fixed top-0 left-0 right-0 z-50 h-14 bg-white dark:bg-slate-900 border-b border-slate-200 dark:border-slate-800 flex items-center px-4 gap-4">
            <a
              href="/"
              className="font-bold text-lg text-slate-900 dark:text-white whitespace-nowrap shrink-0"
            >
              Knowledge Hub
            </a>
            <div className="flex-1 max-w-xl">
              <SearchBar entries={searchEntries} />
            </div>
            <div className="ml-auto">
              <DarkModeToggle />
            </div>
          </header>

          {/* Body: sidebar + main */}
          <div className="flex pt-14 min-h-screen">
            <Sidebar manifest={manifest} />
            <main className="flex-1 ml-0 lg:ml-[260px] min-w-0 p-6 lg:p-8">
              {children}
            </main>
          </div>
        </ThemeProvider>
      </body>
    </html>
  );
}
