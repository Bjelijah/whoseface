package com.howell.whoseface

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.howell.service.AliveService

@Deprecated("use com.howell.MainActivity")
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startService(Intent(this,AliveService::class.java))



    }
}
