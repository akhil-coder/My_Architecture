# Android Project Architecture

Android Jetpack Compose project with modularization and Clean architecture

## Getting Started

This is a project template

### Screens

- Splash Screen
- Login/Signup
- Movie List
- Movie Details
- Profile
- My Movies
  - Favorites
  - Watched List
- Settings

### Feactures Done

- Api call with retrofit
- Cashing with Room database
- Preferences DataStore for store data
- Nested navigation
- Passing data to next page
- Receiving result
- Multiple language
- Runtime theme change
- Observe network status
- Permission handling
- New Image picker

### Tech Stack

- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - To improve performance by doing I/O tasks out of main thread asynchronously.
- [Flow](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [Jetpack Compose](https://developer.android.com/jetpack/compose?gclsrc=ds&gclsrc=ds) - Jetpack Compose is Android’s recommended modern toolkit for building native UI
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
- [Accompanist](https://github.com/google/accompanist) - Accompanist is a group of libraries that aim to supplement Jetpack Compose with features that are commonly required by developers but not yet available.
- [Coil](https://coil-kt.github.io/coil/compose) - An image loading library for Android backed by Kotlin Coroutines.
- [Dagger-Hilt](https://dagger.dev/hilt) For [Dependency injection (DI)](https://developer.android.com/training/dependency-injection)
- [Room database](https://developer.android.com/jetpack/androidx/releases/room) - Persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
