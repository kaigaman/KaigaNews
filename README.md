# Kaiga News Bulletins - Android App

A production-ready Android news application for Kaiga News Bulletins, optimized for low-bandwidth African regions with offline reading capability.

## Features

- **Home Screen**: Latest news headlines with featured article hero card
- **FM Radio Uganda**: Live streaming from 20+ Ugandan radio stations
- **Categories**: Browse news by category (Tech, Creative, Travel, Sports, etc.)
- **Article Detail**: Full article view with title, image, author, date, and content
- **Instant Sync**: Automatic sync every 15 minutes + on app launch
- **Pull-to-Refresh**: Manual sync from website via RSS feed
- **Offline Reading**: Cache articles for offline access using Room database
- **Search**: Keyword search through cached articles
- **Share**: Share articles via WhatsApp, social media, etc.
- **Push Notifications**: Firebase Cloud Messaging for breaking news
- **Dark Mode**: System theme support

## Architecture

The app follows **Clean Architecture** with three distinct layers:

```
app/
├── data/                    # Data Layer
│   ├── local/              # Room database, DAOs, entities
│   ├── remote/             # RSS service, Firebase service
│   ├── sync/               # WorkManager sync
│   └── repository/         # Repository implementation
├── domain/                  # Domain Layer
│   ├── model/              # Domain models
│   ├── repository/         # Repository interfaces
│   └── usecase/            # Use cases
├── ui/                      # Presentation Layer
│   ├── screens/            # Compose screens
│   ├── components/         # Reusable UI components
│   ├── navigation/          # Navigation setup
│   ├── theme/              # Material 3 theme
│   └── viewmodel/          # ViewModels
└── di/                      # Dependency injection modules
```

## Tech Stack

- **Language**: Kotlin 1.9.x
- **UI**: Jetpack Compose with Material 3
- **Architecture**: MVVM + Clean Architecture
- **DI**: Hilt 2.50
- **Database**: Room 2.6.1
- **Networking**: Retrofit 2.9 + OkHttp 4.12
- **RSS Parsing**: RssParser 6.0.6
- **Image Loading**: Coil 2.5
- **Background Sync**: WorkManager 2.9
- **Media Player**: Media3 ExoPlayer 1.2
- **Firebase**: Analytics + Cloud Messaging

## Instant Sync

The app uses **WorkManager** for automatic background sync:

1. **On App Launch**: Instantly syncs when app opens
2. **Periodic Sync**: Every 15 minutes when connected to network
3. **Manual Refresh**: Tap refresh button anytime

### Sync Implementation

```kotlin
// WorkManager schedules periodic sync
NewsSyncWorker.schedule(context)  // Every 15 min
NewsSyncWorker.syncNow(context)   // Immediate sync
```

### Cache Strategy

- Articles are cached in Room database
- Images are cached using Coil's disk cache
- Cache is valid for 1 hour, then old articles are cleaned up
- App works offline using cached data

## FM Radio Uganda

The app includes live streaming for 20+ Ugandan radio stations:

- BBC Uganda
- Radio Uganda (CBS)
- Capital FM
- Radio 4 (NBS)
- KFM
- Dembe FM
- Hue Radio
- Radio Simba
- And many more...

### Radio Features

- Live streaming using Media3 ExoPlayer
- Background playback support
- Play/Pause/Stop controls
- Now playing indicator
- Works with minimal bandwidth

### Radio Implementation

```kotlin
// Uses Media3 ExoPlayer for streaming
radioPlayerManager.play(station)
radioPlayerManager.pause()
radioPlayerManager.stop()
```

## How to Swap Data Sources

To switch from RSS to a REST API:

### 1. Create API Service

```kotlin
interface NewsApiService {
    @GET("articles")
    suspend fun getArticles(): List<ArticleDto>
}
```

### 2. Update RssService

Replace `RssService` with a new `ApiService` class that calls the REST endpoint.

### 3. Update Repository

Modify `NewsRepositoryImpl` to use the new API service instead of RSS.

### Example: Adding a Custom API

```kotlin
@Singleton
class ApiService @Inject constructor(
    private val retrofit: Retrofit
) {
    private val api = retrofit.create(NewsApiService::class.java)
    
    suspend fun fetchArticles(): Result<List<Article>> {
        return try {
            val articles = api.getArticles().map { it.toArticle() }
            Result.success(articles)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
```

## Build Instructions

### Prerequisites

- JDK 17+
- Android SDK 35
- Gradle 8.10.2

### Build Commands

```bash
# Set Android SDK
export ANDROID_HOME=/path/to/android/sdk

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Run tests
./gradlew test
```

### Generate Signed APK

1. Create `keystore.jks` in app directory
2. Update `app/build.gradle.kts` with signing config
3. Run `./gradlew assembleRelease`

## Firebase Setup

1. Create project at [Firebase Console](https://console.firebase.google.com)
2. Add Android app with package name: `com.kaiganews.app`
3. Download `google-services.json` and place in `app/`
4. Enable Cloud Messaging in Firebase Console

## AdMob Integration (Future)

To add AdMob ads:

1. Add AdMob dependency to `build.gradle.kts`
2. Create AdMob app in Google AdSense
3. Add ad unit IDs to `strings.xml`
4. Implement banner/native ads in screens

## Multi-Language Support

The app is prepared for multi-language support:

1. Add translations to `values/strings.xml`
2. Add locale-specific folders (e.g., `values-fr/strings.xml`)
3. Use `stringResource()` in Compose

## License

This project is proprietary software for Kaiga News Bulletins.

## Support

For issues or questions, please contact the development team.
