package com.howell.modules.history

import android.content.Context
import android.util.Log
import com.howell.action.Config
import com.howell.bean.FaceBean
import com.howell.modules.BasePresenter
import com.howell.modules.ImpBaseView
import com.howellsdk.api.ApiManager
import com.howellsdk.utils.Util
import io.reactivex.Observable
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HistoryPresenter:BasePresenter(),IHistoryContract.IPresenter {
    var mContext:Context?=null
    var mView : IHistoryContract.IVew?=null


    override fun init(c: Context) {
        mContext = c
    }

    override fun queryFaceList(id: String, begTime: String, endTime: String) {
        Log.i("123","query face list begTime=$begTime   endTime = $endTime")
        ApiManager.getInstance().getHWHttpService(Config.URL)
                .queryFaceDetectEventRecords(
                        ApiManager.HttpHelp.getCookie(ApiManager.HttpHelp.Type.FACE_EVENTS_RECORDS),
                        begTime,endTime, id,
                        null,null,null
                ).map { recordList->
                    Log.i("123","recordList=$recordList")
                    recordList.eventRecords
                }.flatMap { list-> Observable.fromIterable(list) }
                .map { record->
                    var bean = FaceBean(record.componentId)
                    bean.msgTime     = Util.ISODateString2Date(record.alarmTime)
                    bean.similarity  = record.confidence?:0
                    bean.name        = record.name
                    bean.description = record.description?:""

                    bean.imageUrl1   = "http://${Config.IP}:8800/howell/ver10/Medium/Pictures/${record.faceAppendData.pictureId}/Data"
                    bean.imageUrl2   = "http://${Config.IP}:8800/howell/ver10/Medium/Pictures/${record.faceSnapData.facePictureId}/Data"

                    bean.userName    = record.faceAppendData.name
                    bean.sex         = record.faceAppendData.sex?:""
                    bean.phone       = record.faceAppendData.phone?:""
                    bean.birthday    = record.faceAppendData.birthDate?:""
                    bean.city        = record.faceAppendData.city?:""
                    bean.age         = if(record.faceSnapData.feature.age in 1..100)record.faceSnapData.feature.age else 0
                    bean.group       = record.faceSet.name
                    return@map bean
                }.toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :SingleObserver<List<FaceBean>>{
                    override fun onSuccess(t: List<FaceBean>) {
                        mView?.onQueryFaceResult(t as ArrayList<FaceBean>)
                    }

                    override fun onSubscribe(d: Disposable) {
                        addDisposable(d)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
    }

    override fun bindView(v: ImpBaseView) {
        mView = v as IHistoryContract.IVew
    }

    override fun unbindView() {
        dispose()
        mView = null
    }
}