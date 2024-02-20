package com.mmk.kmprevenuecat.sample

import android.app.Application
import com.mmk.kmprevenuecat.sample.AppInitializer

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppInitializer.onApplicationStart()
    }
}