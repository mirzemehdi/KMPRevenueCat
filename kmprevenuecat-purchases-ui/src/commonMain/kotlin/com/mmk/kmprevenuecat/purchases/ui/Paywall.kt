package com.mmk.kmprevenuecat.purchases.ui

import androidx.compose.runtime.Composable


@Composable
public expect fun Paywall(
    shouldDisplayDismissButton: Boolean = true,
    onDismiss: () -> Unit,
    listener: PaywallListener?
)