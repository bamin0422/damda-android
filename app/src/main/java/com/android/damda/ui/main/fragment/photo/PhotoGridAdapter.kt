package com.android.damda.ui.main.fragment.photo

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
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
import com.android.damda.ui.photo.PhotoActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.makeramen.roundedimageview.RoundedImageView

@GlideModule
class PhotoGridAdapter internal constructor(
    private val context : Context,
    private var photoData: MutableList<ImgItem>)
    : RecyclerView.Adapter<PhotoGridAdapter.PhotoGridViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    var tracker: SelectionTracker<ImgItem>? = null

    @GlideModule
    inner class PhotoGridViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
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
                override fun getSelectionKey(): ImgItem? = photoData[position]
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridViewHolder {
        val itemView = inflater.inflate(R.layout.item_container_basic, parent, false)
        return PhotoGridViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PhotoGridViewHolder, position: Int) {
        holder.setImgView(photoData[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(context, PhotoActivity::class.java)
            intent.putExtra("now", position)
            intent.putExtra("frag", "photo")
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return photoData.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData:MutableList<ImgItem>){
        photoData = newData
        notifyDataSetChanged()
    }

    fun getItem(position: Int) = photoData[position]
    fun getPosition(title: String) = photoData.indexOfFirst { it.title == title }
}