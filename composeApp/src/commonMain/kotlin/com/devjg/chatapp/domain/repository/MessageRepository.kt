package com.devjg.chatapp.domain.repository

import com.devjg.chatapp.core.Resource
import com.devjg.chatapp.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    suspend fun sendMessage(message: Message): Flow<Resource<String>>
    suspend fun getAllMessages(): Flow<Resource<List<Message>>>
    suspend fun getMessagesByChatRoomId(chatRoomId:String): Flow<Resource<List<Message>>>
}