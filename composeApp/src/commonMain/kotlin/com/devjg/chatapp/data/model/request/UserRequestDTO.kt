package com.devjg.chatapp.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class UserRequestDTO(val email: String, val password: String)
