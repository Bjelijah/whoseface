package com.howell.activity

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.howell.whoseface.R

class FaceMain :AppCompatActivity(),AppBarLayout.OnOffsetChangedListener {

    @BindView(R.id.face_main_fb)lateinit var mFb: FloatingActionButton
    @BindView(R.id.face_main_tb)lateinit var mTb:Toolbar
    @BindView(R.id.face_main_appbar)lateinit var mAppbar:AppBarLayout
    private var mMaxScrollSize: Int = 0
    private var mIsImageHidden: Boolean = false
    private val PERCENTAGE_TO_SHOW_IMAGE = 20
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_face_main)
        ButterKnife.bind(this)
        initFoolbar()
    }


    fun initFoolbar(){
        setSupportActionBar(mTb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mTb.setNavigationOnClickListener {
            onBackPressed()
        }
        mAppbar.addOnOffsetChangedListener(this)
    }



    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        if (mMaxScrollSize==0)mMaxScrollSize = mAppbar.totalScrollRange
        var currentScrollPercentage = (Math.abs(verticalOffset)) *100 / mMaxScrollSize
        if (currentScrollPercentage>= PERCENTAGE_TO_SHOW_IMAGE){
            if (!mIsImageHidden){
                mIsImageHidden = true
                ViewCompat.animate(mFb).scaleY(0f).scaleX(0f).start()
            }
        }else{
            if(mIsImageHidden){
                mIsImageHidden = false
                ViewCompat.animate(mFb).scaleY(1f).scaleX(1f).start()

            }

        }
    }

    @OnClick(R.id.face_main_fb)
    fun onFbClick(){

    }


}