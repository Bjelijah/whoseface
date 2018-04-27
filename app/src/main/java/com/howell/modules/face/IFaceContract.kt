package com.howell.modules.face

import android.content.Context
import com.howell.bean.FaceBean
import com.howell.modules.ImpBasePresenter
import com.howell.modules.ImpBaseView

interface IFaceContract {
    interface IVew: ImpBaseView{
        fun onLoginResult(isOK: Boolean)
        fun onQueryFaceResult(bean:FaceBean)
    }
    interface IPresenter : ImpBasePresenter{
        fun init(c: Context)
        fun queryFace(id:String,begTime:String?,endTime:String?)
    }
}