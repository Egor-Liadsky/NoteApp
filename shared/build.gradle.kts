plugins {
    kotlin("multiplatform")
    id("com.squareup.sqldelight")
    id("com.android.library")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // DI
                implementation("io.insert-koin:koin-core:3.3.2")

                //database
                implementation("com.squareup.sqldelight:runtime:1.5.4")
                implementation("com.squareup.sqldelight:coroutines-extensions:1.5.4")
            }
        }
        val androidMain by getting {
            dependencies {
                //database
                implementation("com.squareup.sqldelight:android-driver:1.5.5")
            }
        }
        val iosMain by creating {
            dependsOn(commonMain)
            dependencies {
                //database
                implementation("com.squareup.sqldelight:native-driver:1.5.5")
            }
        }
    }
}

android {
    namespace = "com.lyadsky.noteapp"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}

sqldelight {
    database("NoteDatabase"){
        packageName = "com.lyadsky.database"
        sourceFolders = listOf("sqldelight")
    }
}
