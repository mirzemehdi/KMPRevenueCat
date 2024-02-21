package com.mmk.kmprevenuecat.purchases.di

import android.content.Context
import androidx.startup.Initializer
import com.mmk.kmprevenuecat.purchases.KMPRevenueCatInternalApi
import com.mmk.kmprevenuecat.purchases.Purchases
import com.mmk.kmprevenuecat.purchases.PurchasesImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal lateinit var applicationContext: Context
    private set

internal class ContextInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        applicationContext = context.applicationContext
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}


@KMPRevenueCatInternalApi
public actual fun isAndroidPlatform(): Boolean = true
internal actual val platformModule = module {
    single { applicationContext }
    factoryOf(::PurchasesImpl) bind Purchases::class
}


