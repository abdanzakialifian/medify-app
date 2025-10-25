package com.medify.app.data.network.request

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class LoginRequest(
	@SerialName("email")
	val email: String,

	@SerialName("password")
	val password: String,
)
