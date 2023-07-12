apply {
    from("$rootDir/library-build.gradle")
}

plugins {
    kotlin("plugin.serialization") version "1.8.22"
}

dependencies {

    "implementation"(project(Modules.core))

    "implementation"(Kotlinx.coroutinesCore)

    "implementation"(Kotlinx.serialization)

}