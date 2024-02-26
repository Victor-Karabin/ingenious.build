plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt") // should be last: 'The following options were not recognized by any processor'

}

android {
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    namespace = "presentation"

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()

        renderscriptTargetApi = libs.versions.android.tagetSdk.get().toInt()
        renderscriptSupportModeEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }
}

dependencies {
    implementation(projects.interactors)
    implementation(projects.common)

    implementation(libs.kotlinx.datetime)

    implementation(libs.google.material)
    implementation(libs.coil)

    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.preview)

    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.compose.utils)
    implementation(libs.androidx.paging.compose)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    testImplementation(testFixtures(projects.common))
    testImplementation(libs.bundles.unit.tests)
    testImplementation(libs.coroutines.test)
}