package com.devjg.chatapp.data.repository.auth

import com.devjg.chatapp.core.Resource
import com.devjg.chatapp.data.mapper.toRequestDTO
import com.devjg.chatapp.data.remote.ChatApi
import com.devjg.chatapp.data.remote.Endpoints.BASE_URL
import com.devjg.chatapp.domain.model.Message
import com.devjg.chatapp.domain.repository.ChatRepository
import io.ktor.client.plugins.websocket.sendSerialized
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.serialization.json.Json

class ChatRepositoryImpl(private val apiChatApi: ChatApi) : ChatRepository {

    private var socketSession: io.ktor.client.plugins.websocket.DefaultClientWebSocketSession? = null
    private val incomingMessages = Channel<Message>(Channel.BUFFERED)

    override suspend fun connectToChat(roomId: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading)

        try {
            val socketUrl = buildString {
                append("ws://")
                append(BASE_URL.removePrefix("http://").removeSuffix("/"))
                append("/chat/")
                append(roomId)
            }

            apiChatApi.httpClient.webSocket(urlString = socketUrl) {
                socketSession = this
                emit(Resource.Success("Connected"))

                for (frame in incoming) {
                    if (frame is io.ktor.websocket.Frame.Text) {
                        val jsonText = frame.readText()

                        try {
                            val message = Json.decodeFromString<Message>(jsonText)
                            incomingMessages.send(message)
                        } catch (e: Exception) {
                            emit(Resource.Error(Exception(e.message)))
                        }
                    }
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }

     override suspend fun sendMessage(message: Message):Flow<Resource<String>> = flow {
         emit(Resource.Success("Mensaje enviado"))
         socketSession?.sendSerialized(message.toRequestDTO())
     }



    override suspend fun disconnect(): Flow<Resource<String>> = flow {
        try {
            socketSession?.close()
            socketSession = null
            emit(Resource.Success("Not Connected"))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }

}


