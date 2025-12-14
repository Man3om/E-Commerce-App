package com.example.e_commerce.app

import android.app.Application
import com.example.e_commerce.app.utils.Crashlytics
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ECommerceApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // initialize Uncaught Exception Handler
        val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(Crashlytics(defaultHandler))
    }
}