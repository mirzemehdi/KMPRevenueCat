package com.mmk.kmprevenuecat.purchases.ui

import androidx.compose.runtime.Composable
import com.revenuecat.purchases.ui.revenuecatui.ExperimentalPreviewRevenueCatUIPurchasesAPI
import com.revenuecat.purchases.ui.revenuecatui.PaywallOptions
import com.revenuecat.purchases.ui.revenuecatui.Paywall as RevenueCatPaywall


@OptIn(ExperimentalPreviewRevenueCatUIPurchasesAPI::class)
@Composable
public actual fun Paywall(onDismiss: () -> Unit, listener: PaywallListener?) {
    RevenueCatPaywall(
        options = PaywallOptions.Builder(
            dismissRequest = onDismiss
        )
            .setListener(listener?.asRevenueCatPaywallListener())
            .build()
    )
}