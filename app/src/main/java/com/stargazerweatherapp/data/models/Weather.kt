package com.stargazerweatherapp.data.models

import androidx.compose.runtime.Immutable

@Immutable
data class Weather(
    val latitude: Double,
    val longitude: Double,
    val temperature : Double,
    val windSpeed: Double,
    val windDirection: Double,
    val weather: WeatherType
)
