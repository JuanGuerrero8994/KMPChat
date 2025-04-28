package com.devjg.chatapp.data.repository.auth

import com.devjg.chatapp.core.Resource
import com.devjg.chatapp.data.mapper.toDTO
import com.devjg.chatapp.data.model.response.ApiResponseDTO
import com.devjg.chatapp.data.remote.ChatApi
import com.devjg.chatapp.data.remote.Endpoints
import com.devjg.chatapp.data.remote.buildUrl
import com.devjg.chatapp.domain.model.User
import com.devjg.chatapp.domain.repository.AuthRespository
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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

    override suspend fun register(user: User): Flow<Resource<String>> = flow {

        emit(Resource.Loading)
        try {
            val requestDto = user.toDTO()

            val response = api.httpClient.post {
                buildUrl(endpoint = Endpoints.USER_REGISTER)
                contentType(ContentType.Application.Json)
                setBody(requestDto)
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

    override suspend fun changePassword(user: User, newPassword: String): Flow<Resource<String>> =
        flow {
            emit(Resource.Loading)
            try {
                val requestDto = user.toDTO()
                requestDto.newPassword = newPassword

                val response = api.httpClient.post {
                    buildUrl(endpoint = Endpoints.USER_CHANGE_PASSWORD)
                    contentType(ContentType.Application.Json)
                    setBody(requestDto)
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

    override suspend fun validateToken(token: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading)
        try {
            val response = api.httpClient.get {
                buildUrl(endpoint = Endpoints.USER_VALIDATE_TOKEN)
                header("Authorization", "Bearer $token")
            }

            val apiResponse = response.body<ApiResponseDTO<User>>()
            if (apiResponse.status == "Success") {
                emit(Resource.Success(apiResponse.data!!))
            } else {
                emit(Resource.Error(Exception("${apiResponse.messages}")))
            }
        } catch (e: Exception) {
            emit(Resource.Error(Exception(e.message)))
        }
    }


}