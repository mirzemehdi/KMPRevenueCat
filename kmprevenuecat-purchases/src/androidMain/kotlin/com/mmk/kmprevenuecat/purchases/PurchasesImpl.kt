package com.mmk.kmprevenuecat.purchases

import android.content.Context
import com.mmk.kmprevenuecat.purchases.data.CustomerInfo
import com.mmk.kmprevenuecat.purchases.data.LogInResult
import com.revenuecat.purchases.CustomerInfo as RevenueCatCustomerInfo
import com.revenuecat.purchases.PurchasesConfiguration
import com.revenuecat.purchases.PurchasesError
import com.revenuecat.purchases.interfaces.ReceiveCustomerInfoCallback
import com.revenuecat.purchases.Purchases as RevenueCatPurchases
import com.revenuecat.purchases.interfaces.LogInCallback as RevenueCatLoginCallback

internal class PurchasesImpl(private val context: Context) : Purchases {
    override var logLevel: LogLevel
        get() = RevenueCatPurchases.logLevel.asLogLevel()
        set(value) {
            RevenueCatPurchases.logLevel = value.asRevenueCatLogLevel()
        }

    override fun configure(apiKey: String) {
        RevenueCatPurchases.configure(PurchasesConfiguration.Builder(context, apiKey).build())
    }

    @OptIn(KMPRevenueCatInternalApi::class)
    override fun login(appUserId: String, onResult: (Result<LogInResult>) -> Unit) {
        RevenueCatPurchases.sharedInstance.logIn(appUserId, object : RevenueCatLoginCallback {
            override fun onError(error: PurchasesError) {
                onResult(Result.failure(Exception(error.message)))
            }

            override fun onReceived(customerInfo: RevenueCatCustomerInfo, created: Boolean) {
                onResult(Result.success(LogInResult(customerInfo.asCustomerInfo(), created)))
            }
        })
    }

    @OptIn(KMPRevenueCatInternalApi::class)
    override fun logOut(onResult: (Result<CustomerInfo>) -> Unit) {
        RevenueCatPurchases.sharedInstance.logOut(object : ReceiveCustomerInfoCallback {
            override fun onError(error: PurchasesError) {
                onResult(Result.failure(Exception(error.message)))
            }

            override fun onReceived(customerInfo: RevenueCatCustomerInfo) {
                onResult(Result.success(customerInfo.asCustomerInfo()))
            }

        })
    }
}