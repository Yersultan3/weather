package com.example.bankeurasia.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageStaticUrl(
    val url: String
) : Parcelable {

    override fun toString(): String {
        return url
    }
}