package com.howell.service

import android.app.Notification
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.howell.action.Config
import com.howell.modules.push.IPushContract
import com.howell.modules.push.PushPresenter
import com.howell.pushlibrary.AbsWorkService
import com.howell.pushlibrary.DaemonEnv
import com.howell.utils.PhoneConfig
import com.howell.whoseface.R

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
        sIsWorking = false
        unLink()
        unbindPresenter()
        stopForeground(true)
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
        stopForeground(true)
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        DaemonEnv.mShouldWakeUp = true
        sShouldStopService = false
        bindPresenter()
        startServiceForeground()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startServiceForeground(){
        var n = Notification.Builder(this.getApplicationContext())
                .setContentTitle("后台运行")
                .setContentText("WhoseFace 正在运行")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setWhen(System.currentTimeMillis())
                .build()
        startForeground(110,n)
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