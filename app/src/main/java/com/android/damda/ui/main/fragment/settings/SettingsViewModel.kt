package com.android.damda.ui.main.fragment.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {
    private val _currentValue = MutableLiveData<Int>()
    val currentValue: LiveData<Int>
        get() = _currentValue

    init {
        _currentValue.value = 0
    }
}