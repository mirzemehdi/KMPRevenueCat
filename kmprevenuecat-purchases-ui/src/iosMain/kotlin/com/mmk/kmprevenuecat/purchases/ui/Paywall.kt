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
        dismissRequestedHandler = null
    ).apply {
        updateWithDisplayCloseButton(shouldDisplayDismissButton)
        setDelegate(listener?.asRCPaywallViewControllerDelegate(onDismiss))
    }.also {
        if (it.isBeingPresented().not())
            rootViewController?.presentViewController(it, true, completion = {
                if (it.isBeingPresented().not()) onDismiss()
            })
    }
}