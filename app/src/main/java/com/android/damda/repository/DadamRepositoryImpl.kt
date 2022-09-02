package com.android.damda.repository

import android.util.LruCache
import androidx.core.util.lruCache
import com.android.damda.data.ImgItem

class DadamRepositoryImpl : DadamReopsitory {
    override suspend fun getCacheItems(): List<ImgItem> {
        return listOf(ImgItem("title","date","imgsrc"))
    }

    override suspend fun getImgItems(): LruCache<String, String> {
        return lruCache(10)
    }

}