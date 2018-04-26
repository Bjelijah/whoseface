package com.howell.activity

import android.media.FaceDetector
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.howell.adapter.HistroyRecyclerViewAdapter
import com.howell.bean.FaceBean
import com.howell.whoseface.R

class HistoryActiviy:AppCompatActivity() ,HistroyRecyclerViewAdapter.OnItemClick{


    @BindView(R.id.history_toolbar)lateinit var mTb:Toolbar
    @BindView(R.id.history_rv)lateinit var mRv:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        ButterKnife.bind(this)
        initView()
        initFun()
    }

    private fun initView(){
        mTb.title = getString(R.string.face_history)
        setSupportActionBar(mTb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        mTb.setNavigationOnClickListener { finish() }

        mRv.layoutManager = LinearLayoutManager(this)
        mRv.adapter = HistroyRecyclerViewAdapter(this,this)
    }

    fun initFun(){
        //get msg
        testData()



    }

    fun testData(){
        var bean = FaceBean("阿猫")
        bean.sex = "男"
        bean.age = 12
        bean.group ="group 123"
        bean.msgTime="2018-4-26 13:00:54"
        bean.similarity = 3

        var l:ArrayList<FaceBean> = ArrayList()
        for(i in 0..10){
            l.add(bean)
        }
        (mRv.adapter as HistroyRecyclerViewAdapter).setData(l)
    }



    override fun onItemClick(bean: FaceBean, pos: Int) {

    }
}