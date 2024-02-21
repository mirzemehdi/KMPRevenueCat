package com.mmk.kmprevenuecat.purchases

import com.mmk.kmprevenuecat.purchases.data.CustomerInfo
import com.revenuecat.purchases.LogLevel as RevenueCatLogLevel
import com.revenuecat.purchases.CustomerInfo as RevenueCatCustomerInfo


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

internal fun RevenueCatCustomerInfo.asCustomerInfo(): CustomerInfo {
    return CustomerInfo(
        originalAppUserId = this.originalAppUserId
    )
}