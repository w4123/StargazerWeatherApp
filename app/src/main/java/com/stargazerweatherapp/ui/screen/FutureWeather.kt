package com.stargazerweatherapp.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stargazerweatherapp.data.models.DailyWeather
import com.stargazerweatherapp.ui.components.FutureWeatherCardLarge
import com.stargazerweatherapp.ui.components.WeatherCard
import com.stargazerweatherapp.ui.screen.ui.theme.StargazerWeatherAppTheme
import com.stargazerweatherapp.ui.screens.MySearchBar
import com.stargazerweatherapp.viewmodels.WeatherViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FutureWeather(
    navigateToDetailsScreen: () -> Unit,
    navigateToSettingsScreen: () -> Unit,
    weatherData: String
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
        }
    }


}