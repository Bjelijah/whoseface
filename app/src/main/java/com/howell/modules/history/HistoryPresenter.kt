package com.howell.modules.history

import android.content.Context
import com.howell.action.Config
import com.howell.bean.FaceBean
import com.howell.modules.BasePresenter
import com.howell.modules.ImpBaseView
import com.howellsdk.api.ApiManager
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
        ApiManager.getInstance().getHWHttpService(Config.URL)
                .queryFaceDetectEventRecords(
                        ApiManager.HttpHelp.getCookie(ApiManager.HttpHelp.Type.FACE_EVENTS_RECORDS),
                        begTime,endTime, id,
                        null,null,null
                ).map { recordList->
                    recordList.eventRecords
                }.flatMap { list-> Observable.fromIterable(list) }
                .map { record->
                    var bean = FaceBean(record.componentId)
                    bean.msgTime     = record.alarmTime
                    bean.similarity  = record.confidence
                    bean.name        = record.name
                    bean.description = record.description

                    bean.imageUrl1   = record.faceAppendData.pictureId
                    bean.imageUrl2   = record.faceSnapData.facePictureId

                    bean.userName    = record.faceAppendData.name
                    bean.sex         = record.faceAppendData.sex
                    bean.phone       = record.faceAppendData.phone
                    bean.birthday    = record.faceAppendData.birthDate
                    bean.city        = record.faceAppendData.city
                    bean.age         = record.faceSnapData.feature.age
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