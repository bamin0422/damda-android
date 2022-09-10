package com.android.damda.ui.main.fragment.search

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.damda.R
import com.android.damda.data.ImgItem
import com.android.damda.databinding.FragmentSearchBinding
import com.android.damda.ui.main.fragment.photo.PhotoGridAdapter
import com.android.damda.ui.main.fragment.photo.PhotoViewModel

class SearchFragment : Fragment() {
    lateinit var binding : FragmentSearchBinding
    lateinit var searchViewModel : SearchViewModel
    private lateinit var searchData : MutableList<ImgItem>
    private lateinit var searchGrid : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()
        initViews(inflater, container)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initViews(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        searchGrid = binding.searchGrid
        searchGrid.layoutManager = GridLayoutManager(requireContext(), 4)
        searchGrid.adapter = SearchGridAdapter(requireContext(), searchData)
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun initViewModel() {
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        searchViewModel.searchData.observe(this, Observer {
            (searchGrid.adapter as SearchGridAdapter).setData(it)
        })
        searchData = searchViewModel.getSearchData()!!
    }
}