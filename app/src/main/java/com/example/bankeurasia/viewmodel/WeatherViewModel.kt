package com.example.bankeurasia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankeurasia.model.WeatherData
import com.example.bankeurasia.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {
    private val _weatherData = MutableLiveData<Result<WeatherData>>()
    val weatherData: LiveData<Result<WeatherData>> get() = _weatherData

    fun getHourlyForecast(city: String?) {
        viewModelScope.launch {
            val response = repository.getHourlyForecast(city)
            if (response.isSuccess) {
                _weatherData.value = response
            } else {
                _weatherData.value = Result.failure(Exception())
            }
        }
    }
}
