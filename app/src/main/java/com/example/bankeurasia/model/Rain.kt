package com.example.bankeurasia.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rain(
    val `3h`: Double
): Parcelable