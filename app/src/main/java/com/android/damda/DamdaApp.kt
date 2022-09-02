package com.android.damda

import android.app.Application

class DamdaApp : Application() {
    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        lateinit var INSTANCE : DamdaApp
    }
}