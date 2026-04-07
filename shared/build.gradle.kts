import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            // Compose
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation("org.jetbrains.compose.material:material-icons-core:1.7.3")

            // Coroutines
            implementation(libs.kotlinx.coroutines.core)
            implementation("app.cash.sqldelight:coroutines-extensions:2.0.2")

            // Koin
            // implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            // Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)

            // Serialization
            implementation(libs.kotlinx.serialization.json)

            // UI Tooling (✅ INI YANG DIPERBAIKI)
            implementation(compose.components.uiToolingPreview)

            // SqlDelight
            implementation("app.cash.sqldelight:runtime:2.0.2")

            implementation("org.jetbrains.androidx.navigation:navigation-compose:2.9.2")
        }

        androidMain.dependencies {
            // Di androidMain, menggunakan versi libs (AndroidX) tidak masalah
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.compose.ui)
            implementation(libs.compose.uiTooling)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.okhttp)
            implementation("app.cash.sqldelight:android-driver:2.0.2")
            implementation(libs.koin.android)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation("app.cash.sqldelight:native-driver:2.0.2")
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "org.gunwndev.financetracker.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    sourceSets["main"].res.srcDirs("src/commonMain/resources", "src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("org.gunwndev.financetracker.db")
            dialect("app.cash.sqldelight:sqlite-3-38-dialect:2.0.2")
        }
    }
}