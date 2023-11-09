
apply(from = "$rootDir/android-library-build-new.kts")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}


android {
    buildToolsVersion = Android.buildTools
    compileSdk = Android.compileSdk

    defaultConfig {

        minSdk = Android.minSdk
        targetSdk = Android.targetSdk


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"))
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.example.profile"
}


dependencies {

    "implementation"(project(Modules.navigation))
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.components))
    "implementation"(project(Modules.interactors))
    "implementation"(project(Modules.domain))

    //navigation-animation
    "implementation"(Accompanist.animation)

    //Coil
    "implementation"(Coil.coil)

    //icon
    "implementation" (Compose.icons)

    //Material3
    "implementation"(Compose.material3)
}