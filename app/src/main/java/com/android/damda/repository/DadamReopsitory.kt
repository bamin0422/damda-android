package com.android.damda.repository

import android.util.LruCache
import com.android.damda.data.ImgItem

interface DadamReopsitory {
    suspend fun getCacheItems() : MutableList<ImgItem>
    suspend fun getImgItems() : LruCache<String, String>

    /**
     * Get Items from Data and send data to each Fragment
     * @return MutableList<ImgItem>
     **/
    fun getMemoryItems() : MutableList<ImgItem>
    fun getPhotoItems() : MutableList<ImgItem>
    fun getSearchItems() : MutableList<ImgItem>
}