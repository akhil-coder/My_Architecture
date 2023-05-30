object Room {

    private const val room_version = "2.5.1"


    const val runtime =  "androidx.room:room-runtime:$room_version"
    const val compilerAnnotation = "androidx.room:room-compiler:$room_version"

    // To use Kotlin annotation processing tool (kapt) MUST HAVE!
    const val compailerKapt = "androidx.room:room-compiler:$room_version"
    const val roomKtx = "androidx.room:room-ktx:$room_version"
}