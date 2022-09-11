package com.android.damda.ui.photo

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.android.damda.R
import com.android.damda.data.ImgItem
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import java.util.*

class ViewPagerAdapter(val context: Context, var imageList: MutableList<ImgItem>) : PagerAdapter() {

    override fun getCount(): Int {
        return imageList.size
    }

    // on below line we are returning the object
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val itemView: View = mLayoutInflater.inflate(R.layout.viewpager_content, container, false)


        val imageView: PhotoView = itemView.findViewById<View>(R.id.photo_view) as PhotoView

        Glide.with(context)
            .load(imageList[position].src) // 불러올 이미지 url
//                .placeholder(defaultImage) // 이미지 로딩 시작하기 전 표시할 이미지
//                .error(defaultImage) // 로딩 에러 발생 시 표시할 이미지
//                .fallback(defaultImage) // 로드할 url 이 비어있을(null 등) 경우 표시할 이미지
            .into(imageView)

        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData:MutableList<ImgItem>){
        imageList = newData
        notifyDataSetChanged()
    }
}