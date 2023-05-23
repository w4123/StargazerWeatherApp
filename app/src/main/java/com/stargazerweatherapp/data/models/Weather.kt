package com.stargazerweatherapp.data.models

import androidx.compose.runtime.Immutable

@Immutable
data class Weather(//Record class for current days location. Because different data is available today
    val datetime: String,
    val location: Location,
    val temperature : Double,
    val windSpeed: Double,
    val windDirection: Double,
    val weatherType: WeatherType,
    val cloudCover : Int,
    val visibility : Int
)
