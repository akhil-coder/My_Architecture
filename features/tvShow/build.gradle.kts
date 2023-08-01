apply {
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

    //Icon
    "implementation"(Compose.icons)

    // Pagination
    "implementation"(Paging.paging)
    "implementation"(Paging.pagingCompose)

    //Constraints
    "implementation"(Compose.constraints)

    //Material3
    "implementation"(Compose.material3)

    // Util
    "implementation"(Compose.util)

}