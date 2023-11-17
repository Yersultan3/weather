package com.example.bankeurasia.data

import com.example.bankeurasia.model.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApiService {
    @GET("forecast")
    suspend fun getHourlyForecast(
        @Query("q") city: String?,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): WeatherData
}