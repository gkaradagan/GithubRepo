import Libraries.Versions.coroutinesVersion
import Libraries.Versions.daggerVersion
import Libraries.Versions.glideVersion
import Libraries.Versions.gsonVersion
import Libraries.Versions.ktxFragmentVersion
import Libraries.Versions.lifecycleVersion
import Libraries.Versions.okhttpVersion
import Libraries.Versions.retrofitVersion
import Libraries.Versions.timberVersion

object Libraries {
    private object Versions {
        const val jetpack = "1.0.2"
        const val constraintLayout = "1.1.2"
        const val ktx = "1.0.2"
        const val ktxFragmentVersion = "1.1.0-alpha09"
        const val lifecycleVersion = "2.2.0-alpha02"
        const val coroutinesVersion = "1.3.1"
        const val timberVersion = "4.7.1"
        const val daggerVersion = "2.23.2"
        const val retrofitVersion = "2.6.0"
        const val okhttpVersion = "3.10.0"
        const val gsonVersion = "2.8.2"
        const val glideVersion = "4.4.0"
        const val recyclerViewVersion = "1.1.0-alpha05"
        const val cardviewVersion = "1.0.0"
        const val materialdatetimepickerVersion = "4.2.3"
        const val shimmerVersion = "0.4.0"
        const val roomVersion = "2.2.0-rc01"
        const val material = "1.1.0-alpha10"

    }

    const val material = "com.google.android.material:material:${Versions.material}"

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.jetpack}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerViewVersion}"
    const val cardview = "androidx.cardview:cardview:${Versions.cardviewVersion}"
    const val materialdatetimepicker =
        "com.wdullaer:materialdatetimepicker:${Versions.materialdatetimepickerVersion}"
    const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmerVersion}"

    const val room_runtime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val kapt_room_compiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.roomVersion}"

    const val ktx_fragment = "androidx.fragment:fragment-ktx:$ktxFragmentVersion"
    const val lifecycle_extensions = "androidx.lifecycle:lifecycle-extensions:$lifecycleVersion"
    const val livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
    const val viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"

    const val timber = "com.jakewharton.timber:timber:$timberVersion"

    const val kapt_dagger_compiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    const val kapt_dagger_processor = "com.google.dagger:dagger-android-processor:$daggerVersion"
    const val dagger = "com.google.dagger:dagger:$daggerVersion"
    const val dagger_android = "com.google.dagger:dagger-android:$daggerVersion"
    const val dagger_android_support = "com.google.dagger:dagger-android-support:$daggerVersion"

    const val gson = "com.google.code.gson:gson:$gsonVersion"
    const val gson_converter = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val okhttp3 = "com.squareup.okhttp3:okhttp:$okhttpVersion"
    const val okhttp3_logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:$okhttpVersion"


    const val glide = "com.github.bumptech.glide:glide:$glideVersion"
    const val glide_kapt = "com.github.bumptech.glide:compiler:$glideVersion"

}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.12"
        const val testRunner = "1.2.0"
        const val espresso = "3.2.0"
        const val mockwebserver = "3.8.1"
        const val coreTestingVersion = "2.0.0"
        const val mockitoVersion = "2.25.0"
        const val coroutines = "1.3.1"
        const val roomVersion = "2.2.0-rc01"
        const val junit_android = "1.1.1"
        const val robolectric = "4.3"
    }


    const val junit4 = "junit:junit:${Versions.junit4}"
    const val mockwebserver = "com.squareup.okhttp3:mockwebserver:${Versions.mockwebserver}"
    const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTestingVersion}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockitoVersion}"
    const val coroutines_test =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"

    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val junit_android = "androidx.test.ext:junit:${Versions.junit_android}"
    const val junit_android_ktx = "androidx.test.ext:junit-ktx:${Versions.junit_android}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"

    const val room = "androidx.room:room-testing:${Versions.roomVersion}"

}