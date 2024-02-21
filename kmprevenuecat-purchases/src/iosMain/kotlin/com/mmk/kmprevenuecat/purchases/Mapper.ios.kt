package com.mmk.kmprevenuecat.purchases

import cocoapods.RevenueCat.RCLogLevelDebug
import cocoapods.RevenueCat.RCLogLevelError
import cocoapods.RevenueCat.RCLogLevelInfo
import cocoapods.RevenueCat.RCLogLevelVerbose
import cocoapods.RevenueCat.RCLogLevelWarn
import kotlinx.cinterop.ExperimentalForeignApi


@OptIn(ExperimentalForeignApi::class)
internal fun LogLevel.asRevenueCatLogLevel():Long{
    return when(this){
        LogLevel.VERBOSE -> RCLogLevelVerbose
        LogLevel.DEBUG -> RCLogLevelDebug
        LogLevel.INFO -> RCLogLevelInfo
        LogLevel.WARN -> RCLogLevelWarn
        LogLevel.ERROR -> RCLogLevelError
    }

}

@OptIn(ExperimentalForeignApi::class)
internal fun Long.asLogLevel():LogLevel{
    return when(this){
        RCLogLevelVerbose -> LogLevel.VERBOSE
        RCLogLevelDebug -> LogLevel.DEBUG
        RCLogLevelInfo -> LogLevel.INFO
        RCLogLevelWarn -> LogLevel.WARN
        RCLogLevelError ->LogLevel.ERROR
        else -> LogLevel.DEBUG
    }
}

