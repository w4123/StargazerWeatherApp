package com.stargazerweatherapp.data.models

import androidx.compose.runtime.Immutable

@Immutable
data class DailyWeather(//Class for storing data for days in the
    val date: String,
    val location: Location,
    val temperatureMax : Double,
    val temperatureMin : Double,
    val sunsetTime: String,
    val weatherType: WeatherType,
    val cloudCoverAtSunset: Int,
    val visibility: Int,
    val transparency: Transparency,
    val seeing : Int
){
    constructor(date: String,
                location: Location,
                temperatureMax : Double,
                temperatureMin : Double,
                sunsetTime: String,
                weatherType: WeatherType,
                cloudCoverAtSunset: Int,
                visibility: Int)
            : this(
        date,
        location,
        temperatureMax,
        temperatureMin,
        sunsetTime,
        weatherType,
        cloudCoverAtSunset,
        visibility,
        Transparency.Average,
        5
    )

    fun getSunset() : String {//Gets the sunset time string
        return sunsetTime.takeLast(5);
    }
}