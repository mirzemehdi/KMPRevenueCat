package com.mmk.kmprevenuecat.purchases

import cocoapods.RevenueCat.RCCustomerInfo
import cocoapods.RevenueCat.RCPurchases
import cocoapods.RevenueCat.configureWithAPIKey
import cocoapods.RevenueCat.enableAdServicesAttributionTokenCollection
import com.mmk.kmprevenuecat.purchases.data.CustomerInfo
import com.mmk.kmprevenuecat.purchases.data.LogInResult
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSError


@OptIn(ExperimentalForeignApi::class)
internal class PurchasesImpl : Purchases {
    override var logLevel: LogLevel
        get() = RCPurchases.logLevel().asLogLevel()
        set(value) {
            RCPurchases.setLogLevel(value.asRevenueCatLogLevel())
        }

    override fun configure(apiKey: String, appUserId: String?) {
        RCPurchases.configureWithAPIKey(apiKey, appUserId)
    }

    @OptIn(KMPRevenueCatInternalApi::class)
    override fun login(appUserId: String, onResult: (Result<LogInResult>) -> Unit) {
        RCPurchases.sharedPurchases()
            .logIn(appUserId, completionHandler = { rcCustomerInfo, created, nsError ->
                if (rcCustomerInfo != null) onResult(
                    Result.success(
                        LogInResult(
                            customerInfo = rcCustomerInfo.asCustomerInfo(),
                            created = created
                        )
                    )
                )
                else
                    onResult(Result.failure(Exception(nsError?.localizedFailureReason)))
            })
    }


    override fun logOut(onResult: (Result<CustomerInfo>) -> Unit) {
        RCPurchases.sharedPurchases().logOutWithCompletion(onCompletionHandler(onResult))
    }

    override fun getCustomerInfo(
        fetchPolicy: CacheFetchPolicy,
        onResult: (Result<CustomerInfo>) -> Unit
    ) {
        RCPurchases.sharedPurchases().getCustomerInfoWithFetchPolicy(
            fetchPolicy = fetchPolicy.asRevenueCatCacheFetchPolicy(),
            completion = onCompletionHandler(onResult)
        )
    }

    override fun setAttributes(attributes: Map<String, String?>) {
        val map = attributes.map { (key, value) ->
            key as Any? to value as Any?
        }.toMap()
        RCPurchases.sharedPurchases().setAttributes(map)
    }

    override fun setFirebaseAppInstanceID(firebaseAppInstanceID: String) {
        RCPurchases.sharedPurchases().setFirebaseAppInstanceID(firebaseAppInstanceID)
    }

    override fun collectDeviceIdentifiers() {
        RCPurchases.sharedPurchases().collectDeviceIdentifiers()
    }

    override fun enableAdServicesAttributionTokenCollection() {
        RCPurchases.sharedPurchases().attribution().enableAdServicesAttributionTokenCollection()
    }

    override fun syncPurchases(onResult: (Result<CustomerInfo>) -> Unit) {
        RCPurchases.sharedPurchases()
            .syncPurchasesWithCompletion(completion = onCompletionHandler(onResult))
    }

    @OptIn(KMPRevenueCatInternalApi::class)
    private fun onCompletionHandler(onResult: (Result<CustomerInfo>) -> Unit): ((RCCustomerInfo?, NSError?) -> Unit) {
        return { rcCustomerInfo, nsError ->
            if (rcCustomerInfo != null) onResult(Result.success(rcCustomerInfo.asCustomerInfo()))
            else onResult(Result.failure(Exception(nsError?.localizedFailureReason)))
        }
    }

}