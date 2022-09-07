package com.android.damda.ui.main.fragment.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.android.damda.R
import com.android.damda.databinding.FragmentPhotoBinding
import com.android.damda.databinding.FragmentSearchBinding
import com.android.damda.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    lateinit var binding : FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        return binding.root
    }
}