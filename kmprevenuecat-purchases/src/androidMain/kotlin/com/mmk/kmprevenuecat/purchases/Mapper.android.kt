package com.mmk.kmprevenuecat.purchases

import com.revenuecat.purchases.LogLevel as RevenueCatLogLevel


internal fun LogLevel.asRevenueCatLogLevel():RevenueCatLogLevel{
    return when(this){
        LogLevel.VERBOSE -> RevenueCatLogLevel.VERBOSE
        LogLevel.DEBUG -> RevenueCatLogLevel.DEBUG
        LogLevel.INFO -> RevenueCatLogLevel.INFO
        LogLevel.WARN -> RevenueCatLogLevel.WARN
        LogLevel.ERROR -> RevenueCatLogLevel.ERROR
    }

}

internal fun RevenueCatLogLevel.asLogLevel():LogLevel{
    return when(this){
        RevenueCatLogLevel.VERBOSE -> LogLevel.VERBOSE
        RevenueCatLogLevel.DEBUG -> LogLevel.DEBUG
        RevenueCatLogLevel.INFO -> LogLevel.INFO
        RevenueCatLogLevel.WARN -> LogLevel.WARN
        RevenueCatLogLevel.ERROR -> LogLevel.ERROR
    }
}