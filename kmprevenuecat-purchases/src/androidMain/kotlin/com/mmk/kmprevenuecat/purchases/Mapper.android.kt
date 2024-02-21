package com.mmk.kmprevenuecat.purchases

import com.mmk.kmprevenuecat.purchases.data.CustomerInfo
import com.revenuecat.purchases.LogLevel as RevenueCatLogLevel
import com.revenuecat.purchases.CustomerInfo as RevenueCatCustomerInfo
import com.revenuecat.purchases.CacheFetchPolicy as RevenueCatCacheFetchPolicy
import com.mmk.kmprevenuecat.purchases.data.EntitlementInfo
import com.mmk.kmprevenuecat.purchases.data.EntitlementInfos
import com.revenuecat.purchases.EntitlementInfos as RevenueCatEntitlementInfos
import com.revenuecat.purchases.EntitlementInfo as RevenueCatEntitlementInfo


internal fun LogLevel.asRevenueCatLogLevel(): RevenueCatLogLevel {
    return when (this) {
        LogLevel.VERBOSE -> RevenueCatLogLevel.VERBOSE
        LogLevel.DEBUG -> RevenueCatLogLevel.DEBUG
        LogLevel.INFO -> RevenueCatLogLevel.INFO
        LogLevel.WARN -> RevenueCatLogLevel.WARN
        LogLevel.ERROR -> RevenueCatLogLevel.ERROR
    }

}

internal fun RevenueCatLogLevel.asLogLevel(): LogLevel {
    return when (this) {
        RevenueCatLogLevel.VERBOSE -> LogLevel.VERBOSE
        RevenueCatLogLevel.DEBUG -> LogLevel.DEBUG
        RevenueCatLogLevel.INFO -> LogLevel.INFO
        RevenueCatLogLevel.WARN -> LogLevel.WARN
        RevenueCatLogLevel.ERROR -> LogLevel.ERROR
    }
}

internal fun RevenueCatCacheFetchPolicy.asCacheFetchPolicy(): CacheFetchPolicy {
    return when (this) {
        RevenueCatCacheFetchPolicy.CACHE_ONLY -> CacheFetchPolicy.CACHE_ONLY
        RevenueCatCacheFetchPolicy.FETCH_CURRENT -> CacheFetchPolicy.FETCH_CURRENT
        RevenueCatCacheFetchPolicy.NOT_STALE_CACHED_OR_CURRENT -> CacheFetchPolicy.NOT_STALE_CACHED_OR_CURRENT
        RevenueCatCacheFetchPolicy.CACHED_OR_FETCHED -> CacheFetchPolicy.CACHED_OR_FETCHED
    }
}

internal fun CacheFetchPolicy.asRevenueCatCacheFetchPolicy(): RevenueCatCacheFetchPolicy {
    return when (this) {
        CacheFetchPolicy.CACHE_ONLY -> RevenueCatCacheFetchPolicy.CACHE_ONLY
        CacheFetchPolicy.FETCH_CURRENT -> RevenueCatCacheFetchPolicy.FETCH_CURRENT
        CacheFetchPolicy.NOT_STALE_CACHED_OR_CURRENT -> RevenueCatCacheFetchPolicy.NOT_STALE_CACHED_OR_CURRENT
        CacheFetchPolicy.CACHED_OR_FETCHED -> RevenueCatCacheFetchPolicy.CACHED_OR_FETCHED
    }
}

internal fun RevenueCatEntitlementInfos.asEntitlementInfos(): EntitlementInfos {
    return EntitlementInfos(all = this.all.mapValues { entry ->
        entry.value.asEntitlementInfo()
    })
}

internal fun RevenueCatEntitlementInfo.asEntitlementInfo(): EntitlementInfo {
    return EntitlementInfo(
        identifier = this.identifier,
        isActive = this.isActive,
        willRenew = this.willRenew,
        latestPurchaseDate = this.latestPurchaseDate.time,
        originalPurchaseDate = this.originalPurchaseDate.time,
        expirationDate = this.expirationDate?.time,
        productIdentifier = this.productIdentifier,
        productPlanIdentifier = this.productPlanIdentifier,
        isSandbox = this.isSandbox,
        unsubscribeDetectedAt = this.unsubscribeDetectedAt?.time,
        billingIssueDetectedAt = this.billingIssueDetectedAt?.time
    )
}

@KMPRevenueCatInternalApi
public fun RevenueCatCustomerInfo.asCustomerInfo(): CustomerInfo {
    return CustomerInfo(
        originalAppUserId = this.originalAppUserId,
        entitlements = entitlements.asEntitlementInfos()
    )
}