object AndroidX {
    private const val coreKtxVersion = "1.9.0"
    const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"

    private const val appCompatVersion = "1.6.1"
    const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"

    private const val lifecycleVmKtxVersion = "2.6.1"
    const val lifecycleVmKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVmKtxVersion"

    private const val lifecycleRuntimeKtxVersion = "2.6.1"
    const val lifecycleRunKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleRuntimeKtxVersion"

    private const val datastoreVersion = "1.0.0"
    const val datastore = "androidx.datastore:datastore-preferences:$datastoreVersion"

}

object TestImplementation {
    private const val version = "4.13.2"
    const val junit = "junit:junit:$version"
}

object AndroidTestImplementation {

    private const val version = "1.5.2"
    const val runner = "androidx.test:runner:$version"

    private const val junitVersion = "1.1.5"
    const val junit = "androidx.test.ext:junit:$junitVersion"

    private const val espressoVersion = "3.5.1"
    const val espresso = "androidx.test:runner:$espressoVersion"

    private const val composeJunitVersion = "1.3.0"
    const val composeJunit = "androidx.compose.ui:ui-test-junit4:${Compose.composeVersion}"

}

object DebugImplementation {

    const val uiTooling = "androidx.compose.ui:ui-tooling:${Compose.composeVersion}"
    const val testManifest = "androidx.compose.ui:ui-test-manifest:${Compose.composeVersion}"
}

object LeakCanaryImplementation {

    private const val leakCanaryVersion = "2.12"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:$leakCanaryVersion"
}