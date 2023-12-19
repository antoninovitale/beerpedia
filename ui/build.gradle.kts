@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.daggerHilt)
}

android {
    namespace = "com.antoninovitale.beerpedia.ui"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":domain"))
    implementation(libs.daggerHiltAndroid)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.glide.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.hilt.navigation)

    kapt(libs.daggerHiltCompiler)

    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    implementation(libs.compose.foundation)
    implementation(libs.compose.navigation)

    //test
    testImplementation(project(":common-tests"))
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.kotlin.test)
    androidTestImplementation(project(":common-tests"))
    androidTestImplementation(composeBom)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.compose.ui.test.junit4)
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler)
    debugImplementation(libs.compose.ui.test.manifest)
}
