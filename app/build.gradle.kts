@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.daggerHilt)
}

android {
    namespace = "com.antoninovitale.beerpedia"
    compileSdk = project.properties["compileSdkVersion"].toString().toInt()
    buildToolsVersion = project.properties["buildToolsVersion"].toString()

    defaultConfig {
        applicationId = "com.antoninovitale.beerpedia"
        minSdk = project.properties["minSdkVersion"].toString().toInt()
        targetSdk = project.properties["targetSdkVersion"].toString().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.antoninovitale.beerpedia.CustomTestRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    kotlin {
        jvmToolchain(project.properties["javaToolchainVersion"].toString().toInt())
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    implementation(project(":ui"))
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.daggerHiltAndroid)

    kapt(libs.daggerHiltCompiler)

    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    implementation(libs.compose.ui)
    implementation(libs.compose.activity)
    implementation(libs.compose.foundation)
    implementation(libs.compose.navigation)
    implementation(libs.compose.material3)

    //test
    testImplementation(libs.junit)
    androidTestImplementation(project(":common-tests"))
    androidTestImplementation(project(":domain"))
    androidTestImplementation(composeBom)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.compose.ui.test.junit4)
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.androidx.runner)
    kaptAndroidTest(libs.hilt.android.compiler)
    debugImplementation(libs.compose.ui.test.manifest)
}
