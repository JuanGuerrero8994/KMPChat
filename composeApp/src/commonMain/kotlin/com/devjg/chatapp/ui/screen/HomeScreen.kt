package com.devjg.chatapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.devjg.chatapp.core.Resource
import com.devjg.chatapp.domain.model.Message
import com.devjg.chatapp.ui.components.base.BaseResourceComponent
import com.devjg.chatapp.ui.components.scaffold.BottomNavScreen
import com.devjg.chatapp.ui.components.scaffold.ScaffoldComponent
import com.devjg.chatapp.ui.navigation.Destinations
import com.devjg.chatapp.ui.screen.chat.ChatViewModel
import com.devjg.chatapp.ui.screen.message.MessageViewModel

@Composable
fun HomeScreen(
    chatViewModel: ChatViewModel,
    messageViewModel: MessageViewModel,
    navController: NavController,
) {
    val messageInput = remember { mutableStateOf("") }
    val chatRoomId = "67f68247912e873bec58ca8d"

    val chatStatus = chatViewModel.state.collectAsState()
    val messageStatus = messageViewModel.state.collectAsState()
    val stateLoading = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        chatViewModel.connect(chatRoomId)
        messageViewModel.getMessagesByChatRoomId(chatRoomId)
    }

    ScaffoldComponent(navController, title = "Chat Room:") {
        Column(modifier = Modifier.fillMaxSize()) {

            BaseResourceComponent(
                resource = chatStatus.value,
                isLoadingDialog = stateLoading.value,
                onSuccess = {
                    BaseResourceComponent(
                        resource = messageStatus.value,
                        isLoadingDialog = stateLoading.value,
                        onSuccess = { messages ->
                            stateLoading.value = false

                            Column(modifier = Modifier.fillMaxSize()) {
                                LazyColumn(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxSize()
                                ) {
                                    val itemMessage = messages as? Resource.Success<List<Message>>
                                    items(itemMessage?.data!!) { mes ->
                                        Row {
                                            Text(
                                                text = "${mes.sender}: ",
                                                color = Color.Gray
                                            )
                                            Text(
                                                text = "${mes.message}" ?: "",
                                                color = Color.Black
                                            )
                                        }
                                    }
                                }

                                // Input de mensaje + botón enviar
                                Row {
                                    TextField(
                                        value = messageInput.value,
                                        onValueChange = { messageInput.value = it },
                                        modifier = Modifier.weight(1f),
                                        placeholder = { Text("Escribe tu mensaje...") }
                                    )
                                    Button(onClick = {
                                        if (messageInput.value.isNotBlank()) {
                                            //messageViewModel.sendMessage(messageInput.value)
                                            messageInput.value = ""
                                        }
                                    }) {
                                        Text("Enviar")
                                    }
                                }
                            }
                        },
                        onError = { message ->
                            stateLoading.value = false
                            chatViewModel.disconnect()
                            Text(text = message, color = MaterialTheme.colors.error)
                        }
                    )
                },
                onError = { message ->
                    stateLoading.value = false
                    chatViewModel.disconnect()
                    Text(text = message, color = MaterialTheme.colors.error)
                }
            )
        }
    }
}

