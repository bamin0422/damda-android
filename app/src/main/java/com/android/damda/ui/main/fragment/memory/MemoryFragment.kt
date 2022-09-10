package com.android.damda.ui.main.fragment.memory

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.damda.R
import com.android.damda.data.ImgItem
import com.android.damda.databinding.FragmentMemoryBinding
import com.android.damda.ui.main.fragment.photo.PhotoGridAdapter
import com.android.damda.ui.main.fragment.photo.PhotoViewModel

class MemoryFragment : Fragment() {
    private lateinit var binding : FragmentMemoryBinding
    private lateinit var memoryGrid : RecyclerView
    private var memoryData = mutableListOf<ImgItem>()
    private lateinit var memoryViewModel : MemoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_memory, container, false)
        initViewModel()
        initViews(inflater, container)
        return binding.root
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun initViewModel() {
        memoryViewModel = ViewModelProvider(this).get(MemoryViewModel::class.java)
        memoryViewModel.memoryData.observe(this, Observer {
            (memoryGrid.adapter as MemoryGridAdapter).setData(it)
        })
        memoryData = memoryViewModel.getMemoryData()!!
    }

    private fun initViews(inflater: LayoutInflater, container: ViewGroup?) {
        memoryGrid = binding.memoryGrid
        memoryGrid.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        memoryGrid.adapter = MemoryGridAdapter(requireContext(), memoryData)

    }
}