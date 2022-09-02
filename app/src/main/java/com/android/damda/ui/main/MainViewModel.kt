package com.android.damda.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _currentValue = MutableLiveData<Int>()
    val currentValue: LiveData<Int>
        get() = _currentValue

    init {
        _currentValue.value = 0
    }

    fun updateValue(actionType: ActionType, input: Int){
        when(actionType){
            ActionType.PLUS ->
                _currentValue.value = _currentValue.value?.plus(input)
            ActionType.MINUS ->
                _currentValue.value = _currentValue.value?.minus(input)
        }
    }
}

enum class ActionType {
    PLUS, MINUS
}