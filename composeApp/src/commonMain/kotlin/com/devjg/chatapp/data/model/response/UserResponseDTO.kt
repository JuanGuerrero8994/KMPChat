package com.devjg.chatapp.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDTO(
    val id: String,
    val username: String,
    val email: String
)