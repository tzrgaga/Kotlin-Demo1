package com.example.core

import android.app.Application
import android.content.Context

class BaseApplication : Application() {

    //伴生对象 ，内部维护一个单例对象
    companion object {
        private lateinit var currentApplication: Context

        fun currentApplication(): Context {
            return currentApplication
        }

    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        currentApplication=this
    }
}