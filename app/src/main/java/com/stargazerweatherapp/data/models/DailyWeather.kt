package com.stargazerweatherapp.data.models

import androidx.compose.runtime.Immutable

@Immutable
data class DailyWeather(
    val time: String,
    val location: Location,
    val temperatureMax : Double,
    val temperatureMin : Double,
    val sunsetTime: String,
    val weatherType: WeatherType,
    val cloudCoverAtSunset: Int
)