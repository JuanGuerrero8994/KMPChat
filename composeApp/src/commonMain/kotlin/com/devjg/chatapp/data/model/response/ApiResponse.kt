package com.devjg.chatapp.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseDTO<T>(
    val data: T? = null,
    val messages: List<String>,
    val status: String,
    val code: Int
)