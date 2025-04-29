package com.devjg.chatapp.domain.usecases.chat

import com.devjg.chatapp.domain.model.Message
import com.devjg.chatapp.domain.repository.ChatRepository
import com.devjg.chatapp.domain.usecases.message.MessageAction

class ChatUseCase(private val repository: ChatRepository) {

    suspend operator fun invoke(action: ChatAction, roomId: String? = null,message: Message?=null) =
        when (action) {
            ChatAction.CONNECT -> repository.connectToChat(roomId!!)
            ChatAction.DISCONNECT -> repository.disconnect()
            ChatAction.SEND_MESSAGE -> repository.sendMessage(message!!)
        }

}