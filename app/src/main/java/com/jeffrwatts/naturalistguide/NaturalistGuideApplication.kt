package com.jeffrwatts.naturalistguide

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NaturalistGuideApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}