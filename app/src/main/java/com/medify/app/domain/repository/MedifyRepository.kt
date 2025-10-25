package com.medify.app.domain.repository

import com.medify.app.data.network.request.LoginRequest

interface MedifyRepository {
    suspend fun login(request: LoginRequest): Result<String>
}