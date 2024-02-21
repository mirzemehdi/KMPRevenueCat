package com.mmk.kmprevenuecat.purchases.ui

import com.mmk.kmprevenuecat.purchases.KMPRevenueCatInternalApi
import com.mmk.kmprevenuecat.purchases.asCustomerInfo
import com.revenuecat.purchases.CustomerInfo
import com.revenuecat.purchases.Package
import com.revenuecat.purchases.PurchasesError
import com.revenuecat.purchases.models.StoreTransaction
import com.revenuecat.purchases.ui.revenuecatui.ExperimentalPreviewRevenueCatUIPurchasesAPI
import com.revenuecat.purchases.ui.revenuecatui.PaywallListener as RevenueCatPaywallListener


@OptIn(ExperimentalPreviewRevenueCatUIPurchasesAPI::class, KMPRevenueCatInternalApi::class)
internal fun PaywallListener.asRevenueCatPaywallListener(): RevenueCatPaywallListener {
    return object:RevenueCatPaywallListener{
        override fun onPurchaseCancelled() {
            this@asRevenueCatPaywallListener.onPurchaseCancelled()
        }

        override fun onPurchaseCompleted(
            customerInfo: CustomerInfo,
            storeTransaction: StoreTransaction
        ) {
            this@asRevenueCatPaywallListener.onPurchaseCompleted(customerInfo.asCustomerInfo())

        }

        override fun onPurchaseError(error: PurchasesError) {
            this@asRevenueCatPaywallListener.onPurchaseError(error.message)
        }

        override fun onPurchaseStarted(rcPackage: Package) {
            this@asRevenueCatPaywallListener.onPurchaseStarted()
        }

        override fun onRestoreCompleted(customerInfo: CustomerInfo) {
            this@asRevenueCatPaywallListener.onRestoreCompleted(customerInfo.asCustomerInfo())
        }

        override fun onRestoreError(error: PurchasesError) {
            this@asRevenueCatPaywallListener.onRestoreError(error.message)
        }

        override fun onRestoreStarted() {
            this@asRevenueCatPaywallListener.onRestoreStarted()
        }
    }
}
