apply {
    from("$rootDir/android-library-build.gradle")
}

dependencies {

    "implementation"(project(Modules.core))
    "implementation"(project(Modules.navigation))

    //Pager
    "implementation"(Accompanist.pager)
    "implementation"(Accompanist.pagerIndicators)

    //Coil
    "implementation"(Coil.coil)
    "implementation"(Compose.material3)
}