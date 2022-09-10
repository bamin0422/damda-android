package com.android.damda.ui.main.fragment.search

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.android.damda.R
import com.android.damda.data.ImgItem
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.makeramen.roundedimageview.RoundedImageView

@GlideModule
class SearchGridAdapter internal constructor(
    private val context : Context,
    private var searchData: MutableList<ImgItem>)
    : RecyclerView.Adapter<SearchGridAdapter.SearchGridViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    var tracker: SelectionTracker<ImgItem>? = null

    @GlideModule
    inner class SearchGridViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val imgView: ImageView = itemView.findViewById(R.id.imagePost)

        fun setImgView(item: ImgItem) {
            Glide.with(context)
                .load(item.src) // 불러올 이미지 url
                .override(100,100)
//                .placeholder(defaultImage) // 이미지 로딩 시작하기 전 표시할 이미지
//                .error(defaultImage) // 로딩 에러 발생 시 표시할 이미지
//                .fallback(defaultImage) // 로드할 url 이 비어있을(null 등) 경우 표시할 이미지
                .into(imgView)
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<ImgItem> =
            object : ItemDetailsLookup.ItemDetails<ImgItem>() {
                override fun getPosition(): Int = adapterPosition
                override fun getSelectionKey(): ImgItem? = searchData[position]
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchGridViewHolder {
        val itemView = inflater.inflate(R.layout.item_container_basic, parent, false)
        Log.d(TAG,"onCreateViewHolder Called!!")
        return SearchGridViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchGridViewHolder, position: Int) {
        Log.d(TAG,"onCreateBindViewHolder Called!!")
        holder.setImgView(searchData[position])
    }

    override fun getItemCount(): Int {
        return searchData.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData:MutableList<ImgItem>){
        searchData = newData
        notifyDataSetChanged()
    }

    fun getItem(position: Int) = searchData[position]
    fun getPosition(title: String) = searchData.indexOfFirst { it.title == title }
}