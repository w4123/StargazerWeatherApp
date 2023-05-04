package com.stargazerweatherapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stargazerweatherapp.R
import com.stargazerweatherapp.data.models.Weather

@Composable
fun WeatherCard(weather: Weather?, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth()) {
        weather?.let { nonNullWeather ->
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = nonNullWeather.latitude.toString() + "," + nonNullWeather.longitude.toString(), fontSize = 24.sp)
                Text(text = nonNullWeather.weather.description.capitalize(), fontSize = 18.sp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = getWeatherIcon(nonNullWeather.weather.id)),
                        contentDescription = "Weather Icon",
                        modifier = Modifier.size(64.dp),
                        contentScale = ContentScale.Fit
                    )
                    Text(text = "${nonNullWeather.temperature}°C", fontSize = 48.sp)
                }
                Text(text = "WindDirection: ${nonNullWeather.windDirection}")
                Text(text = "Wind: ${nonNullWeather.windSpeed} m/s")
            }
        }
    }
}

fun getWeatherIcon(id: Int): Int {
    // This function should return the corresponding weather icon resource ID based on the icon string provided.
    // You may use a mapping of icon codes to drawable resource IDs or another approach as needed.
    // For example:
    // return when (icon) {
    //     "01d" -> R.drawable.ic_clear_sky_day
    //     "01n" -> R.drawable.ic_clear_sky_night
    //     ...
    //     else -> R.drawable.ic_unknown
    // }
    return R.drawable.ic_launcher_background
}
