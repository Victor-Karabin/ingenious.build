plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("java-test-fixtures")
}

java {
    sourceCompatibility = JavaVersion.VERSION_19
    targetCompatibility = JavaVersion.VERSION_19
}

dependencies {
    implementation(libs.inject)

    testFixturesImplementation(libs.bundles.unit.tests)
    testFixturesImplementation(libs.coroutines.test)
}