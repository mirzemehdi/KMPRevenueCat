package com.mmk.kmprevenuecat.purchases.ui

import cocoapods.RevenueCatUI.RCPaywallViewController
import cocoapods.RevenueCatUI.RCPaywallViewControllerDelegateProtocol
import kotlinx.cinterop.ExperimentalForeignApi
import objcnames.classes.RCCustomerInfo
import objcnames.classes.RCStoreTransaction
import platform.Foundation.NSError
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
@Suppress("CONFLICTING_OVERLOADS")
internal fun PaywallListener.asRCPaywallViewControllerDelegate(onDismissed: () -> Unit): RCPaywallViewControllerDelegateProtocol {
    return object : RCPaywallViewControllerDelegateProtocol, NSObject() {
        override fun paywallViewController(
            controller: RCPaywallViewController,
            didFailPurchasingWithError: NSError
        ) {
            this@asRCPaywallViewControllerDelegate.onPurchaseError(didFailPurchasingWithError.localizedFailureReason)
        }


        override fun paywallViewController(
            controller: RCPaywallViewController,
            didFailRestoringWithError: NSError
        ) {
            this@asRCPaywallViewControllerDelegate.onRestoreError(didFailRestoringWithError.localizedFailureReason)
        }

        override fun paywallViewController(
            controller: RCPaywallViewController,
            didFinishRestoringWithCustomerInfo: RCCustomerInfo
        ) {
            //TODO FIX this
            this@asRCPaywallViewControllerDelegate.onRestoreCompleted(
                null
//                didFinishRestoringWithCustomerInfo.asCustomerInfo()
            )
        }

        override fun paywallViewController(
            controller: RCPaywallViewController,
            didFinishPurchasingWithCustomerInfo: RCCustomerInfo
        ) {
            didFinishPurchasingWithCustomerInfo
            //TODO FIX this
            this@asRCPaywallViewControllerDelegate.onPurchaseCompleted(
                null
//                didFinishPurchasingWithCustomerInfo.asCustomerInfo()
            )

        }

        override fun paywallViewController(
            controller: RCPaywallViewController,
            didFinishPurchasingWithCustomerInfo: RCCustomerInfo,
            transaction: RCStoreTransaction?
        ) {
            //TODO FIX this
            this@asRCPaywallViewControllerDelegate.onPurchaseCompleted(
                null
//                didFinishPurchasingWithCustomerInfo.asCustomerInfo()
            )
        }

        override fun paywallViewControllerDidCancelPurchase(controller: RCPaywallViewController) {
            this@asRCPaywallViewControllerDelegate.onPurchaseCancelled()
        }

        override fun paywallViewControllerDidStartPurchase(controller: RCPaywallViewController) {
            this@asRCPaywallViewControllerDelegate.onPurchaseStarted()
        }

        override fun paywallViewControllerWasDismissed(controller: RCPaywallViewController) {
            onDismissed()
        }
    }

}
