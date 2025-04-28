package com.devjg.chatapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.devjg.chatapp.ui.components.scaffold.ScaffoldComponent

@Composable
fun HomeScreen(
    roomId: String,
    navController: NavController,
) {
    val messages = remember { mutableStateListOf<String>() }
    val messageInput = remember { mutableStateOf("") }


    ScaffoldComponent(navController, title = "Chat Room: $roomId") {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(messages) { message ->
                    Text(text = message)
                }
            }

            Row {
                TextField(
                    value = messageInput.value,
                    onValueChange = { messageInput.value = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Type your message...") }
                )
                Button(onClick = {
                   /// chatManager.send(messageInput.value)
                    messageInput.value = ""
                }) {
                    Text("Send")
                }
            }
        }
    }
}


