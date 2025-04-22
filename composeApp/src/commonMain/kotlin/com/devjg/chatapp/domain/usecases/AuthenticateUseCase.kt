package com.devjg.chatapp.domain.usecases

import com.devjg.chatapp.domain.model.User
import com.devjg.chatapp.domain.repository.AuthRespository

class AuthenticateUseCase (private val authRespository: AuthRespository){
    suspend operator fun invoke(user: User) = authRespository.authenticate(user)
}