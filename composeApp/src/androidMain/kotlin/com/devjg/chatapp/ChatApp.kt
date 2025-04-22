package com.devjg.chatapp

import android.app.Application
import com.devjg.chatapp.di.initKoin

class ChatApp: Application() {
    override fun onCreate() {
        initKoin()
        super.onCreate()
    }
}