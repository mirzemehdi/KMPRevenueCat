package com.mmk.kmprevenuecat.purchases

import android.content.Context
import com.mmk.kmprevenuecat.purchases.data.CustomerInfo
import com.mmk.kmprevenuecat.purchases.data.LogInResult
import com.revenuecat.purchases.CustomerInfo as RevenueCatCustomerInfo
import com.revenuecat.purchases.PurchasesConfiguration
import com.revenuecat.purchases.PurchasesError
import com.revenuecat.purchases.PurchasesException
import com.revenuecat.purchases.awaitCustomerInfo
import com.revenuecat.purchases.getCustomerInfoWith
import com.revenuecat.purchases.interfaces.ReceiveCustomerInfoCallback
import java.util.jar.Attributes
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
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

    @OptIn(KMPRevenueCatInternalApi::class)
    override fun getCustomerInfo(
        fetchPolicy: CacheFetchPolicy,
        onResult: (Result<CustomerInfo>) -> Unit
    ) {
        RevenueCatPurchases.sharedInstance.getCustomerInfo(fetchPolicy = fetchPolicy.asRevenueCatCacheFetchPolicy(),
            object : ReceiveCustomerInfoCallback {
                override fun onError(error: PurchasesError) {
                    onResult(Result.failure(Exception(error.message)))
                }

                override fun onReceived(customerInfo: RevenueCatCustomerInfo) {
                    onResult(Result.success(customerInfo.asCustomerInfo()))
                }

            })
    }

    override fun setAttributes(attributes: Map<String,String?>){
        RevenueCatPurchases.sharedInstance.setAttributes(attributes)
    }

    override fun setFirebaseAppInstanceID(firebaseAppInstanceID: String) {
        RevenueCatPurchases.sharedInstance.setFirebaseAppInstanceID(firebaseAppInstanceID)
    }

}