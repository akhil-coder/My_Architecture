dependencies {

    "implementation"(AndroidX.coreKtx)
    "implementation"(AndroidX.lifecycleRunKtx)
    "implementation"(AndroidX.lifecycleVmKtx)
    "implementation"(Compose.activity)
    "implementation"(Compose.ui)
    "implementation"(Compose.tooling)
    "implementation"(Compose.material)
    "implementation"(Compose.liveDataRuntime)

    "testImplementation"(TestImplementation.junit)

    "androidTestImplementation"(AndroidTestImplementation.runner)
    "androidTestImplementation"(AndroidTestImplementation.junit)
    "androidTestImplementation"(AndroidTestImplementation.espresso)
    "androidTestImplementation"(AndroidTestImplementation.composeJunit)

    "debugImplementation"(DebugImplementation.uiTooling)
    "debugImplementation"(DebugImplementation.testManifest)

    //Dagger
    "implementation"(Hilt.android)
    "kapt"(Hilt.compiler)

    //navigation
    "implementation"(Compose.navigation)
    "implementation"(Compose.hiltNavigation)

    //Leak Canary
    "debugImplementation"(LeakCanaryImplementation.leakCanary)
}