package com.ebayk

import android.app.Application
import com.ebayk.di.adModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EbayApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EbayApplication)
            modules(
                listOf(
                    adModule
                )
            )
        }
        instance = this
    }

    companion object {
        lateinit var instance: EbayApplication
            private set
    }

}