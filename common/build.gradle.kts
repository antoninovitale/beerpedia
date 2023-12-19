@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinKapt)
}

android {
    namespace = "com.antoninovitale.beerpedia.common"
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
    implementation(libs.daggerHiltAndroid)

    kapt(libs.daggerHiltCompiler)
}
