plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    namespace = "data"

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.boundary)
    implementation(projects.common)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.bundles.retrofit2)

    implementation(libs.kotlinx.datetime)

    implementation(libs.inject)

    testImplementation(testFixtures(projects.common))
    testImplementation(testFixtures(projects.boundary))
    testImplementation(libs.bundles.unit.tests)
    testImplementation(libs.coroutines.test)
}