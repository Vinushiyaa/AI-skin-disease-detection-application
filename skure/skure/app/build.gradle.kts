@file:Suppress("UnstableApiUsage")

import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.skure.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.skure.app"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        // Expose OpenRouter/OpenAI key via BuildConfig (OpenRouter format)
        buildConfigField("String", "OPENROUTER_API_KEY", "\"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\"")
        
        // Google Maps API Key for Places API
        buildConfigField("String", "GOOGLE_MAPS_API_KEY", "\"AIzaSyA0Lqwretw9-_6dDOUl5NqGDiGNfq5vNts\"")
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    // Compose Compiler is applied via plugin; no need to set extension version
    packaging {
        resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" }
    }
}

kotlin {
    jvmToolchain(17)
}

// kapt configuration for Room (commented out due to build issues)
// kapt {
//     arguments {
//         arg("room.schemaLocation", "C:\\temp")
//         arg("room.incremental", "true")
//     }
//     javacOptions {
//         option("-Djava.io.tmpdir=C:\\temp")
//     }
// }

dependencies {
    implementation(platform("androidx.compose:compose-bom:2025.01.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation("androidx.navigation:navigation-compose:2.8.3")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")

    // Permissions helper (Compose)
    implementation("com.google.accompanist:accompanist-permissions:0.36.0")

    // Location services
    implementation("com.google.android.gms:play-services-location:21.3.0")

    // CameraX
    val cameraX = "1.3.4"
    implementation("androidx.camera:camera-camera2:$cameraX")
    implementation("androidx.camera:camera-lifecycle:$cameraX")
    implementation("androidx.camera:camera-view:1.3.4")

    implementation("com.google.dagger:hilt-android:2.52")
    kapt("com.google.dagger:hilt-android-compiler:2.52")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")

    // Networking - Retrofit and OkHttp
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("com.squareup.okhttp3:okhttp-dnsoverhttps:4.12.0")

    // Room Database (commented out due to build issues)
    // implementation("androidx.room:room-runtime:2.6.1")
    // implementation("androidx.room:room-ktx:2.6.1")
    // kapt("androidx.room:room-compiler:2.6.1")
}