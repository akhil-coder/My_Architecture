apply{
    from("$rootDir/android-library-build.gradle")
}

dependencies {
    "implementation"(project(Modules.domain))
    "implementation"(project(Modules.core))

    //Retrofit
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.gsonConvertor)
    "implementation"(Retrofit.okHttp)

    //Room
    "implementation"(Room.runtime)
    "annotationProcessor"(Room.compilerAnnotation)
    "kapt"(Room.compailerKapt)
    "implementation"(Room.roomKtx)

}