package com.devjg.chatapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.devjg.chatapp.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "ChatApp",
    ) {
        App()
    }
}