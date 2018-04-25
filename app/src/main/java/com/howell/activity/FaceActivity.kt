package com.howell.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.howell.whoseface.R

class FaceActivity:AppCompatActivity() ,AppBarLayout.OnOffsetChangedListener{

    val PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f
    val PERCENTAGE_TO_HIDE_TITLE_DETAILS    = 0.3f
    val ALPHA_ANIMATIONS_DURATION           = 200L

    var mIsTheTitleVisible                  = false
    var mIsTheTitleContainerVisible         = true


    @BindView(R.id.face_appbar)lateinit var mAppBar : AppBarLayout
    @BindView(R.id.face_tb)lateinit var mTb:Toolbar
    @BindView(R.id.face_title_tv_small)lateinit var mTitle:TextView
    @BindView(R.id.face_title_ll)lateinit var mTitleContainer: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_face)
        ButterKnife.bind(this)
        initView()

    }

    override fun onStart() {
        super.onStart()
        initFun(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        initFun(intent)
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_face,menu)

        return true
    }


    private fun initView(){
        mAppBar.addOnOffsetChangedListener(this)
        mTb.inflateMenu(R.menu.menu_face)
        var menuItem = mTb.menu.findItem(R.id.menu_face_history)
        menuItem.setOnMenuItemClickListener {
            Log.i("123","history click")
            true
        }
        startAlphaAnimation(mTitle,0,View.INVISIBLE)
    }

    private fun initFun(intent: Intent?){
        var bean = intent?.getSerializableExtra("bean")
        //TODO
    }


    private fun startAlphaAnimation(v: View,duration:Long,visibility:Int ){
        var alphaAnimation = if (visibility == View.VISIBLE){
            AlphaAnimation(0f,1f)
        }else{
            AlphaAnimation(1f,0f)
        }
        alphaAnimation.duration = duration
        alphaAnimation.fillAfter = true
        v.startAnimation(alphaAnimation)
    }

    private fun handleAlphaOnTitle(percentage:Float){
        if (percentage>=PERCENTAGE_TO_HIDE_TITLE_DETAILS){
            if (mIsTheTitleContainerVisible){
                startAlphaAnimation(mTitleContainer,ALPHA_ANIMATIONS_DURATION,View.INVISIBLE)
                startAlphaAnimation(mTb,ALPHA_ANIMATIONS_DURATION,View.VISIBLE)
                ViewCompat.animate(mTb).alpha(1.0f).start()
                mIsTheTitleContainerVisible = false
            }
        }else{
            if (!mIsTheTitleContainerVisible){
                startAlphaAnimation(mTitleContainer,ALPHA_ANIMATIONS_DURATION,View.VISIBLE)
                ViewCompat.animate(mTb).alpha(0.0f).start()
                mIsTheTitleContainerVisible = true
            }
        }
    }

    private fun handleToolbarTitleVisibility(percentage:Float){
        if (percentage>=PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR){
            if (!mIsTheTitleVisible){
                mIsTheTitleVisible = true
                startAlphaAnimation(mTitle,ALPHA_ANIMATIONS_DURATION,View.VISIBLE)
            }
        }else{
            if (mIsTheTitleVisible){
                startAlphaAnimation(mTitle,ALPHA_ANIMATIONS_DURATION,View.INVISIBLE)
                mIsTheTitleVisible = false
            }
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, offset: Int) {
        var maxScroll = appBarLayout?.totalScrollRange
        var percentage = Math.abs(offset).toFloat() / maxScroll!!.toFloat()
        handleAlphaOnTitle(percentage)
        handleToolbarTitleVisibility(percentage)
    }
}