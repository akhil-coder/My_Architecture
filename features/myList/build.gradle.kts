apply{
    from("$rootDir/android-library-build.gradle")
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

    //pager
    "implementation" (Accompanist.pager)
    "implementation" (Accompanist.pagerIndicators)

    "implementation" ("me.saket.swipe:swipe:1.1.1")
}