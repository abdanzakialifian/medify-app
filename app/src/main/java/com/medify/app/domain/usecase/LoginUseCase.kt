package com.medify.app.domain.usecase

import com.medify.app.data.network.request.LoginRequest
import com.medify.app.domain.repository.MedifyRepository

class LoginUseCase(private val repository: MedifyRepository) {
    suspend operator fun invoke(request: LoginRequest): Result<String> = repository.login(request)
}