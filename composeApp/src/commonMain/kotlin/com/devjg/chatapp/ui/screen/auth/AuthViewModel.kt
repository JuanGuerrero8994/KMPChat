package com.devjg.chatapp.ui.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devjg.chatapp.core.Resource
import com.devjg.chatapp.domain.model.User
import com.devjg.chatapp.domain.usecases.AuthAction
import com.devjg.chatapp.domain.usecases.AuthenticateUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AuthViewModel(private val authUseCase: AuthenticateUseCase):ViewModel() {

    private val _authState = MutableStateFlow<Resource<String>>(Resource.Loading)
    val authState: StateFlow<Resource<String>> get() = _authState


    /**
     * Función genérica para evitar repetición en los fetch
     */
    private fun <T> fetchData(stateFlow: MutableStateFlow<Resource<T>>, useCase: suspend () -> kotlinx.coroutines.flow.Flow<Resource<T>>) {
        viewModelScope.launch {
            useCase().collectLatest { resource ->
                stateFlow.value = resource
            }
        }
    }

    fun authenticate(user: User) = fetchData(_authState) {
        authUseCase(user, AuthAction.LOGIN)
    }

    fun register(user: User) = fetchData(_authState) {
        authUseCase(user, AuthAction.REGISTER)
    }

}