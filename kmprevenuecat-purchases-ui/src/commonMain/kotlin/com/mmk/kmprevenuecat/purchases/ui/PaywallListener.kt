package com.mmk.kmprevenuecat.purchases.ui

import com.mmk.kmprevenuecat.purchases.data.CustomerInfo


public interface PaywallListener {
    public fun onPurchaseStarted() {}
    public fun onPurchaseCompleted(customerInfo: CustomerInfo?) {}
    public fun onPurchaseError(error: String?) {}
    public fun onPurchaseCancelled() {}
    public fun onRestoreStarted() {}
    public fun onRestoreCompleted(customerInfo: CustomerInfo?) {}
    public fun onRestoreError(error: String?) {}
}