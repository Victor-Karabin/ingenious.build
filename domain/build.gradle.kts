plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_19
    targetCompatibility = JavaVersion.VERSION_19
}

dependencies {
    implementation(projects.interactors)
    implementation(projects.boundary)
    implementation(libs.kotlinx.datetime)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.inject)
    testImplementation(testFixtures(projects.common))
    testImplementation(libs.junit4)
}