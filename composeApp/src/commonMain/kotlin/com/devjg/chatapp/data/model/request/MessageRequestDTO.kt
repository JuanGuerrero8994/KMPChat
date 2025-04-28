package com.devjg.chatapp.data.model.request

import com.devjg.chatapp.currentTimeMillis
import kotlinx.serialization.Serializable

@Serializable
data class MessageRequestDto(
    val sender: String,
    var message: String,
    val timestamp: Long = currentTimeMillis(),
    val fileId: String? = null,
    val chatRoomId:String?=null,
)
