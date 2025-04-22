package com.devjg.chatapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.devjg.chatapp.ui.components.scaffold.ScaffoldComponent

@Composable
fun HomeScreen(
    navController: NavController,
) {
    ScaffoldComponent(navController, title = "Chat"){
        Column(modifier = Modifier.fillMaxSize()) {
            Text("Welcome!!", textAlign = TextAlign.Center)
        }
    }
}


