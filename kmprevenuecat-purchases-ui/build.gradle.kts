plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinNativeCocoaPods)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    explicitApi()
    androidTarget {
        publishAllLibraryVariants()
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
        ios.deploymentTarget = "15.0"
        framework {
            baseName = "KMPRevenueCatPurchasesUI"
            isStatic = true
        }

        pod("RevenueCat"){
            version=libs.versions.revenueCatIos.get()
            extraOpts += listOf("-compiler-option", "-fmodules")
        }
        pod("RevenueCatUI"){
            version=libs.versions.revenueCatIos.get()
            extraOpts += listOf("-compiler-option", "-fmodules")
        }
    }



    sourceSets {

        androidMain.dependencies {
            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.activity.ktx)
            implementation(libs.revenuecat.purchases)
            implementation(libs.revenuecat.purchases.ui)
        }
        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(project(":kmprevenuecat-purchases"))
        }
        iosMain.dependencies {
        }
    }
}

android {
    namespace = "com.mmk.kmprevenuecat.purchases.ui"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

