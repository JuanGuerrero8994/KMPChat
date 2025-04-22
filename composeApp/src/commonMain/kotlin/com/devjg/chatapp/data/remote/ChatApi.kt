package com.devjg.chatapp.data.remote

import com.devjg.chatapp.createHttpClient
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient

class ChatApi {
    val httpClient: HttpClient by lazy { createHttpClient() }
    init { Napier.base(DebugAntilog()) }
}