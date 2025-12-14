package com.example.e_commerce.app.utils

import android.util.Log

class Crashlytics(private val delegate: Thread.UncaughtExceptionHandler?) :
    Thread.UncaughtExceptionHandler {
    private val TAG = "UncaughtExceptions"
    override fun uncaughtException(t: Thread, e: Throwable) {
        // Log or report the crash
        Log.e(TAG, "Uncaught exception in thread ${t.name}", e)
        e.printStackTrace()

        // Always delegate to the default handler (important!)
        delegate?.uncaughtException(t, e)
    }
}