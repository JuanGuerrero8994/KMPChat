package com.devjg.chatapp.domain.usecases.message

import com.devjg.chatapp.domain.model.Message
import com.devjg.chatapp.domain.repository.MessageRepository

class MessageUseCase(
    private val messageRepository: MessageRepository
) {
    suspend operator fun invoke(action: MessageAction, message: Message? = null, chatRoomId: String? = null) =
        when (action) {
            MessageAction.SEND -> { messageRepository.sendMessage(message!!) }
            MessageAction.GET_ALL -> { messageRepository.getAllMessages() }
            MessageAction.GET_BY_CHAT_ROOM -> { messageRepository.getMessagesByChatRoomId(chatRoomId!!) }
        }
}