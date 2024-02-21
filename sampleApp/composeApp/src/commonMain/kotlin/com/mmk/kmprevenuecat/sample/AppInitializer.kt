package com.mmk.kmprevenuecat.sample

import com.mmk.kmprevenuecat.purchases.LogLevel
import com.mmk.kmprevenuecat.purchases.Purchases


object AppInitializer {
    fun onApplicationStart() {
        onApplicationStartPlatformSpecific()
        Purchases.logLevel = LogLevel.DEBUG
        Purchases.configure("goog_EgunaTyXgbBGvtLUxYdVwddiGRC")
    }
}