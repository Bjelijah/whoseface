package com.howell.modules.push

import android.content.Context
import com.howell.modules.ImpBasePresenter
import com.howell.modules.ImpBaseView

interface IPushContract {
    interface IVew:ImpBaseView{
        fun onWebSocketOpen()
        fun onWebSocketClose()
    }
    interface IPresenter : ImpBasePresenter{
        fun init(c:Context,url:String,imei:String):IPresenter
        fun connect()
        fun disconnect()
    }
}