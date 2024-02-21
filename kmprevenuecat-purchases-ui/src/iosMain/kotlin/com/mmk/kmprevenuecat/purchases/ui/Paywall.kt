package com.mmk.kmprevenuecat.purchases.ui

import androidx.compose.runtime.Composable
import cocoapods.RevenueCatUI.RCPaywallViewController
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIApplication


@OptIn(ExperimentalForeignApi::class)
@Composable
public actual fun Paywall(
    shouldDisplayDismissButton: Boolean,
    onDismiss: () -> Unit, listener: PaywallListener?
) {

    val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController
    val controller = RCPaywallViewController(null, shouldDisplayDismissButton)
    controller.setDelegate(listener?.asRCPaywallViewControllerDelegate(onDismiss))
    if (controller.isBeingPresented().not())
        rootViewController?.presentViewController(controller, true, null)

}