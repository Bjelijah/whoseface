package com.howell.utils

import android.content.Context

object ConfigSp {
    val SP_NAME = "alive_set"

    fun savePushOnOff(c: Context, isOnOff:Boolean){
        var sp = c.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE)
        var edit = sp.edit()
        edit.putBoolean("push_on_off",isOnOff)
        edit.commit()
    }

    fun loadPushOnOff(c:Context):Boolean{
        var sp = c.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE)
        return sp.getBoolean("push_on_off",false)
    }
}