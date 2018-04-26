package com.howell.modules.push

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Base64
import android.util.Log
import com.howell.activity.FaceMain
import com.howell.modules.BasePresenter
import com.howell.modules.ImpBaseView
import com.howell.whoseface.R
import com.howellsdk.api.ApiManager
import com.howellsdk.api.HWWebSocketApi
import com.howellsdk.net.http.utils.NetWorkUtil
import com.howellsdk.net.websocket.bean.WSRes
import com.howellsdk.utils.ThreadUtil
import io.reactivex.Observable
import org.json.JSONException
import java.util.concurrent.TimeUnit

class PushPresenter :BasePresenter(),IPushContract.IPresenter{
    var mView:IPushContract.IVew      ?= null
    var mContext:Context              ?= null
    var mURL:String                   ?= null
    var mIMEI:String                  ?= null
    var mIsOpen                        = false
    var mNotifyMgr:NotificationManager?= null
    var mNotifyId                      = 0
    var mIsServiceWork                 = false
    var mHeart                         = 0
    var mIsAliveHeart                  = false
    var mCseq = 0
    override fun init(c: Context, url: String, imei: String): IPushContract.IPresenter {
        mContext = c
        mURL = url
        mIMEI = imei
        mIsOpen = false
        mNotifyId = 0
        mIsServiceWork = false
        mNotifyMgr = c.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        ApiManager.getInstance().getWebSocketService(url,object :HWWebSocketApi.IWebSocketCB{
            override fun onWebSocketOpen() {
                mView?.onWebSocketOpen()
                mIsOpen = true
                sendLink()
            }

            override fun onWebSocketClose() {
                mIsOpen = false
                Log.i("547","on web socket close url=$mURL")
                //stop heart
                stopHeart()
                if (!NetWorkUtil.isNetWorkAvailable(c)){ mView?.onWebSocketClose();return }
                if (!mIsServiceWork){ mView?.onWebSocketClose();return}
                Observable.timer(1,TimeUnit.SECONDS)
                        .subscribe({
                            link()
                        },{e->e.printStackTrace()})
            }

            override fun onGetMessage(res: WSRes?) {
                when(res?.type){
                    WSRes.WS_TYPE.ALARM_LINK->{
                        val alarmLinkRes = res.resultObject as WSRes.AlarmLinkRes
                        if (alarmLinkRes.result==0){
                            sendHeart()
                        }else{
                            unLink()
                        }
                    }
                    WSRes.WS_TYPE.ALARM_ALIVE->{
                        mHeart = 0
                        val aRes = res.resultObject as WSRes.AlarmAliveRes
                        startHeart(aRes.heartbeatinterval.toLong())
                    }
                    WSRes.WS_TYPE.ALARM_EVENT->{
                        val event = res.resultObject as WSRes.AlarmEvent
                        sendEventAfk(event.cseq)

                    }
                    WSRes.WS_TYPE.ALARM_NOTICE->{

                    }
                    WSRes.WS_TYPE.PUSH_MESSAGE->{
                        val ps = res.resultObject as WSRes.PushMessage
                        sendPushAfk(ps.cseq)
                        showNotification(String(Base64.decode(ps.content,0)))
                    }

                    else->{}

                }


            }

            override fun onError(error: Int) {
                Log.e("547","on error $error")
            }
        })
        return this
    }

    override fun connect() {
        mIsServiceWork = true
        ThreadUtil.cachedThreadStart{
            ApiManager.getInstance().webSocketService.connect()
        }
    }

    override fun disconnect() {
        mIsServiceWork = false
        ThreadUtil.cachedThreadStart {
            ApiManager.getInstance().webSocketService.disconnect()
        }
    }

    override fun bindView(v: ImpBaseView) {
        mView = v as IPushContract.IVew
    }

    override fun unbindView() {
        dispose()
        mView = null
        unLink()
    }

    private fun link(){
        connect()
    }

    private fun unLink(){
        ApiManager.getInstance().webSocketService.disconnect()
        stopHeart()
    }

    private fun getCseq():Int = mCseq++


    private fun sendLink(){
        ThreadUtil.cachedThreadStart{
            try {
                ApiManager.getInstance().webSocketService
                        .alarmLink(getCseq(),null,mIMEI)
            }catch (e:JSONException){
                e.printStackTrace()
            }
        }
    }

    private fun sendHeart(){
        ThreadUtil.cachedThreadStart {
            try {
                ApiManager.getInstance().webSocketService
                        .alarmAlive(getCseq(), 0, null, null)
            }catch (e:JSONException){
                e.printStackTrace()
            }
        }
    }

    private fun sendEventAfk(cseq: Int){
        sendPushAfk(cseq)
    }

    private fun sendPushAfk(cseq: Int) {
        ThreadUtil.cachedThreadStart {
            try {
                Log.i("547", "send push afk")
                ApiManager.getInstance().webSocketService
                        .ADCEventRes(cseq)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }

    private fun startHeart(delaySec:Long){
        if (!mIsAliveHeart){
            mIsAliveHeart = true
            ThreadUtil.scheduledSingleThreadStart({

            },delaySec,delaySec,TimeUnit.SECONDS)

        }
    }


    private fun stopHeart(){
        ThreadUtil.scheduledSingleThreadShutDown()
        mIsAliveHeart = false
    }

    private fun getNotificationId():Int {
        mNotifyId++
        if (mNotifyId>10)mNotifyId = 0
        return mNotifyId
    }

    private fun showNotification(content:String){
        val nb = Notification.Builder(mContext)
        nb.setTicker("报警")

        //setContentTitle


        nb.setSmallIcon(R.mipmap.ic_launcher)
        nb.setWhen(System.currentTimeMillis())
        nb.setAutoCancel(true)
        nb.setDefaults(Notification.DEFAULT_SOUND)
        val intent = Intent(mContext,FaceMain::class.java)
        //set msg
        val pendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        nb.setContentIntent(pendingIntent)
        mNotifyMgr?.notify(getNotificationId(), nb.build())
    }

}