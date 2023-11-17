package com.example.bankeurasia.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherData(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherModel>,
    val message: Int
): Parcelable