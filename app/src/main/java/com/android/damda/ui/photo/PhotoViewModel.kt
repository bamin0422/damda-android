package com.android.damda.ui.photo

import androidx.lifecycle.*
import com.android.damda.data.ImgItem
import com.android.damda.ui.main.fragment.memory.MemoryViewModel
import com.android.damda.ui.main.fragment.photo.PhotoViewModel
import com.android.damda.ui.main.fragment.search.SearchViewModel

class PhotoActViewModel : ViewModel(), ViewModelStoreOwner {
    val curData = MutableLiveData<MutableList<ImgItem>>()
    private var data = mutableListOf<ImgItem>()

    fun getData() : MutableList<ImgItem>? {
        return curData.value
    }

    fun getMemoryData(): MutableList<ImgItem>? {
        val memoryViewModel = ViewModelProvider(this)[MemoryViewModel::class.java]
        data = memoryViewModel.getMemoryData()!!
        curData.value = data
        return curData.value
    }

    fun getPhotoData(): MutableList<ImgItem>? {
        val photoViewModel = ViewModelProvider(this)[PhotoViewModel::class.java]
        data = photoViewModel.getPhotoData()!!
        curData.value = data
        return curData.value
    }

    fun getSearchData(): MutableList<ImgItem>? {
        val searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        data = searchViewModel.getSearchData()!!
        curData.value = data
        return curData.value
    }

    override fun getViewModelStore(): ViewModelStore {
        return ViewModelStore()
    }
}