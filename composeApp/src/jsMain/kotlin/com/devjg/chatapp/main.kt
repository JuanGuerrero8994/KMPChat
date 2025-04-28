package com.devjg.chatapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.CanvasBasedWindow
import com.devjg.chatapp.di.initKoin
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin()
    onWasmReady {
        CanvasBasedWindow(canvasElementId = "ComposeTarget") {
            Box(modifier = Modifier.width(1024.dp).height(768.dp))
            App()
        }
    }
}