apply{
    from("$rootDir/android-library-build.gradle")
}

dependencies {

    "implementation"(project(Modules.domain))
    "implementation"(project(Modules.datasource))
    "implementation"(project(Modules.core))

}