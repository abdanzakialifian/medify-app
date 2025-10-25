package com.medify.app.data.repository

import com.medify.app.data.network.MedifyNetworkApi
import com.medify.app.data.network.request.LoginRequest
import com.medify.app.domain.repository.MedifyRepository

class MedifyRepositoryImpl(private val networkApi: MedifyNetworkApi) : MedifyRepository {
    override suspend fun login(request: LoginRequest): Result<String> = runCatching { networkApi.login(request).token ?: "" }
}