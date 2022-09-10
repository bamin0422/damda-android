package com.android.damda.ui.photo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.damda.R
import com.android.damda.data.ImgItem
import com.android.damda.databinding.ActivityPhotoBinding
import com.android.damda.ui.main.fragment.photo.PhotoViewModel
import com.bumptech.glide.Glide

class PhotoActivity : AppCompatActivity() {

    lateinit var photoViewModel: PhotoActViewModel
    lateinit var binding : ActivityPhotoBinding
    private var photoData = mutableListOf<ImgItem>()
    lateinit var title : TextView
    private lateinit var closeBtn : Button
    lateinit var imgData : ImageView
    private var pos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
    }

    private fun initData() {
        pos = intent.extras?.get("now") as Int
        val frag = intent.extras!!.get("frag") as String
        initViewModel(frag)
        title.text = photoData[pos].title

        imgData = binding.imgData
        Glide.with(this)
            .load(photoData[pos].src) // 불러올 이미지 url
//                .placeholder(defaultImage) // 이미지 로딩 시작하기 전 표시할 이미지
//                .error(defaultImage) // 로딩 에러 발생 시 표시할 이미지
//                .fallback(defaultImage) // 로드할 url 이 비어있을(null 등) 경우 표시할 이미지
            .into(imgData)
    }

    private fun initView(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo)
        title = binding.photoTitle
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
            photoData = it
        })
    }
}