package com.android.damda.ui.main.fragment.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.android.damda.R
import com.android.damda.databinding.FragmentPhotoBinding
import com.android.damda.databinding.FragmentSearchBinding
import com.android.damda.databinding.FragmentSettingsBinding
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth

class SettingsFragment : Fragment() {
    lateinit var binding : FragmentSettingsBinding
    lateinit var userName : TextView
    lateinit var userProfile : ImageView
    lateinit var logoutBtn : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initView(inflater, container)
        return binding.root
    }

    private fun initView(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        userName = binding.userName
        userProfile = binding.profileImg
        logoutBtn = binding.logoutBtn

        userName.text = FirebaseAuth.getInstance().currentUser?.displayName
        Glide.with(this).load(FirebaseAuth.getInstance().currentUser?.photoUrl).into(userProfile)

        logoutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            activity?.finish()
        }
    }
}