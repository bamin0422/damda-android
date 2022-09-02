package com.android.damda.repository

import android.util.LruCache
import com.android.damda.data.ImgItem

interface DadamReopsitory {
    suspend fun getCacheItems() : List<ImgItem>
    suspend fun getImgItems() : LruCache<String, String>
}