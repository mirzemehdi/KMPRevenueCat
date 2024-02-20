package com.mmk.kmprevenuecat.purchases.di

import com.mmk.kmprevenuecat.purchases.KMPRevenueCatInternalApi
import org.koin.dsl.module


@KMPRevenueCatInternalApi
public actual fun isAndroidPlatform(): Boolean = false

internal actual val platformModule = module {

}