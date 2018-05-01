package com.howell.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.howell.adapter.DetailRecyclerViewAdapter
import com.howell.bean.DetailBean
import com.howell.whoseface.R
import com.howellsdk.utils.Util
import java.util.*

class DetailedActivity :AppCompatActivity(){

    @BindView(R.id.detailed_toolbar)lateinit var mTb: Toolbar
    @BindView(R.id.detail_rv)lateinit var mRv:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)
        ButterKnife.bind(this)
        initToolBar()
        initFun()
    }


    private fun initToolBar(){
        mTb.title = getString(R.string.info_title)
        setSupportActionBar(mTb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        mTb.setNavigationOnClickListener { finish() }
    }

    private fun initFun(){
        test()
    }

    private fun test(){
        var list = ArrayList<DetailBean>()
        for(i in 0..Random(System.currentTimeMillis()).nextInt(20)){
            var b = DetailBean("")
            var v = Random().nextInt(2000)
            b.value = v
            b.shopName = "二楼衣帽间"
            b.number = 5263
            b.time = Util.Date2String(Date())
            list.add(b)
        }
        mRv.layoutManager = LinearLayoutManager(this)
        mRv.adapter =    DetailRecyclerViewAdapter(this,list)
    }


}