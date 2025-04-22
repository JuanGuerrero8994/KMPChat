package com.devjg.chatapp.data.repository.auth

import com.devjg.chatapp.core.Resource
import com.devjg.chatapp.data.mapper.toDTO
import com.devjg.chatapp.data.mapper.toDomain
import com.devjg.chatapp.data.model.request.UserRequestDTO
import com.devjg.chatapp.data.model.response.ApiResponseDTO
import com.devjg.chatapp.data.model.response.UserResponseDTO
import com.devjg.chatapp.data.remote.ChatApi
import com.devjg.chatapp.data.remote.Endpoints
import com.devjg.chatapp.data.remote.buildUrl
import com.devjg.chatapp.domain.model.User
import com.devjg.chatapp.domain.repository.AuthRespository
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthRepositoryImpl(private val api: ChatApi) : AuthRespository {
    override suspend fun authenticate(user: User): Flow<Resource<String>> = flow {
        emit(Resource.Loading)

        val requestDto = user.toDTO()

        try {
            val response = api.httpClient.post {
                buildUrl(endpoint = Endpoints.USER_AUTH)
                contentType(ContentType.Application.Json)
                setBody(requestDto)
            }
            // 🔐 El token es un String, no un objeto complejo
            val apiResponse = response.body<ApiResponseDTO<String>>()
            val token = apiResponse.data

            if (token != null) {
                emit(Resource.Success(token))
            } else {
                emit(Resource.Error(Exception(" ${apiResponse.messages}")))
            }
        } catch (e: Exception) {
            emit(Resource.Error(Exception(e.message)))
        }
    }

    override suspend fun register(user: User): Flow<Resource<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun validateToken(token: String): Flow<Resource<User>> {
        TODO("Not yet implemented")
    }


}