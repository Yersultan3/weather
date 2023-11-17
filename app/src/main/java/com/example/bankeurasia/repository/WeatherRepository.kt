package com.example.bankeurasia.repository

import com.example.bankeurasia.BuildConfig
import com.example.bankeurasia.base.BaseRepository
import com.example.bankeurasia.data.WeatherApiService
import com.example.bankeurasia.model.WeatherData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private val apiService: WeatherApiService) :
    BaseRepository() {
    suspend fun getHourlyForecast(city: String?): Result<WeatherData> {
        return execute {
            apiService.getHourlyForecast(city, BuildConfig.OPEN_WEATHER_MAP_API_KEY)
        }
    }
}