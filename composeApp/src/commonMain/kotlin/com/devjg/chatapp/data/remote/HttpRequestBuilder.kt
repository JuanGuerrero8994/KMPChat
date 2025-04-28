package com.devjg.chatapp.data.remote

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.appendPathSegments
import io.ktor.http.takeFrom

fun HttpRequestBuilder.buildUrl(endpoint: String, queryParams: Map<String, String> = emptyMap(), token: String? = null) {
    url {
        takeFrom(Endpoints.BASE_URL)
        appendPathSegments(endpoint)
        queryParams.forEach { (key, value) ->
            parameters.append(key, value)
        }
    }
    accept(ContentType.Application.Json)
    token?.let { headers.append("Authorization", "Bearer $it") }
}


