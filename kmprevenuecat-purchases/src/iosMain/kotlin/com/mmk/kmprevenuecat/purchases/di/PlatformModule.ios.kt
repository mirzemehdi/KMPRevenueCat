package com.mmk.kmprevenuecat.purchases.di

import com.mmk.kmprevenuecat.purchases.KMPRevenueCatInternalApi
import com.mmk.kmprevenuecat.purchases.Purchases
import com.mmk.kmprevenuecat.purchases.PurchasesImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module


@KMPRevenueCatInternalApi
public actual fun isAndroidPlatform(): Boolean = false

internal actual val platformModule = module {
    factoryOf(::PurchasesImpl) bind Purchases::class

}