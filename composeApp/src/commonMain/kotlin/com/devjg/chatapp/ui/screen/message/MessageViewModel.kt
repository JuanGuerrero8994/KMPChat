package com.devjg.chatapp.ui.screen.message

import com.devjg.chatapp.domain.model.Message
import com.devjg.chatapp.domain.usecases.message.MessageAction
import com.devjg.chatapp.domain.usecases.message.MessageUseCase
import com.devjg.chatapp.ui.screen.base.BaseViewModel

class MessageViewModel(private val messageUseCase: MessageUseCase) : BaseViewModel<Any>() {

    // Send a message
    fun sendMessage(message: Message) {
        fetchData(_state) { messageUseCase(action= MessageAction.SEND, message = message) }
    }

    // Get all messages
    fun getAllMessages() {
        fetchData(_state) { messageUseCase(action= MessageAction.GET_ALL) }
    }

    // Get messages by chat room ID
    fun getMessagesByChatRoomId(chatRoomId: String) {
        fetchData(_state) { messageUseCase(action = MessageAction.GET_BY_CHAT_ROOM, chatRoomId = chatRoomId) }
    }
}