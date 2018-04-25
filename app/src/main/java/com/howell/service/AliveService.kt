package com.howell.service

import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.howell.action.Config
import com.howell.modules.push.IPushContract
import com.howell.modules.push.PushPresenter
import com.howell.pushlibrary.AbsWorkService
import com.howell.pushlibrary.DaemonEnv
import com.howell.utils.PhoneConfig

class AliveService : AbsWorkService(),IPushContract.IVew {



    private var mPresenter:IPushContract.IPresenter         ?= null


    companion object {
        var sShouldStopService = false
        var sIsWorking         = false
        fun stopService(){
            DaemonEnv.mShouldWakeUp = false
            cancelJobAlarmSub()
            sShouldStopService = true
        }
    }

    override fun onBind(intent: Intent?): IBinder?  = null

    override fun shouldStopService(intent: Intent?, flags: Int, startId: Int): Boolean = sShouldStopService

    override fun shouldStopService(): Boolean = sShouldStopService

    override fun startWork(intent: Intent?, flags: Int, startId: Int) {
        sIsWorking = true
        Log.e("547", ":start work")
        link()
    }

    override fun stopWork(intent: Intent?, flags: Int, startId: Int) {
        sIsWorking = false
        unLink()
    }

    override fun stopWork() {
        sIsWorking = false
        unLink()
    }

    override fun isWorkRunning(intent: Intent?, flags: Int, startId: Int): Boolean  = sIsWorking

    override fun onBind(intent: Intent?, alwaysNull: Void?): IBinder? {
        return null
    }

    override fun onServiceKilled(rootIntent: Intent?) {
        sIsWorking = true
        unLink()
        unbindPresenter()
    }

    override fun onWebSocketOpen() {
        sIsWorking = true
    }

    override fun onWebSocketClose() {
        sIsWorking = false
    }

    override fun bindPresenter() {
        if (mPresenter==null)mPresenter = PushPresenter()
        mPresenter?.bindView(this)
    }

    override fun unbindPresenter() {
        mPresenter?.unbindView()
        mPresenter = null
    }


    override fun onDestroy() {
        unLink()
        unbindPresenter()
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        DaemonEnv.mShouldWakeUp = true
        sShouldStopService = false
        bindPresenter()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun link(){

        var url = "ws://" + Config.IP + ":8803/howell/ver10/ADC"
        mPresenter?.init(this,url,PhoneConfig.getIMEI(this))?.connect()
    }

    private fun unLink(){
        mPresenter?.disconnect()
        mPresenter = null
    }



}