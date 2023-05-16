package com.stargazerweatherapp.data.models

import androidx.compose.runtime.Immutable

@Immutable
data class Weather(
    val time: String,
    val location: Location,
    val temperature : Double,
    val windSpeed: Double,
    val windDirection: Double,
    val weatherType: WeatherType,
    val cloudCover : Int,
    val visibility : Int
)
