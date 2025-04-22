package com.devjg.chatapp.domain.repository

import com.devjg.chatapp.core.Resource
import com.devjg.chatapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRespository {
    suspend fun authenticate(user: User): Flow<Resource<String>>
    suspend fun register(user: User): Flow<Resource<String>>
    suspend fun validateToken(token: String): Flow<Resource<User>>

}