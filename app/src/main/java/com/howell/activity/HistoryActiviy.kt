package com.howell.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.howell.adapter.HistroyRecyclerViewAdapter
import com.howell.bean.FaceBean
import com.howell.modules.history.HistoryPresenter
import com.howell.modules.history.IHistoryContract
import com.howell.whoseface.R
import com.howellsdk.utils.Util

class HistoryActiviy:AppCompatActivity() ,HistroyRecyclerViewAdapter.OnItemClick,IHistoryContract.IVew{

    var mPresenter:IHistoryContract.IPresenter ?=null

    @BindView(R.id.history_toolbar)lateinit var mTb:Toolbar
    @BindView(R.id.history_rv)lateinit var mRv:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        ButterKnife.bind(this)
        initView()
        initFun()
    }

    override fun onQueryFaceResult(beans: ArrayList<FaceBean>) {
        (mRv.adapter as HistroyRecyclerViewAdapter).setData(beans)
    }

    override fun bindPresenter() {
        if (mPresenter==null)mPresenter = HistoryPresenter()
        mPresenter?.init(this)
    }

    override fun unbindPresenter() {
        mPresenter?.unbindView()
        mPresenter = null
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
//        testData()
        var id = intent?.getStringExtra("id")
        var time = intent?.getStringExtra("time")
        var date = Util.ISODateString2ISODate(time)
        var db = Util.plusMonth(date,-1)
        var df = Util.plusMinute(date,2)
        var timeb = Util.Date2ISODateString(db)
        var timef = Util.Date2ISODateString(df)


        mPresenter?.queryFaceList(id!!,timeb,timef)


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
        var intent = Intent(this,FaceActivity::class.java)
        intent.putExtra("id",bean.componentId)
        intent.putExtra("time",bean.msgTime)
        startActivity(intent)
    }
}