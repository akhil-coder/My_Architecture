apply{
    from("$rootDir/android-library-build.gradle")
}

dependencies {

    "implementation"(project(Modules.core))
    "implementation"(project(Modules.navigation))

    //pager
    "implementation" (Accompanist.pager)
    "implementation" (Accompanist.pagerIndicators)

    //Coil
    "implementation"(Coil.coil)
}