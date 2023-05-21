package com.stargazerweatherapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stargazerweatherapp.data.models.DailyWeather
import com.stargazerweatherapp.ui.components.FutureWeatherCardLarge
import com.stargazerweatherapp.ui.components.FutureWeatherCardSmall
import com.stargazerweatherapp.ui.screens.MySearchBar
import com.stargazerweatherapp.viewmodels.WeatherViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FutureWeather(
    weatherData: DailyWeather,
    navigateToFutureWeather: (date: String) -> Unit,
    weatherViewModel: WeatherViewModel
) {
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            MySearchBar()
        }
    ) {
        Column(modifier = Modifier
            .padding(it).padding(horizontal = 16.dp)
            .fillMaxHeight()) {
            FutureWeatherCardLarge(
                weather = weatherData,
                modifier = Modifier.weight(1f)
            )
            FutureWeatherCardSmall(
                weatherData = weatherViewModel.futureWeather.value,
                navigateToFutureWeather
            )
        }
    }


}