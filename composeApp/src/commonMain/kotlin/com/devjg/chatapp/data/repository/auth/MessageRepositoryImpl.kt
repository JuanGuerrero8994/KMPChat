package com.devjg.chatapp.data.repository.auth

import com.devjg.chatapp.core.Resource
import com.devjg.chatapp.data.mapper.toDTO
import com.devjg.chatapp.data.mapper.toDomain
import com.devjg.chatapp.data.mapper.toRequestDTO
import com.devjg.chatapp.data.model.response.ApiResponseDTO
import com.devjg.chatapp.data.model.response.MessageResponseDTO
import com.devjg.chatapp.data.remote.ChatApi
import com.devjg.chatapp.data.remote.Endpoints
import com.devjg.chatapp.data.remote.buildUrl
import com.devjg.chatapp.domain.model.Message
import com.devjg.chatapp.domain.repository.MessageRepository
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MessageRepositoryImpl(private val api: ChatApi) : MessageRepository {

    // Sending message
    override suspend fun sendMessage(message: Message): Flow<Resource<String>> = flow {
        val messageRequest = message.toRequestDTO()
        emit(Resource.Loading)

        try {
            val response = api.httpClient.post {
                buildUrl(endpoint = Endpoints.MESSAGES_SEND)
                contentType(ContentType.Application.Json)
                setBody(messageRequest)
            }

            val apiResponse = response.body<ApiResponseDTO<String>>()
            if (apiResponse.status == "Success") {
                emit(Resource.Success("${apiResponse.messages}"))
            } else {
                emit(Resource.Error(Exception("${apiResponse.messages}")))
            }
        } catch (e: Exception) {
            emit(Resource.Error(Exception(e.message)))
        }
    }

    // Get all messages
    override suspend fun getAllMessages(): Flow<Resource<List<Message>>> = flow {
        emit(Resource.Loading)

        try {
            val response = api.httpClient.get {
                buildUrl(endpoint = Endpoints.MESSAGES_GET_ALL)
                contentType(ContentType.Application.Json)
            }

            val apiResponse = response.body<ApiResponseDTO<List<MessageResponseDTO>>>()

            if (apiResponse.status == "Success" && apiResponse.data != null) {
                emit(Resource.Success(apiResponse.data.map { it.toDomain() }))
            } else {
                emit(Resource.Error(Exception(apiResponse.messages.joinToString())))
            }
        } catch (e: Exception) {
            emit(Resource.Error(Exception(e.message)))
        }
    }

    // Get messages by chat room ID
    override suspend fun getMessagesByChatRoomId(chatRoomId: String): Flow<Resource<List<Message>>> = flow {
        emit(Resource.Loading)
        try {
            val response = api.httpClient.get {
                buildUrl(endpoint = "messages/chat/$chatRoomId")
            }
            val apiResponse = response.body<ApiResponseDTO<List<MessageResponseDTO>>>()

            if (apiResponse.status == "Success" && apiResponse.data != null) {
                emit(Resource.Success(apiResponse.data.map { it.toDomain() }))
            } else {
                emit(Resource.Error(Exception(apiResponse.messages.joinToString())))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }
}
