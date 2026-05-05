export type FileType = 'markdown' | 'code' | 'text' | 'image';

export interface ManifestEntry {
  id: string;
  title: string;
  topic: string;
  slug: string;
  path: string;
  type: FileType;
  language?: string;
  tags: string[];
  excerpt: string;
}

export interface Manifest {
  generatedAt: string;
  totalFiles: number;
  entries: ManifestEntry[];
}
