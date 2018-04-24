package com.howell.service

import android.content.Intent
import android.os.IBinder
import com.howell.pushlibrary.AbsWorkService

class AliveService : AbsWorkService() {
    override fun shouldStopService(intent: Intent?, flags: Int, startId: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun shouldStopService(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun startWork(intent: Intent?, flags: Int, startId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stopWork(intent: Intent?, flags: Int, startId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stopWork() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isWorkRunning(intent: Intent?, flags: Int, startId: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBind(intent: Intent?, alwaysNull: Void?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServiceKilled(rootIntent: Intent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }




}