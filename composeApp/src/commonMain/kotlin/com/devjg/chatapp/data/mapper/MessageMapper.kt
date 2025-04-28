package com.devjg.chatapp.data.mapper

import com.devjg.chatapp.data.model.request.MessageRequestDto
import com.devjg.chatapp.data.model.response.MessageResponseDTO
import com.devjg.chatapp.domain.model.Message

// MessageMapper.kt
fun Message.toRequestDTO(): MessageRequestDto = MessageRequestDto(
    sender = this.sender ?: "",
    message = this.message ?: "",
    timestamp = this.timestamp ?: 0L,
    fileId = this.fileId,
    chatRoomId = this.chatRoomId
)

fun MessageResponseDTO.toDomain(): Message = Message(
    id = this.id,
    sender = this.sender,
    message = this.message,
    timestamp = this.timestamp,
    fileId = this.fileId,
    chatRoomId = this.chatRoomId
)