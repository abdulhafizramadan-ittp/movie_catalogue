package com.example.moviecatalogue.di

import android.app.Application
import org.koin.core.context.startKoin

@Suppress("UNUSED")
class DIApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }
}