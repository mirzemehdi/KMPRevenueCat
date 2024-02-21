package com.mmk.kmprevenuecat.purchases

import android.content.Context
import com.revenuecat.purchases.PurchasesConfiguration
import com.revenuecat.purchases.Purchases as RevenueCatPurchases

internal class PurchasesImpl(private val context: Context) : Purchases {
    override var logLevel: LogLevel
        get() = RevenueCatPurchases.logLevel.asLogLevel()
        set(value) {
            RevenueCatPurchases.logLevel = value.asRevenueCatLogLevel()
        }

    override fun configure(apiKey: String) {
        RevenueCatPurchases.configure(PurchasesConfiguration.Builder(context, apiKey).build())
    }
}