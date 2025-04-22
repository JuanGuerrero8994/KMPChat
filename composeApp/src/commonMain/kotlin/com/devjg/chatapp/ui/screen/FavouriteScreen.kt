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
fun FavouriteScreen(
    navController: NavController,
) {
    ScaffoldComponent(navController, title = "Favourite"){
        Column(modifier = Modifier.fillMaxSize()) {
            Text("Favourite!!", textAlign = TextAlign.Center)
        }
    }
}


