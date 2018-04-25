package com.howell.bean

import java.io.Serializable

data class FaceBean(var name:String):Serializable {
    var similarity = 0
    var age = 0
    var group = ""
    var sex = ""
    var imageUrl1 = ""
    var imageUrl2 = ""
    var msgTime = ""
}