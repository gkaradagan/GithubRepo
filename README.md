# GithubRepo!
This application is written in kotlin also gradle is written in kotlin-dsl. 

* [Apk File](https://github.com/gkaradagan/GithubRepo/blob/master/apk/app-debug.apk) 

![GithubRepo Application Demo](https://github.com/gkaradagan/GithubRepo/blob/master/art/appdemo.gif)

The application uses Clean Architecture based on MVVM and Repository patterns. Also Repository, ViewModel, Room and Service testes is written. Automatically codeCoverageReport task will added when app is builded. I used jacoco library for creating code coverage report because android studio is not calculating it when gradle is written in kotlin-dsl.

![Image of Test Result](https://github.com/gkaradagan/GithubRepo/blob/master/art/testresult.png)


### Libraries Used

* [Kotlin Gradle DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) 

* [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) 

* [Material-io](https://material.io/) 

* [Room](https://developer.android.com/jetpack/androidx/releases/room) 

* [Dagger 2](https://dagger.dev/users-guide) 

* [Retrofit 2](https://square.github.io/retrofit/) 

* [OkHttp 3](https://square.github.io/okhttp/) 

* [GSON](https://github.com/google/gson)

* [Glide](https://bumptech.github.io/glide/) 

* [Data Binding](https://developer.android.com/topic/libraries/support-library/packages#v7-appcompat) 

* [Lifecycles](https://developer.android.com/topic/libraries/architecture/lifecycle)

* [LiveData](https://developer.android.com/topic/libraries/architecture/lifecycle) 

* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) 

### For Testing

* [Unit Tests - JUnit](https://junit.org/junit4/) 

* [Mockito-Kotlin](https://github.com/nhaarman/mockito-kotlin) 

* [MockServer](https://github.com/Kotlin/kotlinx.coroutines/tree/master/kotlinx-coroutines-test) 

* [Code Coverage (Jacoco)](https://www.eclemma.org/jacoco/) 
