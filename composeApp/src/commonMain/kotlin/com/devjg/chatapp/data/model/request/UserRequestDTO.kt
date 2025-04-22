package com.devjg.chatapp.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class UserRequestDTO(
    val username: String? = null,
    val email: String,
    val password: String
)
