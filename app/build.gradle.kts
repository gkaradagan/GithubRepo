import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlin_kapt)
    id(BuildPlugins.jacoco)
}

android {
    androidExtensions {
        isExperimental = true
    }
    testOptions.unitTests.isIncludeAndroidResources = true
    dataBinding.isEnabled = true
    compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)
    defaultConfig {
        applicationId = AndroidConfig.ID
        minSdkVersion(AndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(AndroidConfig.TARGET_SDK_VERSION)
        buildToolsVersion(AndroidConfig.BUILD_TOOLS_VERSION)

        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME
        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER
        vectorDrawables.useSupportLibrary = AndroidConfig.SUPPORT_LIBRARY_VECTOR_DRAWABLES

        javaCompileOptions {
            annotationProcessorOptions {
                arguments = mapOf("room.schemaLocation" to "$projectDir/schemas")
            }
        }
    }

    signingConfigs {
        getByName("debug") {
            storeFile = rootProject.file("debug.jks")
            storePassword = "121212"
            keyAlias = "GithubRepo"
            keyPassword = "121212"
        }
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }

        getByName(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            isTestCoverageEnabled = true
            signingConfig = signingConfigs.getByName("debug")
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        // "this" is currently lacking a proper type
        // See: https://youtrack.jetbrains.com/issue/KT-31077
        val options = this as? KotlinJvmOptions
        options?.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

jacoco {
    toolVersion = "0.8.4"
    reportsDir = file("$buildDir/customJacocoReportDir")
}

tasks {

    val codeCoverageReport by creating(JacocoReport::class) {
        executionData(fileTree(project.rootDir.absolutePath).include("**/build/jacoco/*.exec"))

        subprojects.onEach {
            sourceSets(it.sourceSets["main"])
        }

        reports {
            xml.isEnabled = false
            csv.isEnabled = false
            html.destination = file("${buildDir}/jacocoHtml")
        }

        dependsOn("test")
    }
}

dependencies {
    implementation(Libraries.material)
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.appCompat)
    implementation(Libraries.ktxCore)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.recyclerView)
    implementation(Libraries.cardview)
    implementation(Libraries.materialdatetimepicker)
    implementation(Libraries.shimmer)

    implementation(Libraries.room_runtime)
    kapt(Libraries.kapt_room_compiler)
    implementation(Libraries.room_ktx)

    implementation(Libraries.ktx_fragment)
    implementation(Libraries.lifecycle_extensions)
    implementation(Libraries.livedata_ktx)
    implementation(Libraries.viewmodel_ktx)

    implementation(Libraries.coroutines)
    implementation(Libraries.coroutinesCore)
    implementation(Libraries.timber)

    kapt(Libraries.kapt_dagger_compiler)
    kapt(Libraries.kapt_dagger_processor)
    implementation(Libraries.dagger)
    implementation(Libraries.dagger_android)
    implementation(Libraries.dagger_android_support)

    implementation(Libraries.gson)
    implementation(Libraries.retrofit)
    implementation(Libraries.gson_converter)
    implementation(Libraries.okhttp3)
    implementation(Libraries.okhttp3_logging_interceptor)

    implementation(Libraries.glide)
    kapt(Libraries.glide_kapt)

    testImplementation(TestLibraries.junit_android)

    testImplementation(TestLibraries.mockwebserver)
    testImplementation(TestLibraries.coreTesting)
    testImplementation(TestLibraries.mockito)
    testImplementation(TestLibraries.coroutines_test)
    testImplementation(TestLibraries.room)
    testImplementation(TestLibraries.robolectric)
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espresso)
    androidTestImplementation(TestLibraries.junit_android_ktx)
    androidTestImplementation(TestLibraries.junit4)
}