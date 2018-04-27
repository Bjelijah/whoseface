package com.howell.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

import butterknife.BindView
import butterknife.ButterKnife
import com.howell.whoseface.R

class BuyInfos :AppCompatActivity(){

    @BindView(R.id.info_toolbar)lateinit var mTb: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infos)
        ButterKnife.bind(this)
        initToolBar()
    }


    private fun initToolBar(){
        mTb.title = getString(R.string.info_title)
        setSupportActionBar(mTb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        mTb.setNavigationOnClickListener { finish() }
    }

}