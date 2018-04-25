package com.howell.activity

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Switch

import butterknife.BindView
import butterknife.ButterKnife
import com.howell.pushlibrary.IntentWrapper
import com.howell.service.AliveService
import com.howell.utils.ConfigSp
import com.howell.whoseface.R

class PushSetActivity : AppCompatActivity() {

    @BindView(R.id.push_set_toolbar)lateinit var mTb: Toolbar
    @BindView(R.id.push_set_sw)lateinit var mSwitch:Switch

    var mIsPush = false
    var mIntent:Intent?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push_set)
        ButterKnife.bind(this)
        initToolbar()
        initView()
        initFun()
    }

    override fun onDestroy() {
        foo()
        super.onDestroy()
    }

    private fun initToolbar(){
        mTb.title = getString(R.string.push_settting_title)
        setSupportActionBar(mTb)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        mTb.setNavigationOnClickListener { finish() }
    }

    private fun initView(){
        mSwitch.setOnClickListener {
            mIsPush = mSwitch.isChecked
            IntentWrapper.whiteListMatters(this,null)
        }
    }

    private fun initFun(){
        mIntent = Intent(this,AliveService::class.java)
        mIsPush = ConfigSp.loadPushOnOff(this)
        mSwitch.isChecked = mIsPush
    }

    private fun foo(){
        ConfigSp.savePushOnOff(this,mIsPush)
        if (mIsPush){
            startService(mIntent)
        }else{
            AliveService.stopService()
            stopService(mIntent)
            AliveService.sIsWorking = false
        }
    }
}