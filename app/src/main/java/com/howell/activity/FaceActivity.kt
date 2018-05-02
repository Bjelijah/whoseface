package com.howell.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.howell.action.Config
import com.howell.bean.FaceBean
import com.howell.modules.face.FacePresenter
import com.howell.modules.face.IFaceContract
import com.howell.whoseface.R
import com.howellsdk.utils.Util
import com.squareup.picasso.Picasso
import java.util.*

class FaceActivity:AppCompatActivity() ,AppBarLayout.OnOffsetChangedListener,IFaceContract.IVew{



    val PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f
    val PERCENTAGE_TO_HIDE_TITLE_DETAILS    = 0.3f
    val PERCENTAGE_TO_SHOW_IMAGE            = 0.2f
    val ALPHA_ANIMATIONS_DURATION           = 200L

    var mIsTheTitleVisible                  = false
    var mIsTheTitleContainerVisible         = true
    var mIsTheFloatBarVisible               = true
    var mBean :FaceBean                    ?= null
    var mPresenter:IFaceContract.IPresenter?= null
    var mId:String ?=null
    var mTime:String?=null
    @BindView(R.id.face_fb)lateinit var mFb: FloatingActionButton
    @BindView(R.id.face_appbar)lateinit var mAppBar : AppBarLayout
    @BindView(R.id.face_tb)lateinit var mTb:Toolbar
    @BindView(R.id.face_title_tv_small)lateinit var mTvSmallTitle:TextView
    @BindView(R.id.face_title_ll)lateinit var mTitleContainer: LinearLayout
    @BindView(R.id.face_title_fl)lateinit var mTitleBk:FrameLayout
    @BindView(R.id.face_title_tv_main)lateinit var mTvMainTitle:TextView
    @BindView(R.id.face_title_tv_sec)lateinit var mTvSecondTitle:TextView
    @BindView(R.id.face_position)lateinit var mTVPosition:TextView
    @BindView(R.id.face_name)lateinit var mTvName:TextView
    @BindView(R.id.face_age)lateinit var mTvAge:TextView
    @BindView(R.id.face_sex)lateinit var mTvSex:TextView
    @BindView(R.id.face_phone)lateinit var mTvPhone:TextView
    @BindView(R.id.face_birthday)lateinit var mTvBirthday:TextView
    @BindView(R.id.face_city)lateinit var mTvCity:TextView
    @BindView(R.id.face_description)lateinit var mTvDescription:TextView
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
        bindPresenter()
        initView()

    }

    override fun onDestroy() {
        super.onDestroy()
        unbindPresenter()
    }



    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e("123","on new Intent")
        setIntent(intent)
        initFun(intent)
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_face,menu)

        return true
    }

    override fun onLoginResult(isOK: Boolean) {
        Log.i("123","on LoginResult")
        if(isOK){
            initFun(intent)
        }
    }

    override fun onQueryFaceResult(bean: FaceBean) {
        Log.e("123","on query face result  bean=$bean")
        mBean = bean
        var p = Picasso.Builder(this).build()
        Log.i("123","url = ${bean.imageUrl1}")
//        bean.imageUrl1 = "http://116.228.67.70:8800/DSC02453.jpg"
//        bean.imageUrl2 = "http://116.228.67.70:8800/DSC02455.jpg"
        p.load(bean.imageUrl1).into(mIvFace1)
        p.load(bean.imageUrl1).into(mIvCircle)
        p.load(bean.imageUrl2).into(mIvFace2)


        ////
        mTvMainTitle.text = "${mBean?.similarity}%"
        mTvSecondTitle.text = mBean?.msgTime
        mTvSmallTitle.text = if(mBean?.name.equals("")) getString(R.string.face_position_default) else mBean?.name
        mTVPosition.text = if(mBean?.name.equals("")) getString(R.string.face_position_default)else mBean?.name
        mTvName.text =if(mBean?.userName.equals("")) getString(R.string.face_name_default) else mBean?.userName
        mTvAge.text = if(mBean?.age == 0) getString(R.string.face_age_default) else  mBean?.age.toString()
        mTvSex.text = if(mBean?.sex.equals("")) getString(R.string.face_sex_default) else  mBean?.sex
        mTvPhone.text = if(mBean?.phone.equals(""))getString(R.string.face_phone_default)else mBean?.phone
        mTvBirthday.text = if(mBean?.birthday.equals(""))getString(R.string.face_default_unknown)else mBean?.birthday
        mTvCity.text = if(mBean?.city.equals(""))getString(R.string.face_default_unknown)else mBean?.city
        mTvGroup.text = if(mBean?.group.equals("")) getString(R.string.face_group_default) else  mBean?.group
        mTvSimilarity.text = "${mBean?.similarity}%"
        mTvTime.text = mBean?.msgTime
        mTvDescription.text = if(mBean?.description.equals(""))getString(R.string.face_description_default)else mBean?.description

        mId = mBean?.componentId
        mTime = mBean?.msgTime
    }

    override fun bindPresenter() {
        if (mPresenter==null)mPresenter = FacePresenter()
        mPresenter?.bindView(this)
        mPresenter?.init(this)
    }

    override fun unbindPresenter() {
        mPresenter?.unbindView()
        mPresenter = null
    }

    @OnClick(R.id.face_fb)
    fun onFbClick(){
        Log.i("123","on face fb click")
        var intent = Intent(this,HistoryActiviy::class.java)
        //todo set info
        intent.putExtra("id",mId).putExtra("time",mTime)
        startActivity(intent)
    }

    private fun initView(){
        mAppBar.addOnOffsetChangedListener(this)
        //FIXME  no menu
        /*
        mTb.inflateMenu(R.menu.menu_face)
        var menuItem = mTb.menu.findItem(R.id.menu_face_history)
        menuItem.setOnMenuItemClickListener {
            Log.i("123","history click")
            //TODO
            var intent = Intent(this,HistoryActiviy::class.java)
            //todo set info
            intent.putExtra("id",mId).putExtra("time",mTime)
            startActivity(intent)

            true
        }
            */
        startAlphaAnimation(mTvSmallTitle,0,View.INVISIBLE)

    }

    private fun initFun(intent: Intent?){
        if (Config.Debug)return
//        mBean = intent?.getSerializableExtra("face_bean") as FaceBean?
        var bean:FaceBean ?= null
        var id:String ?= null
        var time:String ?= null
        try {
            bean = intent?.getSerializableExtra("bean") as FaceBean
        }catch (e:Exception){e.printStackTrace()}
        try {
            id = intent?.getStringExtra("id")
        }catch (e:Exception){e.printStackTrace()}
        try {
            time = intent?.getStringExtra("time")
        }catch (e:Exception){e.printStackTrace()}
        Log.i("123","initFun  id=$id   time=$time")
        mId = id
        mTime = time
        if(bean!=null){
            onQueryFaceResult(bean)
        }else if(time==null && id!=null){
            mPresenter?.queryFace(id!!,null,null)
        }else if(time!=null && id!=null){
            var date:Date ?= null
            Log.i("123","time = $time")
            date = try{
                Util.ISODateString2ISODate(time)
            }catch (e:Exception){
                Util.DateString2Date(time)
            }
            Log.i("123","date=  $date")
//            var db = Util.plusMinute(date, -10)
            var db = Util.plusMinute(date,-2)
            var df = Util.plusMinute(date, 2)
            var timeb = Util.Date2ISODateString(db)
            var timef = Util.Date2ISODateString(df)
            Log.i("123", "  time=$time    tb=$timeb   tf=$timef")
            mPresenter?.queryFace(id!!,timeb,timef)
        }
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

    private fun handleFloatBarVisibility(percentage:Float){
        if (percentage>PERCENTAGE_TO_SHOW_IMAGE){
            if (mIsTheFloatBarVisible){
                mIsTheFloatBarVisible = false
                ViewCompat.animate(mFb).scaleY(0f).scaleX(0f).start()
            }
        }else{
            if (!mIsTheFloatBarVisible){
                mIsTheFloatBarVisible = true
                ViewCompat.animate(mFb).scaleY(1f).scaleX(1f).start()
            }
        }
    }

    private fun handleTitleBackgroud(percentage:Float){

    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, offset: Int) {
        var maxScroll = appBarLayout?.totalScrollRange
        var percentage = Math.abs(offset).toFloat() / maxScroll!!.toFloat()
        handleAlphaOnTitle(percentage)
        handleToolbarTitleVisibility(percentage)
        handleFloatBarVisibility(percentage)
        handleTitleBackgroud(percentage)
    }
}