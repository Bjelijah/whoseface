package com.howell.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.howell.action.Config
import com.howell.bean.FaceBean
import com.howell.utils.PhoneConfig
import com.howell.whoseface.R
import com.squareup.picasso.Picasso
import java.util.*

class HistroyRecyclerViewAdapter() :RecyclerView.Adapter<HistroyRecyclerViewAdapter.ViewHoder>() {

    var mList:ArrayList<FaceBean>?=null
    var mListener: OnItemClick?=null
    var mContext:Context?=null

    var mImageWidth = 0
    var mImageHeight = 0

    constructor(c: Context, l:ArrayList<FaceBean>) : this() {
        mList = l
        mContext = c
        initImageWidthHeight(c)
    }

    constructor(c:Context,l:ArrayList<FaceBean>,o:OnItemClick):this (){
        mList = l
        mListener = o
        mContext = c
        initImageWidthHeight(c)
    }

    constructor(c:Context,o:OnItemClick):this(){
        mListener = o
        mContext = c
        initImageWidthHeight(c)
    }

    private fun initImageWidthHeight(c:Context){
        var w = (PhoneConfig.getPhoneWidth(c)-32)/3.0f * 2
        var h = 390f/260f * w
        mImageWidth = w.toInt()
        mImageHeight = h.toInt()
    }

    fun setData(l:ArrayList<FaceBean>){
        mList = l
        notifyItemRangeChanged(0,mList?.size?:0)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_history,parent,false)
        return ViewHoder(v)
    }

    override fun getItemCount(): Int = mList?.size?:0

    override fun onBindViewHolder(holder: ViewHoder, position: Int) {
        var bean = mList?.get(position)
        init(holder,bean!!,position)
    }

    private fun initView(v:ImageView,url:String,id:Int){
        v.viewTreeObserver.addOnGlobalLayoutListener (object :ViewTreeObserver.OnGlobalLayoutListener{
            override fun onGlobalLayout() {
                var width = v.width
                if (width>0){
                    var height = 390f/260f * width
                    v.layoutParams = LinearLayout.LayoutParams(width,height.toInt()) as ViewGroup.LayoutParams?
                    if(id!=0) {
                        v.setImageDrawable(mContext?.getDrawable(id))
                    }else{
                       Picasso.Builder(mContext!!).build().load(url).into(v)
                    }

                }
                v.viewTreeObserver.removeOnGlobalLayoutListener (this)
            }
        })


    }

    private fun init(h:ViewHoder,bean:FaceBean,pos:Int){
        //todo do in
        if (Config.Debug) {
            test(h,bean)
        }else {
            doData(h, bean)
        }
    }
    private fun doData(h:ViewHoder,bean:FaceBean){
        h.mPos.text = if(bean.name.equals("")) mContext!!.getString(R.string.face_position_default) else bean.name
        h.mSimilarity.text =  "${bean.similarity}%"
        h.mTime.text = bean.msgTime
        initView(h.mFace1,bean.imageUrl1,0)
        initView(h.mFace2,bean.imageUrl2,0)
        h.mName.text = if(bean.userName.equals(""))mContext!!.getString(R.string.face_name_default)else bean.userName
        h.mAge.text = if(bean.age.equals(""))mContext!!.getString(R.string.face_age_default)else bean.age.toString()
        h.mSex.text = if(bean.sex.equals(""))mContext!!.getString(R.string.face_sex_default)else bean.sex
        h.mGroup.text = if(bean.group.equals(""))mContext!!.getString(R.string.face_group_default)else bean.group
        h.itemView.setOnClickListener {
            mListener?.onItemClick(bean)
        }
        h.mTimell.setOnClickListener{
            mListener?.onInfoClick(bean)
        }
    }


    private fun test(h:ViewHoder,bean:FaceBean){
        initView(h.mFace1,"",R.drawable.face1)
        initView(h.mFace2,"",R.drawable.face2)
        h.itemView.setOnClickListener {
            mListener?.onItemClick(bean)
        }
        h.mTimell.setOnClickListener{
            mListener?.onInfoClick(bean)
        }

    }


    interface OnItemClick{
        fun onItemClick(bean:FaceBean)
        fun onInfoClick(bean:FaceBean)
    }


    inner class ViewHoder(v: View):RecyclerView.ViewHolder(v){
        var mPos:TextView = v.findViewById(R.id.item_history_position)
        var mTime:TextView = v.findViewById(R.id.item_history_time)
        var mFace1:ImageView = v.findViewById(R.id.item_history_face1)
        var mFace2:ImageView = v.findViewById(R.id.item_history_face2)
        var mSimilarity:TextView = v.findViewById(R.id.item_history_similarity)
        var mName:TextView = v.findViewById(R.id.item_history_name)
        var mAge:TextView = v.findViewById(R.id.item_history_age)
        var mSex:TextView = v.findViewById(R.id.item_history_sex)
        var mGroup:TextView = v.findViewById(R.id.item_history_group)
        var mInfoBtn:ImageView = v.findViewById(R.id.item_history_info)
        var mTimell:RelativeLayout = v.findViewById(R.id.item_history_time_ll)
    }

}