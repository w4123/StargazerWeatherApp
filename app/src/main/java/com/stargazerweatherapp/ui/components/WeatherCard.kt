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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
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
                Text(text = nonNullWeather.location.name, fontSize = 24.sp)
                Text(text = nonNullWeather.weatherType.description.capitalize(), fontSize = 18.sp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(id = nonNullWeather.weatherType.icon),
                        fontSize = 64.sp,
                        fontFamily = FontFamily(Font(R.font.weathericons))
                    )
                    Text(text = "${nonNullWeather.temperature}°C", fontSize = 48.sp)
                }
                Text(text = "WindDirection: ${nonNullWeather.windDirection}")
                Text(text = "Wind: ${nonNullWeather.windSpeed} m/s")
            }
        }
    }
}
