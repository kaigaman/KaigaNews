import React, { useState, useEffect } from 'react';
import { View, StyleSheet, Text } from 'react-native';
import { StatusBar } from 'expo-status-bar';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { GestureHandlerRootView } from 'react-native-gesture-handler';

import { HomeScreen } from './src/screens/HomeScreen';
import { CategoryScreen } from './src/screens/CategoryScreen';
import { ArticleScreen } from './src/screens/ArticleScreen';
import { SearchScreen } from './src/screens/SearchScreen';
import { RadioScreen } from './src/screens/RadioScreen';
import { MiniPlayer } from './src/components/MiniPlayer';
import { RadioProvider } from './src/context/RadioContext';
import { COLORS } from './src/constants/categories';

const Stack = createNativeStackNavigator();

function ErrorBoundary({ children }: { children: React.ReactNode }) {
  const [hasError, setHasError] = useState(false);
  const [error, setError] = useState<Error | null>(null);

  useEffect(() => {
    const handler = (e: ErrorEvent) => {
      setHasError(true);
      setError(new Error(e.message));
    };
    if (typeof window !== 'undefined') {
      window.addEventListener('error', handler);
      return () => window.removeEventListener('error', handler);
    }
  }, []);

  if (hasError) {
    return (
      <View style={styles.errorContainer}>
        <Text style={styles.errorText}>App Error</Text>
        <Text style={styles.errorMessage}>{error?.message}</Text>
      </View>
    );
  }
  return <>{children}</>;
}

export default function App() {
  return (
    <ErrorBoundary>
      <GestureHandlerRootView style={styles.container}>
        <RadioProvider>
          <NavigationContainer>
            <StatusBar />
            <View style={{flex: 1}}>
              <Stack.Navigator screenOptions={{ headerShown: false }}>
                <Stack.Screen name="Home" component={HomeScreen} />
                <Stack.Screen name="Search" component={SearchScreen} />
                <Stack.Screen name="Radio" component={RadioScreen} />
                <Stack.Screen name="Article" component={ArticleScreen} />
                <Stack.Screen name="Category" component={CategoryScreen} />
              </Stack.Navigator>
              <MiniPlayer />
            </View>
          </NavigationContainer>
        </RadioProvider>
      </GestureHandlerRootView>
    </ErrorBoundary>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1 },
  errorContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#8B0000',
    padding: 20,
  },
  errorText: {
    fontSize: 24,
    fontWeight: 'bold',
    color: 'white',
    marginBottom: 10,
  },
  errorMessage: {
    fontSize: 14,
    color: '#ffcccc',
    textAlign: 'center',
  },
});
