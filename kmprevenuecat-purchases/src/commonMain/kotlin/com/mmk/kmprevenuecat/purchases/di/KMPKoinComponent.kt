package com.mmk.kmprevenuecat.purchases.di

import com.mmk.kmprevenuecat.purchases.KMPRevenueCatInternalApi
import org.koin.core.Koin
import org.koin.core.component.KoinComponent

@KMPRevenueCatInternalApi
public abstract class KMPKoinComponent : KoinComponent {
    override fun getKoin(): Koin {
        requireNotNull(LibDependencyInitializer.koinApp) {
            "Make sure you invoked #initialize method"
        }
        return LibDependencyInitializer.koinApp?.koin!!
    }
}