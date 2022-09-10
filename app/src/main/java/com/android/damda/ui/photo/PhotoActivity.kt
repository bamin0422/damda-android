package com.android.damda.ui.photo

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.damda.R
import com.android.damda.data.ImgItem
import com.android.damda.databinding.ActivityMainBinding
import com.android.damda.databinding.ActivityPhotoBinding
import com.android.damda.ui.main.fragment.memory.MemoryFragment
import com.android.damda.ui.main.fragment.photo.PhotoFragment
import com.android.damda.ui.main.fragment.photo.PhotoViewModel
import com.android.damda.ui.main.fragment.search.SearchFragment
import com.android.damda.ui.main.fragment.settings.SettingsFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PhotoActivity : AppCompatActivity() {

    lateinit var photoViewModel: PhotoViewModel
    lateinit var binding : ActivityPhotoBinding
    private var photoData = mutableListOf<ImgItem>()
    lateinit var title : TextView
    lateinit var closeBtn : Button
    private var pos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
        initViewModel()
    }

    private fun initData() {
        pos = intent.extras?.get("now") as Int
        val frag = intent.extras!!.get("frag")
    }

    private fun initView(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo)
        title = binding.photoTitle
        closeBtn = binding.closeBtn
        closeBtn.setOnClickListener {
            finish()
        }
    }

    private fun initViewModel() {
        photoViewModel = ViewModelProvider(this).get(PhotoViewModel::class.java)
        photoViewModel.photoData.observe(this, Observer {
            photoData = it
        })
    }
}