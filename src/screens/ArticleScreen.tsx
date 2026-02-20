import React, { useState, useEffect } from 'react';
import {
  View,
  Text,
  StyleSheet,
  ScrollView,
  Image,
  TouchableOpacity,
  ActivityIndicator,
  Share,
  Dimensions,
} from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { RouteProp, useRoute, useNavigation } from '@react-navigation/native';
import { NativeStackNavigationProp } from '@react-navigation/native-stack';
import { Post } from '../types';
import { fetchPostBySlug, getFeaturedImage, getAuthorName, formatDate } from '../services/api';
import { COLORS } from '../constants/categories';

type RootStackParamList = {
  Article: { postId: number; postSlug: string };
};

type ArticleScreenRouteProp = RouteProp<RootStackParamList, 'Article'>;
type ArticleScreenNavigationProp = NativeStackNavigationProp<RootStackParamList>;

const { width } = Dimensions.get('window');

export const ArticleScreen: React.FC = () => {
  const route = useRoute<ArticleScreenRouteProp>();
  const navigation = useNavigation<ArticleScreenNavigationProp>();
  const { postSlug } = route.params;
  
  const [post, setPost] = useState<Post | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadPost();
  }, [postSlug]);

  const loadPost = async () => {
    setLoading(true);
    const fetchedPost = await fetchPostBySlug(postSlug);
    setPost(fetchedPost);
    setLoading(false);
  };

  const handleShare = async () => {
    if (post) {
      try {
        await Share.share({
          message: `${post.title.rendered.replace(/<[^>]*>/g, '')}\n${post.link}`,
          title: post.title.rendered.replace(/<[^>]*>/g, ''),
          url: post.link,
        });
      } catch (error) {
        console.error('Error sharing:', error);
      }
    }
  };

  if (loading) {
    return (
      <View style={styles.loadingContainer}>
        <ActivityIndicator size="large" color={COLORS.primary} />
      </View>
    );
  }

  if (!post) {
    return (
      <View style={styles.errorContainer}>
        <Text style={styles.errorText}>Article not found</Text>
      </View>
    );
  }

  const imageUrl = getFeaturedImage(post);
  const authorName = getAuthorName(post);
  const date = formatDate(post.date);
  const cleanTitle = post.title.rendered.replace(/<[^>]*>/g, '');
  const cleanContent = post.content.rendered.replace(/<[^>]*>/g, '');

  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <TouchableOpacity
          style={styles.backButton}
          onPress={() => navigation.goBack()}
        >
          <Ionicons name="arrow-back" size={24} color={COLORS.white} />
        </TouchableOpacity>
        <TouchableOpacity style={styles.shareButton} onPress={handleShare}>
          <Ionicons name="share-social" size={24} color={COLORS.white} />
        </TouchableOpacity>
      </View>

      <ScrollView style={styles.scrollView} showsVerticalScrollIndicator={false}>
        {imageUrl && (
          <Image
            source={{ uri: imageUrl }}
            style={styles.featuredImage}
            resizeMode="cover"
          />
        )}

        <View style={styles.content}>
          <View style={styles.metaContainer}>
            <Text style={styles.category}>{date}</Text>
            <Text style={styles.metaDot}>•</Text>
            <Text style={styles.category}>{authorName}</Text>
          </View>

          <Text style={styles.title}>{cleanTitle}</Text>

          <View style={styles.divider} />

          <Text style={styles.articleContent}>{cleanContent}</Text>

          <View style={styles.sourceContainer}>
            <Text style={styles.sourceLabel}>Source:</Text>
            <Text style={styles.sourceLink}>kaiga.online</Text>
          </View>
        </View>
      </ScrollView>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: COLORS.white,
  },
  header: {
    backgroundColor: COLORS.secondary,
    paddingTop: 50,
    paddingBottom: 16,
    paddingHorizontal: 16,
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  backButton: {
    padding: 8,
  },
  shareButton: {
    padding: 8,
  },
  scrollView: {
    flex: 1,
  },
  featuredImage: {
    width: width,
    height: 250,
  },
  content: {
    padding: 20,
  },
  metaContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 12,
  },
  category: {
    fontSize: 14,
    color: COLORS.primary,
    fontWeight: '600',
  },
  metaDot: {
    marginHorizontal: 8,
    color: COLORS.gray,
  },
  title: {
    fontSize: 26,
    fontWeight: 'bold',
    color: COLORS.secondary,
    lineHeight: 34,
    marginBottom: 16,
  },
  divider: {
    height: 1,
    backgroundColor: COLORS.lightGray,
    marginBottom: 20,
  },
  articleContent: {
    fontSize: 17,
    color: COLORS.secondary,
    lineHeight: 28,
    textAlign: 'justify',
  },
  sourceContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    marginTop: 30,
    paddingTop: 20,
    borderTopWidth: 1,
    borderTopColor: COLORS.lightGray,
  },
  sourceLabel: {
    fontSize: 14,
    color: COLORS.gray,
    marginRight: 8,
  },
  sourceLink: {
    fontSize: 14,
    color: COLORS.primary,
    fontWeight: '600',
  },
  loadingContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: COLORS.background,
  },
  errorContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: COLORS.background,
  },
  errorText: {
    fontSize: 18,
    color: COLORS.gray,
  },
});
