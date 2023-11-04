object Build {
    private const val androidBuildToolsVersion = "8.1.2"

    const val kotlinVersion = "1.8.10"

    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"

    const val hiltAndroid = "com.google.dagger:hilt-android-gradle-plugin:${Hilt.hiltVersion}"

    //const val sqlDelightGradlePlugin = "com.squareup.sqldelight:gradle-plugin:${SqlDelight.version}"
}