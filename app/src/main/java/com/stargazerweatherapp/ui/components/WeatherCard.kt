package com.stargazerweatherapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
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

                Text(text = nonNullWeather.location.name,
                    fontSize = 36.sp,
                    modifier = Modifier.align(Alignment.Start).padding(horizontal=16.dp, vertical = 8.dp))

                Row(verticalAlignment = Alignment.Top,
                    modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text("17:00",
                        textAlign = TextAlign.Left,
                        fontSize = 26.sp
                    )
                    Spacer(Modifier.weight(1.0f))

                    Box(
                        modifier = Modifier.border(2.dp, Color.White).size(48.dp).padding(horizontal = 3.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.wi_moon_waxing_crescent_1),
                            fontSize = 36.sp,
                            fontFamily = FontFamily(Font(R.font.weathericons))
                        )
                    }

                    Box(
                        modifier = Modifier.border(2.dp, Color.White).size(48.dp).padding(horizontal = 3.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "5",
                            fontSize = 36.sp,
                            fontFamily = FontFamily(Font(R.font.weathericons))
                        )
                    }
                }
                Text(
                    text = stringResource(id = nonNullWeather.weatherType.icon),
                    fontSize = 120.sp,
                    fontFamily = FontFamily(Font(R.font.weathericons))
                )
                Text(text = nonNullWeather.weatherType.description, fontSize = 36.sp)
                Text(text = "${nonNullWeather.temperature}°C", fontSize = 36.sp)

                Text(text = "${nonNullWeather.cloudCover}%")
                Text(text = "${nonNullWeather.visibility}m")
            }
        }
    }
}
