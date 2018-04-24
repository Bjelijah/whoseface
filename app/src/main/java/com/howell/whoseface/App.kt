package com.howell.whoseface

import android.support.multidex.MultiDexApplication
import com.howell.pushlibrary.DaemonEnv
import com.howell.service.AliveService

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        DaemonEnv.initialize(this,AliveService::class.java,DaemonEnv.DEFAULT_WAKE_UP_INTERVAL)
    }
}