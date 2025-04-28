package com.devjg.chatapp.ui.screen.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devjg.chatapp.core.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

// BaseViewModel with private _state and public state
open class BaseViewModel<T> : ViewModel() {

    // Make _state protected so that it can be accessed by subclasses
    protected val _state = MutableStateFlow<Resource<T>>(Resource.Loading)
    val state: StateFlow<Resource<T>> get() = _state

    /**
     * Función genérica para evitar repetición en los fetch
     */
    protected fun <T> fetchData(
        stateFlow: MutableStateFlow<Resource<T>>,
        useCase: suspend () -> Flow<Resource<T>>
    ) {
        viewModelScope.launch {
            useCase().collectLatest { resource ->
                stateFlow.value = resource
            }
        }
    }
}