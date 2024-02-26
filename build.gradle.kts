// Top-level build file where you can add configuration options common to all sub-projects/modules.
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.google.dagger.hilt.android") version libs.versions.hilt.get() apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.gradle.tools)
        classpath(libs.kotlin.gradle)
    }
}

val testWithReport = tasks.register<TestReport>("testWithReport") {
    destinationDirectory = file("$buildDir/reports/tests")
    //for kotlin modules
    testResults.from(subprojects.mapNotNull { project ->
        project.tasks.findByPath("test")
    })
    //for android debug modules
    testResults.from(subprojects.mapNotNull { project ->
        project.tasks.findByPath("testDebugUnitTest")
    })
}

subprojects {
    // reports for tests
    tasks.withType<Test> {
        useJUnit()
        testLogging {
            events("passed", "skipped", "failed")
        }
        // disable default ones. @use testWithReport
        reports.html.required.set(false)
    }

    // reports and metrics for jetpack compose
    // @see https://github.com/androidx/androidx/blob/androidx-main/compose/compiler/design/compiler-metrics.md#enabling-metrics
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            if (project.findProperty("composeCompilerReports") == "true") {
                freeCompilerArgs += listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${project.buildDir.absolutePath}/compose_compiler"
                )
            }
            if (project.findProperty("composeCompilerMetrics") == "true") {
                freeCompilerArgs += listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=${project.buildDir.absolutePath}/compose_compiler"
                )
            }
        }
    }

    // solve 'compileDebugJavaWithJavac' and 'compileDebugKotlin' compatibility
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = libs.versions.jvmTarget.get()
        }
    }

    tasks.withType<JavaCompile>().configureEach {
        targetCompatibility = JavaVersion.VERSION_11.toString()
        sourceCompatibility = JavaVersion.VERSION_11.toString()
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}