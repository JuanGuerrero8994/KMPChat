package com.devjg.chatapp.ui.screen.chat

import com.devjg.chatapp.core.Resource
import com.devjg.chatapp.domain.model.Message
import com.devjg.chatapp.domain.usecases.chat.ChatAction
import com.devjg.chatapp.domain.usecases.chat.ChatUseCase
import com.devjg.chatapp.ui.screen.base.BaseViewModel
import kotlinx.coroutines.flow.Flow

class ChatViewModel(private val chatUseCase: ChatUseCase) :  BaseViewModel<String>() {


    fun connect(roomId: String) {
        fetchData(_state) { chatUseCase(action = ChatAction.CONNECT, roomId = roomId) }
    }

    fun sendMessage(message: Message) {
        fetchData(_state) { chatUseCase(action = ChatAction.SEND_MESSAGE, message = message) }
    }

    fun disconnect() {
        fetchData(_state) { chatUseCase(action = ChatAction.DISCONNECT) }
    }
}