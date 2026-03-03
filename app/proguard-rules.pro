# Add project specific ProGuard rules here.

# Keep data classes
-keep class com.kaiganews.app.domain.model.** { *; }
-keep class com.kaiganews.app.data.local.entity.** { *; }

# Retrofit
-keepattributes Signature
-keepattributes Exceptions
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# OkHttp
-dontwarn okhttp3.**
-dontwarn okio.**

# RSS Parser
-keep class com.prof18.rssparser.** { *; }

# Firebase
-keep class com.google.firebase.** { *; }

# JSoup
-keeppackagenames org.jsoup.nodes
