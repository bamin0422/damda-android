package com.android.damda.ui.photo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.android.damda.R
import com.android.damda.data.ImgItem
import com.android.damda.databinding.ActivityPhotoBinding
import com.android.damda.ui.main.fragment.photo.PhotoGridAdapter
import com.android.damda.ui.main.fragment.photo.PhotoViewModel
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView

class PhotoActivity : AppCompatActivity() {

    lateinit var photoViewModel: PhotoActViewModel
    lateinit var binding : ActivityPhotoBinding
    private var photoData = mutableListOf<ImgItem>()
    private lateinit var closeBtn : Button
    lateinit var imgData : PhotoView
    private var pos = 0
    lateinit var viewPager : ViewPager
    lateinit var viewPagerAdapter : ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
    }

    private fun initData() {
        pos = intent.extras?.get("now") as Int
        val frag = intent.extras!!.get("frag") as String
        initViewModel(frag)
    }

    private fun initView(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo)
        closeBtn = binding.closeBtn
        closeBtn.setOnClickListener {
            finish()
        }
    }

    private fun initViewModel(frag: String) {
        photoViewModel = ViewModelProvider(this).get(PhotoActViewModel::class.java)
        when(frag){
            "memory" -> photoData = photoViewModel.getMemoryData()!!
            "photo" -> photoData = photoViewModel.getPhotoData()!!
            "search" -> photoData = photoViewModel.getSearchData()!!
        }
        photoViewModel.curData.observe(this, Observer {
            (viewPager.adapter as ViewPagerAdapter).setData(it)
        })
        viewPager = binding.viewpager
        viewPagerAdapter = ViewPagerAdapter(this@PhotoActivity, photoData)
        viewPager.adapter = viewPagerAdapter
        viewPager.currentItem = pos
    }
}