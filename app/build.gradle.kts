plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = Android.appId
    compileSdk = Android.compileSdk

    defaultConfig {

        applicationId = Android.appId
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
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
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
    packagingOptions {
        resources {
            exclude("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {

    implementation(project(Modules.navigation))
    implementation(project(Modules.interactors))
    implementation(project(Modules.components))
    implementation(project(Modules.preferences))
    implementation(project(Modules.feature_movie))
    implementation(project(Modules.features_main))
    implementation(project(Modules.feature_auth))
    implementation(project(Modules.feature_profile))
    implementation(project(Modules.feature_myList))
    implementation(project(Modules.feature_tvShow))


    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.lifecycleRunKtx)
    implementation(AndroidX.lifecycleVmKtx)
    implementation(Compose.activity)
    implementation(Compose.ui)
    implementation(Compose.tooling)
    implementation(project(mapOf("path" to ":features:tvShow")))

    testImplementation(TestImplementation.junit)

    androidTestImplementation(AndroidTestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.espresso)
    androidTestImplementation(AndroidTestImplementation.composeJunit)

    debugImplementation(DebugImplementation.uiTooling)
    debugImplementation(DebugImplementation.testManifest)

    //Navigation
    implementation(Compose.navigation)
    implementation(Compose.hiltNavigation)

    //Dagger
    implementation(Hilt.android)
    kapt(Hilt.compiler)

    //navigation-animation
    implementation(Accompanist.animation)
    implementation(Accompanist.systemuicontroller)

    //Coil
    implementation(Coil.coil)

    //Material3
    implementation(Compose.material3)
    implementation(Compose.materialWindowSize)

    // debugImplementation because LeakCanary should only run in debug builds.
    debugImplementation(LeakCanaryImplementation.leakCanary)


    //implementation 'androidx.core:core-ktx:1.9.0'
    //implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.6.1'
    //implementation 'androidx.activity:activity-compose:1.7.0'
    //implementation "androidx.compose.ui:ui:$compose_ui_version"
    //implementation "androidx.compose.ui:ui-tooling-preview:$compose_ui_version"
    //implementation 'androidx.compose.material:material:1.4.0'
    //testImplementation 'junit:junit:4.13.2'
    //androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    //androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
    //androidTestImplementation "androidx.compose.ui:ui-test-junit4:$compose_ui_version"
    //debugImplementation "androidx.compose.ui:ui-tooling:$compose_ui_version"
    //debugImplementation "androidx.compose.ui:ui-test-manifest:$compose_ui_version"

}