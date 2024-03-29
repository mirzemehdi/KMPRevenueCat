package com.mmk.kmprevenuecat.purchases.di


import com.mmk.kmprevenuecat.purchases.KMPRevenueCatInternalApi
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.koinApplication
import org.koin.dsl.module

@KMPRevenueCatInternalApi
public object LibDependencyInitializer {

    @KMPRevenueCatInternalApi
    public var koinApp: KoinApplication? = null
        private set

    @KMPRevenueCatInternalApi
    public fun initialize(module: Module) {
        initialize(listOf(module))
    }

    @KMPRevenueCatInternalApi
    public fun initialize(modules: List<Module> = emptyList()) {
        if (isInitialized()) return
        val configModule = module {
            includes(modules)
        }
        koinApp = koinApplication {
            modules(configModule + platformModule)
        }.also {
            it.koin.onLibraryInitialized()
        }

    }

    private fun isInitialized() = koinApp != null


}

private fun Koin.onLibraryInitialized() {
    println("Library is initialized")
}

