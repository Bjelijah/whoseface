package com.howell.utils

import android.content.Context
import android.net.ConnectivityManager

object NetWorkUtil {
    fun isNetWorkConnected(c:Context):Boolean{
        var mgr:ConnectivityManager = c.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return try {
            mgr.activeNetworkInfo.isAvailable
        }catch (e:NullPointerException){
            false
        }
    }
}