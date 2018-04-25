package com.howell.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import com.howell.service.AliveService
import com.howell.whoseface.R

class MainActivity :AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.main_btn_entry)
    fun entryClick(){
        startService(Intent(this,AliveService::class.java))
        finish()
    }

    @OnClick(R.id.main_btn_push)
    fun pushClick(){
        startActivity(Intent(this,PushSetActivity::class.java))
    }

    @OnClick(R.id.main_btn_test)
    fun testClick(){
//        startActivity(Intent(this,FaceMain::class.java))
//        startActivity(Intent(this,FaceActivity::class.java))
        startActivity(Intent(this,HistoryActiviy::class.java))
    }
}