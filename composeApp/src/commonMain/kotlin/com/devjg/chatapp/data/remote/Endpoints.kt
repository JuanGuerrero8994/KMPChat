package com.devjg.chatapp.data.remote


object Endpoints {

    const val BASE_URL = "http://localhost:8080/"

    //USERS
    const val USER_AUTH = "users/authenticate"
    const val USER_REGISTER = "users/register"
    const val USER_CHANGE_PASSWORD = "users/change-password"
    const val USER_VALIDATE_TOKEN = "users/validate-token"


    // MESSAGES
    const val MESSAGES_GET_ALL = "messages"
    const val MESSAGES_SEND = "messages"
    const val MESSAGES_GET_BY_CHAT_ROOM = "messages/{id}/messages"


}
