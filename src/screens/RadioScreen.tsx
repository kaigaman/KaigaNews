import React from 'react';
import {
  View,
  Text,
  StyleSheet,
  FlatList,
  TouchableOpacity,
  Image,
  ActivityIndicator,
  Dimensions,
} from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { useNavigation } from '@react-navigation/native';
import { RadioStation, RADIO_STATIONS } from '../constants/radio';
import { useRadio } from '../context/RadioContext';
import { COLORS } from '../constants/categories';

const { width } = Dimensions.get('window');

export const RadioScreen: React.FC = () => {
  const navigation = useNavigation<any>();
  const { playStation, currentStation, isPlaying, isLoading, togglePlayback, stopPlayback } = useRadio();

  const renderStation = ({ item }: { item: RadioStation }) => {
    const isCurrentStation = currentStation?.id === item.id;
    const isThisPlaying = isCurrentStation && isPlaying;

    return (
      <TouchableOpacity
        style={[
          styles.stationCard,
          isCurrentStation && styles.activeStationCard,
        ]}
        onPress={() => playStation(item)}
        activeOpacity={0.8}
      >
        <View style={[styles.stationLogo, { backgroundColor: item.color }]}>
          <Ionicons name="radio" size={32} color={COLORS.white} />
        </View>
        <View style={styles.stationInfo}>
          <Text style={styles.stationName}>{item.name}</Text>
          <Text style={styles.stationFrequency}>{item.frequency}</Text>
        </View>
        {isCurrentStation && (
          <View style={styles.playingIndicator}>
            {isLoading ? (
              <ActivityIndicator size="small" color={COLORS.primary} />
            ) : isThisPlaying ? (
              <Ionicons name="pause-circle" size={32} color={COLORS.primary} />
            ) : (
              <Ionicons name="play-circle" size={32} color={COLORS.primary} />
            )}
          </View>
        )}
      </TouchableOpacity>
    );
  };

  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <TouchableOpacity
          style={styles.backButton}
          onPress={() => navigation.goBack()}
        >
          <Ionicons name="arrow-back" size={24} color={COLORS.white} />
        </TouchableOpacity>
        <Text style={styles.headerTitle}>Radio Stations</Text>
        <View style={styles.placeholder} />
      </View>

      <View style={styles.heroSection}>
        <View style={styles.heroContent}>
          <Ionicons name="radio" size={64} color={COLORS.white} />
          <Text style={styles.heroTitle}>Live Radio</Text>
          <Text style={styles.heroSubtitle}>Uganda's Best Radio Stations</Text>
          
          {currentStation && (
            <View style={styles.nowPlaying}>
              <Text style={styles.nowPlayingLabel}>Now Playing</Text>
              <Text style={styles.nowPlayingStation}>{currentStation.name}</Text>
              <View style={styles.playerControls}>
                <TouchableOpacity 
                  style={styles.controlButton}
                  onPress={togglePlayback}
                  disabled={isLoading}
                >
                  {isLoading ? (
                    <ActivityIndicator size="small" color={COLORS.white} />
                  ) : (
                    <Ionicons 
                      name={isPlaying ? "pause" : "play"} 
                      size={32} 
                      color={COLORS.white} 
                    />
                  )}
                </TouchableOpacity>
                <TouchableOpacity 
                  style={styles.controlButton}
                  onPress={stopPlayback}
                >
                  <Ionicons name="stop" size={28} color={COLORS.white} />
                </TouchableOpacity>
              </View>
            </View>
          )}
        </View>
      </View>

      <Text style={styles.sectionTitle}>All Stations</Text>

      <FlatList
        data={RADIO_STATIONS}
        renderItem={renderStation}
        keyExtractor={(item) => item.id.toString()}
        contentContainerStyle={styles.listContent}
        showsVerticalScrollIndicator={false}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: COLORS.background,
  },
  header: {
    backgroundColor: COLORS.secondary,
    paddingTop: 50,
    paddingBottom: 16,
    paddingHorizontal: 16,
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
  },
  backButton: {
    padding: 8,
  },
  headerTitle: {
    fontSize: 22,
    fontWeight: 'bold',
    color: COLORS.white,
  },
  placeholder: {
    width: 40,
  },
  heroSection: {
    backgroundColor: COLORS.primary,
    paddingVertical: 40,
    paddingHorizontal: 20,
    alignItems: 'center',
  },
  heroContent: {
    alignItems: 'center',
  },
  heroTitle: {
    fontSize: 32,
    fontWeight: 'bold',
    color: COLORS.white,
    marginTop: 16,
  },
  heroSubtitle: {
    fontSize: 16,
    color: COLORS.white,
    opacity: 0.8,
    marginTop: 8,
  },
  nowPlaying: {
    marginTop: 24,
    alignItems: 'center',
    backgroundColor: 'rgba(0,0,0,0.2)',
    padding: 16,
    borderRadius: 16,
    width: width - 80,
  },
  nowPlayingLabel: {
    fontSize: 12,
    color: COLORS.white,
    opacity: 0.7,
    textTransform: 'uppercase',
  },
  nowPlayingStation: {
    fontSize: 20,
    fontWeight: 'bold',
    color: COLORS.white,
    marginTop: 4,
  },
  playerControls: {
    flexDirection: 'row',
    marginTop: 16,
    gap: 24,
  },
  controlButton: {
    width: 48,
    height: 48,
    borderRadius: 24,
    backgroundColor: 'rgba(255,255,255,0.2)',
    justifyContent: 'center',
    alignItems: 'center',
  },
  sectionTitle: {
    fontSize: 22,
    fontWeight: 'bold',
    color: COLORS.secondary,
    marginHorizontal: 16,
    marginTop: 24,
    marginBottom: 8,
  },
  listContent: {
    paddingHorizontal: 16,
    paddingBottom: 20,
  },
  stationCard: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: COLORS.white,
    marginVertical: 6,
    padding: 16,
    borderRadius: 16,
    shadowColor: COLORS.black,
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 8,
    elevation: 4,
  },
  activeStationCard: {
    borderWidth: 2,
    borderColor: COLORS.primary,
  },
  stationLogo: {
    width: 56,
    height: 56,
    borderRadius: 16,
    justifyContent: 'center',
    alignItems: 'center',
  },
  stationInfo: {
    flex: 1,
    marginLeft: 16,
  },
  stationName: {
    fontSize: 18,
    fontWeight: 'bold',
    color: COLORS.secondary,
  },
  stationFrequency: {
    fontSize: 14,
    color: COLORS.gray,
    marginTop: 4,
  },
  playingIndicator: {
    padding: 4,
  },
});
