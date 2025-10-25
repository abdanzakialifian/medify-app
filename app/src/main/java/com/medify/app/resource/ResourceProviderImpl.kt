package com.medify.app.resource

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources

class ResourceProviderImpl(private val context: Context) : ResourceProvider {
    override fun getString(resId: Int): String = context.getString(resId)
    override fun getDrawable(resId: Int): Drawable? = AppCompatResources.getDrawable(context, resId)
}