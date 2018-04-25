package com.howell.utils

import android.annotation.SuppressLint
import android.content.Context
import android.telephony.TelephonyManager

object PhoneConfig {

    @SuppressLint("ServiceCast", "HardwareIds")
    fun getIMEI(c:Context):String = (c.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).deviceId

}