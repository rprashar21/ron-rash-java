import path from 'path';
import { fileURLToPath } from 'url';

const __dirname = path.dirname(fileURLToPath(import.meta.url));

/** @type {import('next').NextConfig} */
const nextConfig = {
  experimental: {
    // Required: lets Vercel bundle files from outside learning-platform/
    outputFileTracingRoot: path.join(__dirname, '../'),
  },
  images: {
    // Local repo images can't go through Vercel's image optimization CDN
    unoptimized: true,
  },
};

export default nextConfig;
