export interface Post {
  id: number;
  date: string;
  slug: string;
  title: { rendered: string };
  content: { rendered: string };
  excerpt: { rendered: string };
  featured_media: number;
  categories: number[];
  author: number;
  link: string;
  _embedded?: {
    'wp:featuredmedia'?: Array<{
      source_url: string;
      alt_text: string;
    }>;
    author?: Array<{
      name: string;
    }>;
  };
}

export interface Category {
  id: number;
  name: string;
  slug: string;
  description: string;
}

export interface FeaturedMedia {
  id: number;
  source_url: string;
  alt_text: string;
}
