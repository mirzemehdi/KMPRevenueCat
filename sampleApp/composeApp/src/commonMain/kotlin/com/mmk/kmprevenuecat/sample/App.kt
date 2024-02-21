package com.mmk.kmprevenuecat.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mmk.kmprevenuecat.purchases.data.CustomerInfo
import com.mmk.kmprevenuecat.purchases.ui.Paywall
import com.mmk.kmprevenuecat.purchases.ui.PaywallListener

@Composable
fun App() {

    MaterialTheme {
        Column(
            Modifier.fillMaxSize().padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            Text(
                text = "Hello KMPWorld",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Start,
            )

            Paywall(shouldDisplayDismissButton = true,onDismiss = {
                                println("Ondismissed")
            },object :PaywallListener{
                override fun onPurchaseStarted() {
                    super.onPurchaseStarted()
                    println("onPurchaseStarted")
                }

                override fun onPurchaseCompleted(customerInfo: CustomerInfo?) {
                    super.onPurchaseCompleted(customerInfo)
                    println("onPurchaseCompleted, $customerInfo")

                }

                override fun onPurchaseError(error: String?) {
                    super.onPurchaseError(error)
                    println("onPurchaseError: $error")

                }

                override fun onPurchaseCancelled() {
                    super.onPurchaseCancelled()
                    println("onPurchaseCancelled")

                }

                override fun onRestoreStarted() {
                    super.onRestoreStarted()
                    println("onRestoreStarted")

                }

                override fun onRestoreCompleted(customerInfo: CustomerInfo?) {
                    super.onRestoreCompleted(customerInfo)
                    println("onRestoreCompleted, $customerInfo")

                }

                override fun onRestoreError(error: String?) {
                    super.onRestoreError(error)
                    println("onRestoreError: $error")

                }
            })
        }
    }
}