package com.stargazerweatherapp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stargazerweatherapp.data.models.Astronomy
import com.stargazerweatherapp.data.models.Weather
import com.stargazerweatherapp.data.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class WeatherViewModel(
    private val weatherRepository: WeatherRepository = WeatherRepository()
) : ViewModel() {

    val weather = mutableStateOf<Weather?>(null)
    val astronomy = mutableStateOf<Astronomy?>(null)
    val isLoading = mutableStateOf(false)
    val isError = mutableStateOf(false)

    init {
        fetchWeatherAndAstronomyData()
    }

    private fun fetchWeatherAndAstronomyData() {
        isLoading.value = true
        isError.value = false

        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    val weatherData = weatherRepository.getWeatherData(0.0, 0.0);
                    //val astronomyData = weatherRepository.getAstronomyData()
                    weather.value = weatherData
                    astronomy.value = Astronomy(1)
                }
            } catch (e: Exception) {
                isError.value = true
            } finally {
                isLoading.value = false
            }
        }
    }
}
