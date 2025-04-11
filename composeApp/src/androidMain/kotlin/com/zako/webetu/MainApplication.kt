package com.zako.webetu

import android.app.Application
import com.zako.webetu.di.initKoin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MainApplication)
        }
    }
}
