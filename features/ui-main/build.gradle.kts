apply {
    from("$rootDir/android-library-build.gradle")
}

dependencies {

    "implementation"(project(Modules.navigation))
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.preferences))
    "implementation"(project(Modules.components))
    "implementation"(project(Modules.interactors))
    "implementation"(project(Modules.domain))

    "implementation"(AndroidX.appCompat)

    //navigation-animation
    "implementation"(Accompanist.animation)

    //Coil
    "implementation"(Coil.coil)

    //icon
    "implementation"(Compose.icons)

    //Material3
    "implementation"(Compose.material3)
    "implementation"(Compose.materialWindowSize)
}