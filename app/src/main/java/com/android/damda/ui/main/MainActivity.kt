package com.android.damda.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.damda.R
import com.android.damda.databinding.ActivityLoginBinding
import com.android.damda.databinding.ActivityMainBinding
import com.android.damda.ui.main.fragment.MemoryFragment
import com.android.damda.ui.main.fragment.PhotoFragment
import com.android.damda.ui.main.fragment.SearchFragment
import com.android.damda.ui.main.fragment.SettingsFragment

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var mainViewModel: MainViewModel
    var number : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.bnvMain.setOnItemSelectedListener {
            changeFragment(it.itemId)
            true
        }
        changeFragment(R.id.photo)
        binding.bnvMain.selectedItemId = R.id.photo
    }

    private fun changeFragment(menuItemId: Int){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, getFragment(menuItemId))
            .commitAllowingStateLoss()
    }

    private fun getFragment(menuItemId: Int): Fragment {
        when(menuItemId){
            R.id.memory -> return MemoryFragment()
            R.id.photo ->return  PhotoFragment()
            R.id.search -> return SearchFragment()
            else -> return SettingsFragment()
        }
    }

    private fun initViewModel() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.currentValue.observe(this, Observer {
            number = it
        })
    }

    override fun onClick(p0: View?) {

    }
}