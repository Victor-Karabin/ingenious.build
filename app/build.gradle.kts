plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt") // should be last: 'The following options were not recognized by any processor'
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

android {
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    namespace = "app"

    defaultConfig {
        applicationId = "com.ingenious"

        versionCode = 1
        versionName = "1.0"

        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.tagetSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables { useSupportLibrary = true }

        buildConfigField("String", "BACKEND_BASE_URL", "\"${property("GITHUB_BASE_URL")}\"")
        buildConfigField("String", "BACKEND_API_VERSION", "\"${property("GITHUB_API_VERSION")}\"")
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

    packaging {
        resources {
            excludes.add("META-INF/AL2.0")
            excludes.add("META-INF/LGPL2.1")
            excludes.add("META-INF/INDEX.LIST")
            excludes.add("META-INF/DEPENDENCIES")
            excludes.add("META-INF/LICENSE")
            excludes.add("META-INF/LICENSE.txt")
            excludes.add("META-INF/LICENSE.md")
            excludes.add("META-INF/license.txt")
            excludes.add("META-INF/NOTICE")
            excludes.add("META-INF/NOTICE.txt")
            excludes.add("META-INF/NOTICE.md")
            excludes.add("META-INF/notice.txt")
            excludes.add("META-INF/*.kotlin_module")
        }
    }
}

dependencies {
    implementation(projects.presentation)
    implementation(projects.interactors)
    implementation(projects.domain)
    implementation(projects.boundary)
    implementation(projects.data)
    implementation(projects.common)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.core)

    debugImplementation(libs.leak.canary)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    testImplementation(libs.coroutines.test)
    testImplementation(libs.bundles.unit.tests)
    androidTestImplementation(libs.bundles.android.tests)
}