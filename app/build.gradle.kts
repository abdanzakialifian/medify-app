import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.medify.app"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.medify.app"
        minSdk = 21
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // --- Android Core ---
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(libs.appcompat)
    implementation(libs.material)

    // --- BOM ---
    implementation(platform(libs.compose.bom))
    implementation(platform(libs.ktor.bom))
    implementation(platform(libs.koin.bom))

    // --- Compose ---
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.ui.test.manifest)
    implementation(libs.compose.ui.test.junit4)
    implementation(libs.compose.material3)
    implementation(libs.compose.navigation)
    implementation(libs.compose.constraintlayout)

    // --- Coroutines ---
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // --- Koin (DI) ---
    implementation(libs.koin.compose)
    implementation(libs.koin.compose.navigation)
    implementation(libs.koin.core)
    implementation(libs.koin.android)

    // -- Ktor --
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    // --- Testing ---
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    testImplementation(libs.junit.ext)
}