package com.devjg.chatapp.domain.repository

import com.devjg.chatapp.core.Resource
import com.devjg.chatapp.domain.model.Message
import kotlinx.coroutines.flow.Flow


interface ChatRepository {
    suspend fun connectToChat(roomId: String): Flow<Resource<String>>
    suspend fun sendMessage(message: Message) :Flow<Resource<String>>
    suspend fun disconnect():Flow<Resource<String>>
}