package com.howell.bean

import java.io.Serializable

data class FaceBean(var componentId:String):Serializable {
    var msgTime = ""
    var similarity = 0
    var name = ""//触发地点
    var description = ""
    var imageUrl1 = ""
    var imageUrl2 = ""
    var userName = ""
    var sex = ""
    var phone = ""
    var birthday = ""
    var city = ""
    var age = 0
    var group = ""//人脸库名称
}