package com.stargazerweatherapp.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.stargazerweatherapp.R
import com.stargazerweatherapp.data.models.DailyWeather

@Composable
fun FutureWeatherCardSmall(
    weatherData: List<DailyWeather>?,
    navController: NavController,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        if (weatherData != null) {
            items(weatherData) {
                WeatherItem(it, navController)
            }
        }
    }
}

@Composable
fun WeatherItem(weather: DailyWeather, navController: NavController) {
    Column(
        modifier = Modifier
            .clickable {
                Log.d(
                    "Euan",
                    "got here"
                )

                navController.navigate("FutureWeather/${weather.date}")
            }
            .requiredWidth(110.dp)
            .requiredHeight(160.dp)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = weather.date,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(id = weather.weatherType.icon),
            fontSize = 32.sp,
            fontFamily = FontFamily(Font(R.font.weathericons))
        )
        Text(
            text = weather.temperatureMin.toString() + "~" + weather.temperatureMax.toString() + "°C",
            fontSize = 10.sp
        )
        Text(
            text = weather.weatherType.description,
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )
        Text(
            text = "Sunset: " + weather.sunsetTime,
            textAlign = TextAlign.Center,
            fontSize = 10.sp
        )
        Text(
            text = "Cloud Cover: " + weather.cloudCoverAtSunset + "%",
            textAlign = TextAlign.Center,
            fontSize = 10.sp
        )
        Text(
            text = "Visibility: " + weather.visibility + "m",
            textAlign = TextAlign.Center,
            fontSize = 10.sp
        )
    }
}