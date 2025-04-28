package com.devjg.chatapp.ui.screen.chat

import com.devjg.chatapp.domain.model.Message
import com.devjg.chatapp.domain.usecases.chat.ChatAction
import com.devjg.chatapp.domain.usecases.chat.ChatUseCase
import com.devjg.chatapp.domain.usecases.message.MessageUseCase
import com.devjg.chatapp.ui.screen.base.BaseViewModel

class ChatViewModel<T>(private val chatUseCase: ChatUseCase) : BaseViewModel<T>() {

    fun connect(roomId: String) {
        fetchData(_state) {
            chatUseCase(ChatAction.CONNECT(roomId))
        }
        observeIncomingMessages()
    }

    private fun observeIncomingMessages() {
        fetchData(_state) {
            chatUseCase(ChatAction.OBSERVE_MESSAGES)
        }
    }

    fun sendMessage(message: Message) {
        fetchData(_state) {
            chatUseCase(ChatAction.SEND_MESSAGE,message = message)
        }
    }

    fun disconnect() {
        fetchData(_state) {
            chatUseCase(ChatAction.DISCONNECT)
        }
    }
}