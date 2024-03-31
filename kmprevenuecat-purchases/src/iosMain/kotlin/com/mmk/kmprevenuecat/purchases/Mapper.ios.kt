package com.mmk.kmprevenuecat.purchases

import cocoapods.RevenueCat.RCCacheFetchPolicy
import cocoapods.RevenueCat.RCCacheFetchPolicyCachedOrFetched
import cocoapods.RevenueCat.RCCacheFetchPolicyFetchCurrent
import cocoapods.RevenueCat.RCCacheFetchPolicyFromCacheOnly
import cocoapods.RevenueCat.RCCacheFetchPolicyNotStaleCachedOrFetched
import cocoapods.RevenueCat.RCCustomerInfo
import cocoapods.RevenueCat.RCEntitlementInfo
import cocoapods.RevenueCat.RCEntitlementInfos
import cocoapods.RevenueCat.RCLogLevelDebug
import cocoapods.RevenueCat.RCLogLevelError
import cocoapods.RevenueCat.RCLogLevelInfo
import cocoapods.RevenueCat.RCLogLevelVerbose
import cocoapods.RevenueCat.RCLogLevelWarn
import com.mmk.kmprevenuecat.purchases.data.CustomerInfo
import com.mmk.kmprevenuecat.purchases.data.EntitlementInfo
import com.mmk.kmprevenuecat.purchases.data.EntitlementInfos
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.timeIntervalSince1970


@OptIn(ExperimentalForeignApi::class)
internal fun LogLevel.asRevenueCatLogLevel(): Long {
    return when (this) {
        LogLevel.VERBOSE -> RCLogLevelVerbose
        LogLevel.DEBUG -> RCLogLevelDebug
        LogLevel.INFO -> RCLogLevelInfo
        LogLevel.WARN -> RCLogLevelWarn
        LogLevel.ERROR -> RCLogLevelError
    }

}

@OptIn(ExperimentalForeignApi::class)
internal fun Long.asLogLevel(): LogLevel {
    return when (this) {
        RCLogLevelVerbose -> LogLevel.VERBOSE
        RCLogLevelDebug -> LogLevel.DEBUG
        RCLogLevelInfo -> LogLevel.INFO
        RCLogLevelWarn -> LogLevel.WARN
        RCLogLevelError -> LogLevel.ERROR
        else -> LogLevel.DEBUG
    }
}

@OptIn(ExperimentalForeignApi::class)
internal fun RCCacheFetchPolicy.asCacheFetchPolicy(): CacheFetchPolicy {
    return when (this) {
        RCCacheFetchPolicyFromCacheOnly -> CacheFetchPolicy.CACHE_ONLY
        RCCacheFetchPolicyFetchCurrent -> CacheFetchPolicy.FETCH_CURRENT
        RCCacheFetchPolicyNotStaleCachedOrFetched -> CacheFetchPolicy.NOT_STALE_CACHED_OR_CURRENT
        RCCacheFetchPolicyCachedOrFetched -> CacheFetchPolicy.CACHED_OR_FETCHED
        else -> CacheFetchPolicy.CACHED_OR_FETCHED
    }
}

@OptIn(ExperimentalForeignApi::class)
internal fun CacheFetchPolicy.asRevenueCatCacheFetchPolicy(): RCCacheFetchPolicy {
    return when (this) {
        CacheFetchPolicy.CACHE_ONLY -> RCCacheFetchPolicyFromCacheOnly
        CacheFetchPolicy.FETCH_CURRENT -> RCCacheFetchPolicyFetchCurrent
        CacheFetchPolicy.NOT_STALE_CACHED_OR_CURRENT -> RCCacheFetchPolicyNotStaleCachedOrFetched
        CacheFetchPolicy.CACHED_OR_FETCHED -> RCCacheFetchPolicyCachedOrFetched
    }
}

@OptIn(ExperimentalForeignApi::class)
internal fun RCEntitlementInfos.asEntitlementInfos(): EntitlementInfos {
    val entitlementInfos: Map<String, EntitlementInfo> = this.all().filter { entry ->
        entry.key is String && entry.value is RCEntitlementInfo
    }.map { entry ->
        val key = entry.key as String
        val value = entry.value as RCEntitlementInfo
        key to value.asEntitlementInfo()
    }.toMap()

    return EntitlementInfos(all = entitlementInfos)
}

@OptIn(ExperimentalForeignApi::class)
internal fun RCEntitlementInfo.asEntitlementInfo(): EntitlementInfo {
    return EntitlementInfo(
        identifier = this.identifier(),
        isActive = this.isActive(),
        willRenew = this.willRenew(),
        latestPurchaseDate = this.latestPurchaseDate()?.timeIntervalSince1970?.toLong() ?: 0L,
        originalPurchaseDate = this.originalPurchaseDate()?.timeIntervalSince1970?.toLong() ?: 0L,
        expirationDate = this.expirationDate()?.timeIntervalSince1970?.toLong() ?: 0L,
        productIdentifier = this.productIdentifier(),
        productPlanIdentifier = this.productPlanIdentifier(),
        isSandbox = this.isSandbox(),
        unsubscribeDetectedAt = this.unsubscribeDetectedAt()?.timeIntervalSince1970?.toLong() ?: 0L,
        billingIssueDetectedAt = this.billingIssueDetectedAt()?.timeIntervalSince1970?.toLong()
            ?: 0L
    )
}

@OptIn(ExperimentalForeignApi::class)
public fun RCCustomerInfo.asCustomerInfo(): CustomerInfo {
    return CustomerInfo(
        originalAppUserId = originalAppUserId(),
        entitlements = entitlements().asEntitlementInfos(),
        managementURL = managementURL()?.absoluteString
    )
}

