package com.devjg.chatapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.devjg.chatapp.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "ChatApp",
    ) {
        Box(modifier = Modifier.width(800.dp).height(600.dp)) {
            App()
        }
    }
}