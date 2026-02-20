import axios from 'axios';
import { Post, Category } from '../types';
import { WP_API_URL } from '../constants/categories';

const api = axios.create({
  baseURL: WP_API_URL,
  timeout: 15000,
});

export const fetchPosts = async (page: number = 1, perPage: number = 10): Promise<Post[]> => {
  try {
    const response = await api.get<Post[]>('/posts', {
      params: {
        page,
        per_page: perPage,
        _embed: true,
      },
    });
    return response.data;
  } catch (error) {
    console.error('Error fetching posts:', error);
    return [];
  }
};

export const fetchPostsByCategory = async (categoryId: number, page: number = 1, perPage: number = 10): Promise<Post[]> => {
  try {
    const response = await api.get<Post[]>('/posts', {
      params: {
        categories: categoryId,
        page,
        per_page: perPage,
        _embed: true,
      },
    });
    return response.data;
  } catch (error) {
    console.error('Error fetching posts by category:', error);
    return [];
  }
};

export const fetchPostBySlug = async (slug: string): Promise<Post | null> => {
  try {
    const response = await api.get<Post[]>('/posts', {
      params: {
        slug,
        _embed: true,
      },
    });
    return response.data[0] || null;
  } catch (error) {
    console.error('Error fetching post by slug:', error);
    return null;
  }
};

export const searchPosts = async (searchTerm: string, page: number = 1): Promise<Post[]> => {
  try {
    const response = await api.get<Post[]>('/posts', {
      params: {
        search: searchTerm,
        page,
        per_page: 20,
        _embed: true,
      },
    });
    return response.data;
  } catch (error) {
    console.error('Error searching posts:', error);
    return [];
  }
};

export const fetchCategories = async (): Promise<Category[]> => {
  try {
    const response = await api.get<Category[]>('/categories', {
      params: {
        per_page: 100,
      },
    });
    return response.data;
  } catch (error) {
    console.error('Error fetching categories:', error);
    return [];
  }
};

export const getFeaturedImage = (post: Post): string | null => {
  if (post._embedded?.['wp:featuredmedia']?.[0]?.source_url) {
    return post._embedded['wp:featuredmedia'][0].source_url;
  }
  return null;
};

export const getAuthorName = (post: Post): string => {
  return post._embedded?.author?.[0]?.name || 'Unknown';
};

export const formatDate = (dateString: string): string => {
  const date = new Date(dateString);
  return date.toLocaleDateString('en-US', {
    year: 'numeric',
    month: 'short',
    day: 'numeric',
  });
};
