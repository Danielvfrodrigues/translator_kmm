plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.native.cocoapods)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sqldelight)
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        setVersion("1.0")
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        sourceSets {
            val commonMain by getting {
                dependencies {
                    implementation(libs.bundles.ktor)
                    implementation(libs.sqldelight.runtime)
                    implementation(libs.sqldelight.coroutines.extensions)
                    implementation(libs.kotlin.date.time)
                }
            }
            val commonTest by getting {
                dependencies {
                    implementation(libs.kotlin.test)
                    implementation(libs.assertk)
                    implementation(libs.turbine)
                }
            }
            val androidMain by getting {
                dependencies {
                    implementation(libs.ktor.android)
                    implementation(libs.sqldelight.android.driver)
                }
            }
            val iosMain by creating {
                dependencies {
                    implementation(libs.ktor.ios)
                    implementation(libs.sqldelight.native.driver)
                }
            }
        }
    }
}

android {
    namespace = "br.com.codeforge.translator_kmm"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

sqldelight {
    databases {
        create("TranslateDatabase") {
            packageName.set("br.com.codeforge.translator_kmm.database")
        }
    }
}