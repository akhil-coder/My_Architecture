apply {
    from("$rootDir/android-library-build.gradle")
}

dependencies {
    "implementation"(project(Modules.domain))
    "implementation"(project(Modules.core))

    //Retrofit
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.gsonConvertor)
    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.loggingInterceptor)


    // Paging
    "implementation"(Paging.paging)
    "implementation"(Paging.pagingCompose)


    //Room
    "implementation"(Room.runtime)
    "annotationProcessor"(Room.compilerAnnotation)
    "kapt"(Room.compailerKapt)
    "implementation"(Room.roomKtx)

}