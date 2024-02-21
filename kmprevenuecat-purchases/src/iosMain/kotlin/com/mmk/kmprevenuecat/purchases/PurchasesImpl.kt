package com.mmk.kmprevenuecat.purchases

import cocoapods.RevenueCat.RCPurchases
import cocoapods.RevenueCat.configureWithAPIKey
import kotlinx.cinterop.ExperimentalForeignApi


@OptIn(ExperimentalForeignApi::class)
internal class PurchasesImpl : Purchases {
    override var logLevel: LogLevel
        get() = RCPurchases.logLevel().asLogLevel()
        set(value) {
            RCPurchases.setLogLevel(value.asRevenueCatLogLevel())
        }

    override fun configure(apiKey: String) {
        RCPurchases.configureWithAPIKey(apiKey)
    }
}