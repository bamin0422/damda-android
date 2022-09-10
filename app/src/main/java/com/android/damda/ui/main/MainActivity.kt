package com.android.damda.ui.main

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.damda.R
import com.android.damda.databinding.ActivityMainBinding
import com.android.damda.ui.main.fragment.memory.MemoryFragment
import com.android.damda.ui.main.fragment.photo.PhotoFragment
import com.android.damda.ui.main.fragment.search.SearchFragment
import com.android.damda.ui.main.fragment.settings.SettingsFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var binding : ActivityMainBinding
    lateinit var floatingButton: FloatingActionButton
    var number : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        floatingButton = binding.fab
        floatingButton.setOnClickListener {

        }

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
            R.id.memory -> {
                floatingButton.visibility = View.GONE
                return MemoryFragment()
            }
            R.id.photo ->{
                floatingButton.visibility = View.VISIBLE
                return  PhotoFragment()
            }
            R.id.search -> {
                floatingButton.visibility = View.GONE
                return SearchFragment()
            }
            else -> {
                floatingButton.visibility = View.GONE
                return SettingsFragment()
            }
        }
    }

    private fun initViewModel() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.currentValue.observe(this, Observer {
            number = it
        })
    }
}