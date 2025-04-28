package com.devjg.chatapp.domain.repository

import com.devjg.chatapp.core.Resource
import com.devjg.chatapp.domain.model.Message
import kotlinx.coroutines.flow.Flow


interface ChatRepository {
    suspend fun connectToChat(roomId: String): Flow<Resource<Unit>>
    suspend fun sendMessage(message: Message)
    fun observeMessages(): Flow<Message>
    suspend fun disconnect()
}