const val kotlinVersion = "1.3.41"

object BuildPlugins {

    object Versions {
        const val buildToolsVersion = "3.4.2"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlin_kapt = "kotlin-kapt"
    const val jacoco = "jacoco"
}
