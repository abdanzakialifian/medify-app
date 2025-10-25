package com.medify.app.data.network.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class LoginResponse(
	@SerialName("token")
	val token: String? = null
)
