package com.devjg.chatapp.domain.usecases.chat

import com.devjg.chatapp.domain.model.Message
import com.devjg.chatapp.domain.repository.ChatRepository

class ChatUseCase(private val repository: ChatRepository) {

    suspend operator fun invoke(action: ChatAction, roomId: String, message: Message) =
        when (action) {
            ChatAction.CONNECT -> repository.connectToChat(roomId)
            ChatAction.DISCONNECT -> repository.disconnect()
            ChatAction.SEND_MESSAGE -> repository.sendMessage(message)
            ChatAction.OBSERVE_MESSAGES -> repository.observeMessages()
        }

}