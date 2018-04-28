package com.howell.modules.face

import android.content.Context
import android.util.Base64
import android.util.Log
import com.howell.action.Config
import com.howell.bean.FaceBean
import com.howell.modules.BasePresenter
import com.howell.modules.ImpBaseView
import com.howellsdk.api.ApiManager
import com.howellsdk.net.http.bean.ClientCredential
import com.howellsdk.net.http.bean.Fault
import com.howellsdk.net.http.bean.UserNonce
import com.howellsdk.utils.Util
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

    override fun queryFace(id: String, begTime: String?, endTime: String?) {
        if(begTime==null || endTime==null){
            queryTheFace(id)
        }else{
            queryTheFace(id,begTime,endTime)
        }
    }

    private fun queryTheFace(id: String) {
        ApiManager.getInstance().getHWHttpService(Config.URL)
                .queryFaceDetecteventRecord(
                        ApiManager.HttpHelp.getCookie(ApiManager.HttpHelp.Type.FACE_EVENTS_RECORDS,id),
                        id
                )
                .map { record->
                    var bean = FaceBean(record.componentId)
                    bean.msgTime     = Util.ISODateString2Date(record.alarmTime)
                    bean.similarity  = record.confidence?:0
                    bean.name        = record.name
                    bean.description = record.description?:""

                    var id = String(Base64.decode(record.faceAppendData.pictureId,0))
                    Log.i("123"," url   id=$id")
                    bean.imageUrl1   = "http://${Config.IP}:8800/howell/ver10/Medium/Pictures/${record.faceAppendData.pictureId}/Data"
                    bean.imageUrl2   = "http://${Config.IP}:8800/howell/ver10/Medium/Pictures/${record.faceSnapData.facePictureId}/Data"
                    Log.i("123","url=${bean.imageUrl1}")
                    bean.userName    = record.faceAppendData.name
                    bean.sex         = record.faceAppendData.sex?:""
                    bean.phone       = record.faceAppendData.phone?:""
                    bean.birthday    = record.faceAppendData.birthDate?:""
                    bean.city        = record.faceAppendData.city?:""
                    bean.age         = if(record.faceSnapData.feature.age in 1..100)record.faceSnapData.feature.age else 0
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

    private fun queryTheFace(id:String,begTime:String,endTime:String){
        ApiManager.getInstance().getHWHttpService(Config.URL)
                .queryFaceDetectEventRecords(
                        ApiManager.HttpHelp.getCookie(ApiManager.HttpHelp.Type.FACE_EVENTS_RECORDS),
                        begTime,endTime, id,
                        null,null,null
                ).map { recordList->
                    Log.i("123","recordList=$recordList")
                    recordList.eventRecords[0]
                }.map { record->
                    var bean = FaceBean(record.componentId?:"")
                    bean.msgTime     = Util.ISODateString2Date(record.alarmTime)?:""
                    bean.similarity  = record.confidence?:0
                    bean.name        = record.name?:""
                    bean.description = record.description?:""

                    bean.imageUrl1   = "http://${Config.IP}:8800/howell/ver10/Medium/Pictures/${record.faceAppendData.pictureId}/Data"
                    bean.imageUrl2   = "http://${Config.IP}:8800/howell/ver10/Medium/Pictures/${record.faceSnapData.facePictureId}/Data"
                    bean.userName    = record.faceAppendData.name?:""
                    bean.sex         = record.faceAppendData.sex?:""
                    bean.phone       = record.faceAppendData.phone?:""
                    bean.birthday    = record.faceAppendData.birthDate?:""
                    bean.city        = record.faceAppendData.city?:""
                    bean.age         = if(record.faceSnapData.feature.age in 1..100)record.faceSnapData.feature.age else 0
                    bean.group       = record.faceSet.name?:""
                    return@map bean
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({t->
                    mView?.onQueryFaceResult(t)
                },{e->e.printStackTrace()})
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
                        Log.i("123","user nonce ok dommain=${t.domain}  nonce=${t.nonce}")
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
                        Log.i("123","login2 Server= $t")
                        ApiManager.HttpHelp.setCookie(req.userName,req.domain,t.id,req.verifySession)
                        Log.i("123","${t.faultCode}")
                        if(t.faultCode.equals("0")){
                            Log.i("123"," ==   mView=$mView")
                            mView?.onLoginResult(true)
                        }else{
                            Log.i("123"," != ")
                            mView?.onLoginResult(false)
                        }
                    }

                    override fun onError(e: Throwable) {
                    }

                })

    }

}