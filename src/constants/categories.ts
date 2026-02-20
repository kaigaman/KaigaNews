import { Ionicons } from '@expo/vector-icons';

export interface CategoryItem {
  id: number;
  name: string;
  slug: string;
  icon: keyof typeof Ionicons.glyphMap;
  color: string;
}

export const CATEGORIES: CategoryItem[] = [
  { id: 0, name: 'Home', slug: 'home', icon: 'home', color: '#1a1a2e' },
  { id: 1, name: 'Featured', slug: 'featured', icon: 'star', color: '#e94560' },
  { id: 2, name: 'News', slug: 'news', icon: 'newspaper', color: '#0f3460' },
  { id: 3, name: 'Creative', slug: 'creative', icon: 'color-palette', color: '#e94560' },
  { id: 4, name: 'Travel', slug: 'travel', icon: 'airplane', color: '#16c79a' },
  { id: 5, name: 'Tech', slug: 'tech', icon: 'laptop', color: '#11999e' },
  { id: 6, name: 'Fashion', slug: 'fashion', icon: 'shirt', color: '#f64c72' },
  { id: 7, name: 'Food', slug: 'food', icon: 'restaurant', color: '#fcbad3' },
  { id: 8, name: 'Sports', slug: 'sports', icon: 'football', color: '#00b8a9' },
  { id: 9, name: 'Music', slug: 'music', icon: 'musical-notes', color: '#845ec2' },
  { id: 10, name: 'Shop', slug: 'shop', icon: 'cart', color: '#ffc75f' },
];

export const WP_API_URL = 'https://kaiga.online/wp-json/wp/v2';

export const COLORS = {
  primary: '#8B0000',
  secondary: '#1a1a2e',
  accent: '#e94560',
  background: '#f5f5f5',
  white: '#ffffff',
  black: '#000000',
  gray: '#888888',
  lightGray: '#e0e0e0',
};
