package com.lyadsky.noteapp.android

import android.app.Application
import com.lyadsky.noteapp.android.di.androidModule
import com.lyadsky.noteapp.common.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(Level.INFO)
            androidContext(this@App)
            allowOverride(true)
            modules(androidModule())
        }
    }
}
