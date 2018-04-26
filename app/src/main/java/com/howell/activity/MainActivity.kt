package com.howell.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.howell.bean.FaceBean
import com.howell.service.AliveService
import com.howell.whoseface.R

class MainActivity :AppCompatActivity(){


    @BindView(R.id.main_iv)     lateinit var mView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        Glide.with(this).load("https://unsplash.it/600/300/?random").centerCrop().into(mView)
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
        var intent = Intent(this,FaceActivity::class.java)
        var b = FaceBean("查理一世")
        b.similarity = 2
        b.msgTime = "2018-04-26 10:26:23"
        b.age = 53
        b.sex = "男"
        b.group = ""
        b.imageUrl1 = "http://192.168.21.240:8800/DSC02453.jpg"
        b.imageUrl2 = "http://192.168.21.240:8800/DSC02455.jpg"
//        intent.putExtra("face_bean",b)
        intent.putExtra("id","asdf")
        intent.putExtra("time","")
        startActivity(intent)
//        startActivity(Intent(this,HistoryActiviy::class.java))
    }
}