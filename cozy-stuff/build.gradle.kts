import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    `maven-publish`
}

kotlin {
    android {
        namespace = "com.beslimir.cozy_stuff"
        compileSdk = 36
        minSdk = 26
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.runtime)
            implementation(libs.foundation)
            implementation(libs.material3)
            implementation(libs.ui)
            implementation(libs.material.icons.extended)
            implementation(libs.ui.tooling.preview)
        }
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
        }
    }
}

dependencies {
    "androidRuntimeClasspath"(libs.ui.tooling)
}

group = "com.beslimir"
version = "2.0.0"

afterEvaluate {
    publishing {
        publications.withType<MavenPublication> {
            groupId = "com.beslimir"
            artifactId = "cozy-stuff${if (name != "kotlinMultiplatform") "-$name" else ""}"
        }
    }
}
