package com.jamesnyakush.news

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.error.KoinAppAlreadyStartedException
import org.koin.core.logger.Level
import org.koin.core.module.Module
import timber.log.Timber

class News : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
        initTimber()
    }

    private fun initKoin() {
        try {
            startKoin {
                androidLogger(Level.ERROR)
                androidContext(applicationContext)
                val modules = mutableListOf<Module>().apply {
                    addAll(dataModule)
                    add(viewModelModule)
                }
                modules(modules)
            }
        } catch (error: KoinAppAlreadyStartedException) {
            Timber.e(error.localizedMessage)
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String {
                    return super.createStackElementTag(element) + ":" + element.lineNumber
                }
            })
        } else {
            //Timber.plant(CrashlyticsTree())
        }
    }
}