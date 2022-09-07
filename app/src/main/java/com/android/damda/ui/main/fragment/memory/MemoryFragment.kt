package com.android.damda.ui.main.fragment.memory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.android.damda.R
import com.android.damda.databinding.FragmentMemoryBinding
import com.android.damda.databinding.FragmentPhotoBinding

class MemoryFragment : Fragment() {
    lateinit var binding : FragmentMemoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_memory, container, false)
        return binding.root
    }
}