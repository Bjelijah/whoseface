package com.howell.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.howell.bean.FaceBean
import com.howell.whoseface.R
import java.util.*

class HistroyRecyclerViewAdapter() :RecyclerView.Adapter<HistroyRecyclerViewAdapter.ViewHoder>() {

    var mList:ArrayList<FaceBean>?=null
    var mListener:OnItemClick?=null



    constructor(l:ArrayList<FaceBean>) : this() {
        mList = l
    }

    constructor(l:ArrayList<FaceBean>,o:OnItemClick):this (){
        mList = l
        mListener = o
    }

    constructor(o:OnItemClick):this(){
        mListener = o
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

    private fun init(h:ViewHoder,bean:FaceBean,pos:Int){
        h.mHistoryTime.text = bean.msgTime
    }

    interface OnItemClick{
        fun onItemClick(bean:FaceBean,pos:Int)
    }


    inner class ViewHoder(v: View):RecyclerView.ViewHolder(v){
        var mHistoryTime:TextView = v.findViewById(R.id.item_history_time)
    }

}