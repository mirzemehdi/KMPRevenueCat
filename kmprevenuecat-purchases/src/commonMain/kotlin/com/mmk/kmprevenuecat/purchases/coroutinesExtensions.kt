package com.mmk.kmprevenuecat.purchases


import com.mmk.kmprevenuecat.purchases.data.CustomerInfo
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @throws [PurchasesException]  if there's an error retrieving the customer info.
 *
 */
public suspend fun Purchases.awaitCustomerInfo(fetchPolicy: CacheFetchPolicy=CacheFetchPolicy.default()): CustomerInfo {
    return suspendCoroutine { continuation ->
        getCustomerInfo(
            fetchPolicy,
            onResult = { result ->
                if (result.isSuccess && result.getOrNull() != null) continuation.resume(result.getOrNull()!!)
                else continuation.resumeWithException(
                    PurchasesException(result.exceptionOrNull()?.message)
                )
            },
        )
    }
}

/**
 * @throws [PurchasesException]  if there's an error retrieving the customer info.
 *
 */
public suspend fun Purchases.awaitSyncPurchases(): CustomerInfo {
    return suspendCoroutine { continuation ->
        syncPurchases(
            onResult = {result ->
                if (result.isSuccess && result.getOrNull() != null) continuation.resume(result.getOrNull()!!)
                else continuation.resumeWithException(PurchasesException(result.exceptionOrNull()?.message))
            },
        )
    }
}
