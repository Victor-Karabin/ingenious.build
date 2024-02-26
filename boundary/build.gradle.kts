plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("java-test-fixtures")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.datetime)
}