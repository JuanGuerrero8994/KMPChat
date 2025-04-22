package com.devjg.chatapp.domain.usecases

import com.devjg.chatapp.domain.model.User
import com.devjg.chatapp.domain.repository.AuthRespository



class AuthenticateUseCase(private val authRepository: AuthRespository) {
    suspend operator fun invoke(user: User, action: AuthAction) = when (action) {
        AuthAction.LOGIN -> authRepository.authenticate(user)
        AuthAction.REGISTER -> authRepository.register(user)
    }
}