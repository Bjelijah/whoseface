package com.howell.modules.face

import android.content.Context
import com.howell.action.Config
import com.howell.bean.FaceBean
import com.howell.modules.BasePresenter
import com.howell.modules.ImpBaseView
import com.howellsdk.api.ApiManager
import com.howellsdk.net.http.bean.ClientCredential
import com.howellsdk.net.http.bean.Fault
import com.howellsdk.net.http.bean.UserNonce
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FacePresenter:BasePresenter(),IFaceContract.IPresenter {


    var mView:IFaceContract.IVew ?=null
    var mContext:Context ?=null

    override fun init(c: Context) {
        mContext = c
        login()
    }
    override fun queryFace(id: String, begTime: String, endTime: String) {
        ApiManager.getInstance().getHWHttpService(Config.URL)
                .queryFaceDetecteventRecord(
                        ApiManager.HttpHelp.getCookie(ApiManager.HttpHelp.Type.FACE_EVENTS_RECORDS,id),
                        id,
                        begTime,
                        endTime
                )
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
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<FaceBean>{
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                        addDisposable(d)
                    }

                    override fun onNext(t: FaceBean) {
                        mView?.onQueryFaceResult(t)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })


    }

    override fun bindView(v: ImpBaseView) {
        mView = v as IFaceContract.IVew
    }

    override fun unbindView() {
        dispose()
        mView = null
    }

    private fun login(){
        ApiManager.getInstance().initHttpClient(mContext,false)
                .getHWHttpService(Config.URL)
                .getUserNonce(Config.USER_NAME)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<UserNonce>{
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                        addDisposable(d)
                    }

                    override fun onNext(t: UserNonce) {
                        login2Server(ClientCredential(
                                Config.USER_NAME,
                                Config.PASSWORD,
                                t.domain,
                                t.nonce
                        ))
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
    }

    private fun login2Server(req:ClientCredential){
        ApiManager.getInstance().getHWHttpService(Config.URL)
                .doUserAuthenticate(req)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<Fault>{
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                        addDisposable(d)
                    }

                    override fun onNext(t: Fault) {
                        ApiManager.HttpHelp.setCookie(req.userName,req.domain,t.id,req.verifySession)
                    }

                    override fun onError(e: Throwable) {
                    }

                })

    }

}