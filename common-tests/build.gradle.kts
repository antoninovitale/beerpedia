@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinKapt)
}

android {
    namespace = "com.antoninovitale.beerpedia.common.tests"
    compileSdk = project.properties["compileSdkVersion"].toString().toInt()
    buildToolsVersion = project.properties["buildToolsVersion"].toString()

    defaultConfig {
        minSdk = project.properties["minSdkVersion"].toString().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    kotlin {
        jvmToolchain(project.properties["javaToolchainVersion"].toString().toInt())
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(libs.daggerHiltAndroid)

    kapt(libs.daggerHiltCompiler)

    api(libs.junit)
    api(libs.mockito.core)
    api(libs.mockito.kotlin)
    api(libs.coroutines.test)
    api(libs.kotlin.test)
    api(libs.turbine)
}
