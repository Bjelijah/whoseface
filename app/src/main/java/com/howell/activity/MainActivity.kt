package com.howell.activity

import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.support.v4.view.animation.FastOutLinearInInterpolator
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

import android.view.ViewGroup
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.howell.bean.FaceBean
import com.howell.service.AliveService
import com.howell.whoseface.R
import io.codetail.animation.ViewAnimationUtils


class MainActivity :AppCompatActivity(){
    val SLOW_DURATION = 400L
    val FAST_DURATION = 200L
    @BindView(R.id.main_revealFrame) lateinit var mStack: ViewGroup
    @BindView(R.id.main_iv_1)     lateinit var mView1: ImageView
    @BindView(R.id.main_iv_2)     lateinit var mView2: ImageView
    @BindView(R.id.main_iv_3)     lateinit var mView3: ImageView
    @BindView(R.id.main_iv_4)     lateinit var mView4: ImageView

    var mIvList:List<ImageView> ?= null

    var currentViewIndex = 3
    val mTapDetector = object : GestureDetector.SimpleOnGestureListener(){
        override fun onDown(e: MotionEvent?): Boolean = true
        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            Log.i("123","onSingleTapUp")
            var nextView = getNext()
            nextView.bringToFront()
            Log.i("123","view id = ${nextView.id}")
            nextView.visibility = View.VISIBLE

            val finalRadius = Math.hypot((nextView.width/2f).toDouble(), (nextView.height/2f).toDouble())+
                    hypo(nextView,e!!)
            var revealAnimator =
                    ViewAnimationUtils.createCircularReveal(nextView,
                            e.x.toInt(),
                            e.y.toInt(),
                            0f,
                            finalRadius.toFloat(),
                            View.LAYER_TYPE_HARDWARE)
            revealAnimator.duration = SLOW_DURATION
            revealAnimator.interpolator = FastOutLinearInInterpolator()
            revealAnimator.start()
            return true
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

//        Glide.with(this).load("https://unsplash.it/600/300/?random").centerCrop().into(mView1)
        initView()
    }



    private fun initView(){
        var mgr =  Glide.with(this)
//            mgr.load("https://unsplash.it/600/300/?random")
//                .centerCrop().into(mView1)
            mgr.load("https://unsplash.it/600/300/?random")
                    .centerCrop().into(mView2)
//            mgr.load("https://unsplash.it/600/300/?random")
//                .skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).centerCrop().into(mView3)
            mgr.load("https://unsplash.it/600/300/?random")
                    .centerCrop().into(mView4)

        val detector = GestureDetector(this,mTapDetector)
        for(i in 0 until mStack.childCount){
            var v =mStack.getChildAt(i)

            v.setOnTouchListener { v, event ->  detector.onTouchEvent(event)}
        }

    }

    private fun hypo(v:View,event:MotionEvent): Double {
        var p1 = Point(event.x.toInt(), event.y.toInt())
        var p2 = Point(v.width/2 ,v.height /2)
        return Math.sqrt(Math.pow((p1.y-p2.y).toDouble(),2.0)+Math.pow((p1.x-p2.x).toDouble(),2.0))
    }

    private fun getNext():View {
        if (mIvList==null){
            mIvList = listOf(mView1,mView2,mView3,mView4)
        }
        ++currentViewIndex
        Log.i("123","currentViewIndex =$currentViewIndex   count=${mStack.childCount}")
        if(currentViewIndex>=mStack.childCount)
            currentViewIndex = 0
        else if(currentViewIndex<0)
            currentViewIndex = mStack.childCount -1
        Log.i("123","$currentViewIndex")
        return  mIvList!![currentViewIndex]
    }



    @OnClick(R.id.main_btn_entry)
    fun runClick(){
        startService(Intent(this,AliveService::class.java))
        onBackPressed()
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
        intent.putExtra("id","4fa3b3fd2f2840aab6b8835e02daf140")

//        var d = Date()
//        var time = Util.Date2ISODateString(d)
//        Log.i("123","$d     $time"   )
//        intent.putExtra("time",time)
        startActivity(intent)
//        startActivity(Intent(this,HistoryActiviy::class.java))

//        startActivity(Intent(this,DetailedActivity::class.java))
    }






}