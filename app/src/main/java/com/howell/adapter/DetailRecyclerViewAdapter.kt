package com.howell.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.howell.bean.DetailBean
import com.howell.whoseface.R
import java.util.*

class DetailRecyclerViewAdapter() :RecyclerView.Adapter<DetailRecyclerViewAdapter.ViewHoder>(){
    var mList :ArrayList<DetailBean>?=null
    var mContext:Context?=null
    constructor(c:Context,l:ArrayList<DetailBean>):this(){
        mContext = c
        mList = l
    }

    constructor(c:Context):this(){
        mContext = c
    }

    fun setData(l:ArrayList<DetailBean>){
        mList = l
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_detail,parent,false)
        return ViewHoder(v)
    }

    override fun getItemCount(): Int = mList?.size?:0

    override fun onBindViewHolder(holder: ViewHoder, position: Int) {
        initView(holder,mList?.get(position))
    }

    private fun initView(h:ViewHoder,bean:DetailBean?){
        h.cardNumber.text = "${bean?.number}"
        h.time.text = bean?.time
        h.value.text = "${bean?.value}"
        h.shop.text = bean?.shopName
    }

    inner class ViewHoder(v: View):RecyclerView.ViewHolder(v){
        var cardNumber :TextView = v.findViewById(R.id.item_detail_number)
        var time:TextView = v.findViewById(R.id.item_detail_time)
        var value :TextView = v.findViewById(R.id.item_detail_value)
        var shop:TextView = v.findViewById(R.id.item_detail_shop)
    }
}