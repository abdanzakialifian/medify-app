package com.medify.app.domain.model

import android.graphics.drawable.Drawable

data class HealthyProductItem(
    val id: Int,
    val rating: Int,
    val image: Drawable?,
    val name: String,
    val price: String,
    val stock: Int,
)
