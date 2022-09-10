package com.android.damda.ui.main.fragment.photo

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.damda.R
import com.android.damda.data.ImgItem
import com.android.damda.databinding.FragmentMemoryBinding
import com.android.damda.databinding.FragmentPhotoBinding
import com.android.damda.ui.main.MainViewModel
import com.android.damda.ui.main.fragment.memory.MemoryGridAdapter
import kotlin.math.max
import kotlin.math.min

class PhotoFragment : Fragment() {
    private lateinit var binding : FragmentPhotoBinding
    private lateinit var photoGrid : RecyclerView
    private var photoData = mutableListOf<ImgItem>()
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f
    private lateinit var photoViewModel : PhotoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photo, container, false)
        initViewModel()
        initViews(inflater, container)
        return binding.root
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun initViewModel() {
        photoViewModel = ViewModelProvider(this).get(PhotoViewModel::class.java)
        photoViewModel
        photoViewModel.photoData.observe(this, Observer {
            photoData = it
        })
        photoData = photoViewModel.getPhotoData()!!
        Log.d(TAG, "PhotoData : ${photoData.size}")
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            scaleFactor *= scaleGestureDetector.scaleFactor
            scaleFactor = max(0.1f, min(scaleFactor, 10.0f))

            return true
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initViews(inflater: LayoutInflater, container: ViewGroup?) {
        binding.root.setOnTouchListener { view, motionEvent ->
            scaleGestureDetector.onTouchEvent(motionEvent)
        }
        photoGrid = binding.photoGrid
        photoGrid.layoutManager = GridLayoutManager(requireContext(), 4)
        photoGrid.adapter = PhotoGridAdapter(requireContext(), photoData)
        scaleGestureDetector = ScaleGestureDetector(requireContext(), ScaleListener())
    }
}