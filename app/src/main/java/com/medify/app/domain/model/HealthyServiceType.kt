package com.medify.app.domain.model

import android.graphics.drawable.Drawable

data class HealthyServiceType(
    val id: Int,
    val name: String,
    val price: String,
    val hospitalName: String,
    val hospitalAddress: String,
    val image: Drawable?,
)
