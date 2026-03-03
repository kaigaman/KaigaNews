# Kaiga News Bulletins - Android App Specification

## 1. Project Overview
- **Project Name**: Kaiga News
- **Type**: Android News Application
- **Core Functionality**: A lightweight, production-ready news app that syncs content from the Kaiga News website via RSS feed, optimized for low-bandwidth African regions with offline reading capability.

## 2. Technology Stack & Choices

### Framework & Language
- **Language**: Kotlin 1.9.x
- **Min SDK**: 24 (Android 7.0) - Wide device support for Africa
- **Target SDK**: 35
- **Compile SDK**: 35

### Key Libraries/Dependencies
- **UI**: Jetpack Compose with Material 3
- **Networking**: Retrofit 2.9 + OkHttp 4.x
- **RSS Parsing**: Rome RSS library (android-rome-feed-reader)
- **Database**: Room 2.6.x for offline caching
- **Image Loading**: Coil 2.x (Compose-native, lightweight)
- **DI**: Hilt 2.50
- **Async**: Kotlin Coroutines + Flow
- **Navigation**: Compose Navigation
- **Firebase**: Analytics + Cloud Messaging (FCM)

### State Management
- **Pattern**: MVVM with StateFlow
- **State**: UI State sealed classes for screen states

### Architecture Pattern
- **Clean Architecture** with 3 layers:
  - **Data Layer**: Repository implementations, Room DB, RSS/API services
  - **Domain Layer**: Use cases, repository interfaces, domain models
  - **UI Layer**: Compose screens, ViewModels

## 3. Feature List

### Core Features
1. **Home Screen** - Latest news headlines with card-based layout
2. **Category Screen** - Filter news by categories (Tech, Creative, Travel, Sports, etc.)
3. **Article Detail Screen** - Full article view with title, image, content, author, date
4. **Pull-to-Refresh** - Manual sync trigger from website
5. **Offline Reading** - Cache articles for offline access
6. **Search** - Basic keyword search through cached articles
7. **Share** - Share article links via WhatsApp, social media, etc.
8. **Push Notifications** - Firebase Cloud Messaging for breaking news

### Sync Features
- Auto-detect and parse RSS feed from https://kaiga.online/feed/
- Fetch article full content from feed (content:encoded field)
- Category extraction from RSS categories

### Optimization Features
- Image caching with Coil disk cache
- Text content caching with Room
- Low-data mode indicators

## 4. UI/UX Design Direction

### Visual Style
- **Design System**: Material Design 3
- **Style**: Clean, minimal, elegant - similar to Google News
- **Theme**: Dynamic theming with dark mode support

### Color Scheme
- **Primary**: #81d742 (Green - from website primary color)
- **Background Light**: #FFFFFF
- **Background Dark**: #121212
- **Surface Light**: #F5F5F5
- **Surface Dark**: #1E1E1E
- **Text Primary**: #212121 (light) / #E0E0E0 (dark)

### Layout Approach
- **Navigation**: Bottom navigation bar (Home, Categories, Search, Settings)
- **Home**: Vertical scrolling list with featured article hero card
- **Cards**: Elevated card design with image, title, date, category
- **Article Detail**: Full-width header image, scrollable content below

### Key UI Components
- Pull-to-refresh on all list screens
- Shimmer loading placeholders
- Empty state illustrations
- Error state with retry button
- Offline indicator banner
