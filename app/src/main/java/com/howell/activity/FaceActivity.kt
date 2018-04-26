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
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.howell.bean.FaceBean
import com.howell.whoseface.R
import org.w3c.dom.Text

class FaceActivity:AppCompatActivity() ,AppBarLayout.OnOffsetChangedListener{

    val PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f
    val PERCENTAGE_TO_HIDE_TITLE_DETAILS    = 0.3f
    val ALPHA_ANIMATIONS_DURATION           = 200L

    var mIsTheTitleVisible                  = false
    var mIsTheTitleContainerVisible         = true
    var mBean :FaceBean                    ?= null


    @BindView(R.id.face_appbar)lateinit var mAppBar : AppBarLayout
    @BindView(R.id.face_tb)lateinit var mTb:Toolbar
    @BindView(R.id.face_title_tv_small)lateinit var mTvSmallTitle:TextView
    @BindView(R.id.face_title_ll)lateinit var mTitleContainer: LinearLayout

    @BindView(R.id.face_title_tv_main)lateinit var mTvMainTitle:TextView
    @BindView(R.id.face_title_tv_sec)lateinit var mTvSecondTitle:TextView
    @BindView(R.id.face_name)lateinit var mTvName:TextView
    @BindView(R.id.face_age)lateinit var mTvAge:TextView
    @BindView(R.id.face_sex)lateinit var mTvSex:TextView
    @BindView(R.id.face_group)lateinit var mTvGroup:TextView
    @BindView(R.id.face_similarity)lateinit var mTvSimilarity:TextView
    @BindView(R.id.face_time)lateinit var mTvTime:TextView
    @BindView(R.id.face_face1)lateinit var mIvFace1:ImageView
    @BindView(R.id.face_face2)lateinit var mIvFace2:ImageView
    @BindView(R.id.face_circle_iv)lateinit var mIvCircle:ImageView

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
            //TODO
            var intent = Intent(this,HistoryActiviy::class.java)
            //todo set info

            startActivity(intent)

            true
        }
        startAlphaAnimation(mTvSmallTitle,0,View.INVISIBLE)
    }

    private fun initFun(intent: Intent?){
        mBean = intent?.getSerializableExtra("face_bean") as FaceBean?
        print("mBean= $mBean")
        //TODO do in thread
        mIvFace1.setImageDrawable(getDrawable(R.drawable.face1))
        mIvFace2.setImageDrawable(getDrawable(R.drawable.face2))
        mIvCircle.setImageDrawable(getDrawable(R.drawable.face2))
        ////


        mTvMainTitle.text = "${mBean?.similarity}%"
        mTvSecondTitle.text = mBean?.msgTime
        mTvSmallTitle.text = if(mBean?.name.equals("")) getString(R.string.face_name_default) else mBean?.name
        mTvName.text =if(mBean?.name.equals("")) getString(R.string.face_name_default) else mBean?.name
        mTvAge.text = if(mBean?.age == 0) getString(R.string.face_age_default) else  mBean?.age.toString()
        mTvSex.text = if(mBean?.sex.equals("")) getString(R.string.face_sex_default) else  mBean?.sex
        mTvGroup.text = if(mBean?.group.equals("")) getString(R.string.face_group_default) else  mBean?.group
        mTvSimilarity.text = "${mBean?.similarity}%"
        mTvTime.text = mBean?.msgTime

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
                startAlphaAnimation(mTvSmallTitle,ALPHA_ANIMATIONS_DURATION,View.VISIBLE)
            }
        }else{
            if (mIsTheTitleVisible){
                startAlphaAnimation(mTvSmallTitle,ALPHA_ANIMATIONS_DURATION,View.INVISIBLE)
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