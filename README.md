# KMPRevenueCat - Kotlin Multiplatform RevenueCat Library
[![Build](https://github.com/mirzemehdi/KMPRevenueCat/actions/workflows/build_and_publish.yml/badge.svg)](https://github.com/mirzemehdi/KMPRevenueCat/actions/workflows/build_and_publish.yml)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.21-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.mirzemehdi/kmprevenuecat-purchases?color=blue)](https://search.maven.org/search?q=g:io.github.mirzemehdi+kmprevenuecat)

![badge-android](http://img.shields.io/badge/platform-android-6EDB8D.svg?style=flat)
![badge-ios](http://img.shields.io/badge/platform-ios-CDCDCD.svg?style=flat)


KMPRevenueCat is an _**unofficial**_ Kotlin Multiplatform library designed as a wrapper for [RevenueCat](https://www.revenuecat.com/). It provides a unified API for managing subscription and in-app purchases across both iOS and Android platforms. You can see how this library is used in [FindTravelNow](https://github.com/mirzemehdi/FindTravelNow-KMM/) production Compose + KMP project.
For the documentation, please refer to the official [RevenueCat Documentation](https://www.revenuecat.com/docs/getting-started/quickstart), Kotlin section. The library is designed in the same way as it is shown in the official documentation.

Related Blog Post (if you want to integrate it yourself without this library): https://medium.com/@mirzemehdi/integrating-revenuecat-into-kotlin-multiplatform-465ffa47a97b   

## Installation

### Minimum Requirements

- **Android:** `minSdkVersion 24`
- **iOS:** `minDeploymentTarget 15.0`  

- **RevenueCat Android version:** `7.5.2`  
- **RevenueCat iOS version:** `4.39.0`  

### Gradle Setup
KMPRevenueCat is available on Maven Central. In your root project `build.gradle.kts` file (or `settings.gradle` file) add `mavenCentral()` to repositories.  

**_You will also need to include RevenueCat library to your ios app from XCode using Swift Package Manager or Cocoapods._**   


```kotlin
repositories { 
  mavenCentral()
}
```

Then in your shared module add desired dependencies in `commonMain`. Latest version: [![Maven Central](https://img.shields.io/maven-central/v/io.github.mirzemehdi/kmprevenuecat-purchases?color=blue)](https://search.maven.org/search?q=g:io.github.mirzemehdi+kmprevenuecat).
```kotlin
sourceSets {
  commonMain.dependencies {
    implementation("io.github.mirzemehdi:kmprevenuecat-purchases:<version>") //RevenueCat Purchases
    implementation("io.github.mirzemehdi:kmprevenuecat-purchases-ui:<version>") //RevenueCat Purchases UI
  }
}
```

## Available Functions

### Purchases
```kotlin
var logLevel: LogLevel
fun configure(apiKey: String, appUserId: String? = null)
fun login(appUserId: String, onResult: (Result<LogInResult>) -> Unit)
fun logOut(onResult: (Result<CustomerInfo>) -> Unit)
fun getCustomerInfo(fetchPolicy: CacheFetchPolicy = CacheFetchPolicy.default(),onResult: (Result<CustomerInfo>) -> Unit)
fun setAttributes(attributes: Map<String,String?>)
fun setFirebaseAppInstanceID(firebaseAppInstanceID: String)
fun collectDeviceIdentifiers()
fun enableAdServicesAttributionTokenCollection()

```
### Purchases-UI

```kotlin
//This Composable can be used in Compose Multiplatform

@Composable
fun Paywall(shouldDisplayDismissButton: Boolean = true,onDismiss: () -> Unit,listener: PaywallListener?)
```


## Contributing

Contributions are welcome! When covering new class/functions, please, follow RevenueCat's Kotlin style, maintaining the same naming conventions for functions and classes with identical parameters. Provide clear commits and open issues for problems or suggestions on the [Issues](https://github.com/mirzemehdi/KMPRevenueCat/issues) page. Your help makes this project better – thanks!



