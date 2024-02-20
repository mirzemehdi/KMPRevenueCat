package com.mmk.kmprevenuecat.purchases.di

import com.mmk.kmprevenuecat.purchases.KMPRevenueCatInternalApi
import org.koin.core.module.Module

@KMPRevenueCatInternalApi
public expect fun isAndroidPlatform(): Boolean
internal expect val platformModule: Module