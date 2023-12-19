@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.daggerHilt)
    alias(libs.plugins.protobuf)
}

android {
    namespace = "com.antoninovitale.beerpedia.data"
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

    implementation(libs.retrofit.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.gson)
    implementation(libs.daggerHiltAndroid)
    implementation(libs.androidx.datastore)
    implementation(libs.protobuf.javalite)

    kapt(libs.daggerHiltCompiler)

    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.kotlin.test)
    testImplementation(project(":common-tests"))
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${libs.versions.protobuf.javalite.get()}"
    }

    // Generates the java Protobuf-lite code for the Protobufs in this project. See
    // https://github.com/google/protobuf-gradle-plugin#customizing-protobuf-compilation
    // for more information.
    // Generates the java Protobuf-lite code for the Protobufs in this project. See
    // https://github.com/google/protobuf-gradle-plugin#customizing-protobuf-compilation
    // for more information.
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
            }
        }
    }
}
