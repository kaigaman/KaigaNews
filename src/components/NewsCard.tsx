import React from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  Image,
  Dimensions,
} from 'react-native';
import { Post } from '../types';
import { getFeaturedImage, getAuthorName, formatDate } from '../services/api';
import { COLORS } from '../constants/categories';

interface NewsCardProps {
  post: Post;
  onPress: () => void;
  featured?: boolean;
}

const { width } = Dimensions.get('window');

export const NewsCard: React.FC<NewsCardProps> = ({ post, onPress, featured = false }) => {
  const imageUrl = getFeaturedImage(post);
  const authorName = getAuthorName(post);
  const date = formatDate(post.date);
  
  const cleanTitle = post.title.rendered.replace(/<[^>]*>/g, '');
  const cleanExcerpt = post.excerpt.rendered.replace(/<[^>]*>/g, '').substring(0, 100) + '...';

  if (featured) {
    return (
      <TouchableOpacity style={styles.featuredCard} onPress={onPress} activeOpacity={0.9}>
        <Image
          source={{ uri: imageUrl || 'https://via.placeholder.com/400x250' }}
          style={styles.featuredImage}
          resizeMode="cover"
        />
        <View style={styles.featuredOverlay}>
          <View style={styles.featuredContent}>
            <Text style={styles.featuredTitle} numberOfLines={2}>
              {cleanTitle}
            </Text>
            <View style={styles.metaRow}>
              <Text style={styles.metaText}>{authorName}</Text>
              <Text style={styles.metaDot}>•</Text>
              <Text style={styles.metaText}>{date}</Text>
            </View>
          </View>
        </View>
      </TouchableOpacity>
    );
  }

  return (
    <TouchableOpacity style={styles.card} onPress={onPress} activeOpacity={0.9}>
      <Image
        source={{ uri: imageUrl || 'https://via.placeholder.com/400x200' }}
        style={styles.cardImage}
        resizeMode="cover"
      />
      <View style={styles.cardContent}>
        <Text style={styles.cardTitle} numberOfLines={2}>
          {cleanTitle}
        </Text>
        <Text style={styles.cardExcerpt} numberOfLines={2}>
          {cleanExcerpt}
        </Text>
        <View style={styles.metaRow}>
          <Text style={styles.metaText}>{authorName}</Text>
          <Text style={styles.metaDot}>•</Text>
          <Text style={styles.metaText}>{date}</Text>
        </View>
      </View>
    </TouchableOpacity>
  );
};

const styles = StyleSheet.create({
  card: {
    backgroundColor: COLORS.white,
    marginHorizontal: 16,
    marginVertical: 8,
    borderRadius: 16,
    shadowColor: COLORS.black,
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 8,
    elevation: 4,
    overflow: 'hidden',
  },
  cardImage: {
    width: '100%',
    height: 180,
  },
  cardContent: {
    padding: 16,
  },
  cardTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    color: COLORS.secondary,
    marginBottom: 8,
  },
  cardExcerpt: {
    fontSize: 14,
    color: COLORS.gray,
    lineHeight: 20,
    marginBottom: 12,
  },
  metaRow: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  metaText: {
    fontSize: 12,
    color: COLORS.gray,
  },
  metaDot: {
    marginHorizontal: 8,
    color: COLORS.gray,
  },
  featuredCard: {
    width: width - 48,
    height: 220,
    marginHorizontal: 8,
    borderRadius: 20,
    overflow: 'hidden',
    marginRight: 16,
  },
  featuredImage: {
    width: '100%',
    height: '100%',
  },
  featuredOverlay: {
    position: 'absolute',
    bottom: 0,
    left: 0,
    right: 0,
    backgroundColor: 'rgba(0,0,0,0.6)',
    padding: 16,
  },
  featuredContent: {
    justifyContent: 'flex-end',
  },
  featuredTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    color: COLORS.white,
    marginBottom: 8,
  },
});
