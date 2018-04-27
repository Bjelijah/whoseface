package com.howell.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import com.howell.action.Config
import com.howell.adapter.HistroyRecyclerViewAdapter
import com.howell.bean.FaceBean
import com.howell.modules.history.HistoryPresenter
import com.howell.modules.history.IHistoryContract
import com.howell.whoseface.R
import com.howellsdk.utils.Util
import java.util.*

class HistoryActiviy:AppCompatActivity() ,HistroyRecyclerViewAdapter.OnItemClick,IHistoryContract.IVew{


    var mPresenter:IHistoryContract.IPresenter ?=null

    @BindView(R.id.history_toolbar)lateinit var mTb:Toolbar
    @BindView(R.id.history_rv)lateinit var mRv:RecyclerView
    @BindView(R.id.history_pb)lateinit var mPb:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        ButterKnife.bind(this)
        bindPresenter()
        initView()
//        initFun()
        if(Config.Debug){
            testData()
        }else{
            initFun()
        }
    }

    override fun onDestroy() {
        unbindPresenter()
        super.onDestroy()
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e("123","on new Intent")
        setIntent(intent)
        if (Config.Debug){
            testData()
        }else{
            initFun()
        }
//        initFun()

    }


    override fun onQueryFaceResult(beans: ArrayList<FaceBean>) {
        mPb.visibility = View.GONE
        (mRv.adapter as HistroyRecyclerViewAdapter).setData(beans)
    }

    override fun bindPresenter() {
        if (mPresenter==null)mPresenter = HistoryPresenter()
        mPresenter?.bindView(this)
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

    private fun initFun(){
        mPb.visibility = View.VISIBLE
        var id = intent?.getStringExtra("id")


        var d = Date()
//        time = Util.Date2ISODateString(d)
//        var date = Util.ISODateString2ISODate(time)
        var db = Util.plusMonth(d,-1)
        var df = Util.plusMinute(d,2)
        var timeb = Util.Date2ISODateString(db)
        var timef = Util.Date2ISODateString(df)
        Log.i("123","timeb=$timeb   timef=$timef")
        Log.e("123","query face list    id=$id")
        mPresenter?.queryFaceList(id!!,timeb,timef)
    }



    fun testData(){
        var bean = FaceBean("1")
        var d = Date()
        var time = Util.Date2ISODateString(d)
        bean.userName = "阿猫阿狗"
        bean.sex = "男"
        bean.age = 12
        bean.group ="group 123"
        bean.msgTime=time//"2018-4-26 13:00:54"
        bean.similarity = 3

        var l:ArrayList<FaceBean> = ArrayList()
        for(i in 0..10){
            l.add(bean)
        }
        (mRv.adapter as HistroyRecyclerViewAdapter).setData(l)
    }



    override fun onItemClick(bean: FaceBean) {
        var intent = Intent(this,FaceActivity::class.java)
//        intent.putExtra("id",bean.componentId)
//        intent.putExtra("time",bean.msgTime)
        intent.putExtra("bean",bean)
        startActivity(intent)
    }

    override fun onInfoClick(bean: FaceBean) {
        var intent = Intent(this,BuyInfos::class.java)
        startActivity(intent)
    }
}