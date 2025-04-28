package com.devjg.chatapp.ui.screen.auth

import com.devjg.chatapp.domain.model.User
import com.devjg.chatapp.domain.usecases.auth.AuthAction
import com.devjg.chatapp.domain.usecases.auth.AuthenticateUseCase
import com.devjg.chatapp.ui.screen.base.BaseViewModel

class AuthViewModel(private val authUseCase: AuthenticateUseCase) : BaseViewModel<String>() {

    // Authenticate user
    fun authenticate(user: User) {
        fetchData(_state) {
            authUseCase(user, action = AuthAction.LOGIN)
        }
    }

    // Register new user
    fun register(user: User) {
        fetchData(_state) {
            authUseCase(user, action = AuthAction.REGISTER)
        }
    }

    // Change user password
    fun changePassword(user: User, newPassword: String) {
        fetchData(_state) {
            authUseCase(user, newPassword = newPassword, action = AuthAction.FORGOT_PASSWORD)
        }
    }
}