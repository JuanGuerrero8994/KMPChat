package com.devjg.chatapp.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class MessageResponseDTO(
    val id: String,
    val sender: String,
    val message: String,
    val timestamp: Long,
    val fileId: String? = null,
    val chatRoomId: String? = null
)