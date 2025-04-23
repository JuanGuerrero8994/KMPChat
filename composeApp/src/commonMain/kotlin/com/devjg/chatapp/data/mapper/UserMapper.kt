package com.devjg.chatapp.data.mapper

import com.devjg.chatapp.data.model.request.UserRequestDTO
import com.devjg.chatapp.data.model.response.UserResponseDTO
import com.devjg.chatapp.domain.model.User

// Domain → DTO
fun User.toDTO(): UserRequestDTO {
    return UserRequestDTO(
        username= this.username,
        email = this.email,
        password = this.password
    )
}

// DTO → Domain
fun UserRequestDTO.toDomain(): User {
    return User(
        username=this.username,
        email = this.email,
        password = this.password
    )
}

// ResponseDTO → Domain
fun UserResponseDTO.toDomain(): User {
    return User(
        id = this.id,
        username = this.username,
        email = this.email
    )
}