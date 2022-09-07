package com.android.damda.repository

import android.util.LruCache
import androidx.core.util.lruCache
import com.android.damda.data.ImgItem

class DadamRepositoryImpl : DadamReopsitory {
    override suspend fun getCacheItems(): MutableList<ImgItem> {
        return mutableListOf(ImgItem("title","date","imgsrc"))
    }

    override suspend fun getImgItems(): LruCache<String, String> {
        return lruCache(10)
    }

    /**
     * Get Items from Data and send data to each Fragment
     * @return MutableList<ImgItem>
     **/
    override fun getMemoryItems(): MutableList<ImgItem> {
        return mutableListOf()
    }

    override fun getPhotoItems(): MutableList<ImgItem> {
        return mutableListOf()
    }

    override fun getSearchItems(): MutableList<ImgItem> {
        return mutableListOf()
    }
}