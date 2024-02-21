package com.mmk.kmprevenuecat.purchases

import com.mmk.kmprevenuecat.purchases.di.KMPKoinComponent
import com.mmk.kmprevenuecat.purchases.di.LibDependencyInitializer
import org.koin.core.component.get

public interface Purchases {
    public companion object : Purchases by PurchasesProviderImpl.get()

    public var logLevel: LogLevel

    public fun configure(apiKey: String)


    @OptIn(KMPRevenueCatInternalApi::class)
    private object PurchasesProviderImpl : KMPKoinComponent() {
        @OptIn(KMPRevenueCatInternalApi::class)
        fun get(): Purchases {
            LibDependencyInitializer.initialize()
            return (this as KMPKoinComponent).get()
        }

    }

}
