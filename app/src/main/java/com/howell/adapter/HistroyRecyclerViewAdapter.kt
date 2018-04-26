package com.howell.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.howell.bean.FaceBean
import com.howell.utils.PhoneConfig
import com.howell.whoseface.R
import java.util.*

class HistroyRecyclerViewAdapter() :RecyclerView.Adapter<HistroyRecyclerViewAdapter.ViewHoder>() {

    var mList:ArrayList<FaceBean>?=null
    var mListener:OnItemClick?=null
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
                    v.layoutParams = LinearLayout.LayoutParams(width,height.toInt())
                    v.setImageDrawable(mContext?.getDrawable(id))
                }
                v.viewTreeObserver.removeOnGlobalLayoutListener (this)
            }
        })


    }

    private fun init(h:ViewHoder,bean:FaceBean,pos:Int){
        h.mHistoryTime.text = bean.msgTime

        //todo do in

        initView(h.mHistoryFace1,"",R.drawable.face1)
        initView(h.mHistoryFace2,"",R.drawable.face2)




    }

    interface OnItemClick{
        fun onItemClick(bean:FaceBean,pos:Int)
    }


    inner class ViewHoder(v: View):RecyclerView.ViewHolder(v){

        var mHistoryTime:TextView = v.findViewById(R.id.item_history_time)
        var mHistoryFace1:ImageView = v.findViewById(R.id.item_history_face1)
        var mHistoryFace2:ImageView = v.findViewById(R.id.item_history_face2)

    }

}