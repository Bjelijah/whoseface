package com.howell.modules.history

import android.content.Context
import com.howell.bean.FaceBean
import com.howell.modules.ImpBasePresenter
import com.howell.modules.ImpBaseView

interface IHistoryContract {
    interface IVew: ImpBaseView {
        fun onQueryFaceResult(beans: ArrayList<FaceBean>)
    }
    interface IPresenter : ImpBasePresenter {
        fun init(c: Context)
        fun queryFaceList(id:String,begTime:String,endTime:String)
    }
}