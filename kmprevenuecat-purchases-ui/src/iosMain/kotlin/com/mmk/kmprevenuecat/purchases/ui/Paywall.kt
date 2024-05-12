package com.mmk.kmprevenuecat.purchases.ui

import androidx.compose.runtime.Composable
import cocoapods.RevenueCatUI.RCPaywallViewController
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIApplication


@OptIn(ExperimentalForeignApi::class)
@Composable
public actual fun Paywall(
    shouldDisplayDismissButton: Boolean,
    onDismiss: () -> Unit,
    listener: PaywallListener?
) {
    val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController

    RCPaywallViewController(
        offering = null,
        displayCloseButton = shouldDisplayDismissButton,
        dismissRequestedHandler = null,
        shouldBlockTouchEvents = false
    ).apply {
        setDelegate(listener?.asRCPaywallViewControllerDelegate(onDismiss))
    }.also {
        if (it.isBeingPresented().not())
            rootViewController?.presentViewController(it, true, completion = {
                println("Paywall PresentViewController completion is called")
            })
    }
}