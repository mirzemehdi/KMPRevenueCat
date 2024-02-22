package com.mmk.kmprevenuecat.purchases

import com.mmk.kmprevenuecat.purchases.data.CustomerInfo
import com.mmk.kmprevenuecat.purchases.data.LogInResult
import com.mmk.kmprevenuecat.purchases.di.KMPKoinComponent
import com.mmk.kmprevenuecat.purchases.di.LibDependencyInitializer
import org.koin.core.component.get

public interface Purchases {
    public companion object : Purchases by PurchasesProviderImpl.get()

    public var logLevel: LogLevel

    public fun configure(apiKey: String)

    public fun login(appUserId: String, onResult: (Result<LogInResult>) -> Unit)
    public fun logOut(onResult: (Result<CustomerInfo>) -> Unit)


    public fun getCustomerInfo(
        fetchPolicy: CacheFetchPolicy = CacheFetchPolicy.default(),
        onResult: (Result<CustomerInfo>) -> Unit
    )

    public fun setAttributes(attributes: Map<String,String?>)

    @OptIn(KMPRevenueCatInternalApi::class)
    private object PurchasesProviderImpl : KMPKoinComponent() {
        @OptIn(KMPRevenueCatInternalApi::class)
        fun get(): Purchases {
            LibDependencyInitializer.initialize()
            return (this as KMPKoinComponent).get()
        }

    }

}
