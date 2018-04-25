package com.howell.modules

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter {
    private var mCompositeDisposable:CompositeDisposable?=null

    open fun addDisposable(subscription:Disposable){
        if (mCompositeDisposable?.isDisposed != false){
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable?.add(subscription)
    }

    fun dispose(){
        mCompositeDisposable?.dispose()
    }

}