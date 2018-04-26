package com.howell.utils

import android.annotation.SuppressLint
import android.content.Context
import android.telephony.TelephonyManager
import android.view.WindowManager

object PhoneConfig {

    @SuppressLint("ServiceCast", "HardwareIds")
    fun getIMEI(c:Context):String = (c.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).deviceId

    fun getPhoneWidth(c:Context):Int = (c.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.width

    fun getPhoneHeight(c:Context):Int = (c.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.height

}