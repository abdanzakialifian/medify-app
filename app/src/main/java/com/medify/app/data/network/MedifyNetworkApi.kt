package com.medify.app.data.network

import com.medify.app.data.network.request.LoginRequest
import com.medify.app.data.network.response.LoginResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class MedifyNetworkApi(private val httpClient: HttpClient) {
    suspend fun login(request: LoginRequest): LoginResponse = httpClient.post(
        urlString = "login",
        block = {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    ).body()
}