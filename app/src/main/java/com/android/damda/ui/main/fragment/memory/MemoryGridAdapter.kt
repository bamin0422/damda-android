package com.android.damda.ui.main.fragment.memory

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.android.damda.R
import com.android.damda.data.ImgItem
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView

class MemoryGridAdapter internal constructor(
    private val context : Context,
    private var memoryData: MutableList<ImgItem>)
    : RecyclerView.Adapter<MemoryGridAdapter.MemoryGridViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    var tracker: SelectionTracker<ImgItem>? = null

    inner class MemoryGridViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val imgView: RoundedImageView = itemView.findViewById(R.id.imagePost)

        fun setImgView(item: ImgItem) {
            Glide.with(context)
                .load(item.src) // 불러올 이미지 url
                .override(640,640)
//                .placeholder(defaultImage) // 이미지 로딩 시작하기 전 표시할 이미지
//                .error(defaultImage) // 로딩 에러 발생 시 표시할 이미지
//                .fallback(defaultImage) // 로드할 url 이 비어있을(null 등) 경우 표시할 이미지
                .into(imgView)
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<ImgItem> =
            object : ItemDetailsLookup.ItemDetails<ImgItem>() {
                override fun getPosition(): Int = adapterPosition
                override fun getSelectionKey(): ImgItem? = memoryData[position]
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoryGridViewHolder {
        val itemView = inflater.inflate(R.layout.item_container, parent, false)
        Log.d(TAG,"onCreateViewHolder Called!!")
        return MemoryGridViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MemoryGridViewHolder, position: Int) {
        Log.d(TAG,"onCreateBindViewHolder Called!!")
        holder.setImgView(memoryData[position])
    }

    override fun getItemCount(): Int {
        return memoryData.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData:MutableList<ImgItem>){
        memoryData = newData
        notifyDataSetChanged()
    }

    fun getItem(position: Int) = memoryData[position]
    fun getPosition(title: String) = memoryData.indexOfFirst { it.title == title }
}