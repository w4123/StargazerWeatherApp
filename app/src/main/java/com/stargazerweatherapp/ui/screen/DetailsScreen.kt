package com.stargazerweatherapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
//import com.stargazerweatherapp.ui.components.AstronomyDetailsCard
//import com.stargazerweatherapp.ui.components.WeatherDetailsCard
import com.stargazerweatherapp.viewmodels.WeatherViewModel

@Composable
fun DetailsScreen(
    onBackClick: () -> Unit
) {
    val weatherViewModel: WeatherViewModel = viewModel()

    Scaffold(
        topBar = {
            StargazerWeatherDetailsAppBar(
                onBackClick = onBackClick
            )
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
          //  WeatherDetailsCard(
          //      weather = weatherViewModel.weather,
          //      modifier = Modifier.padding(bottom = 16.dp)
          //  )
          //  AstronomyDetailsCard(astronomy = weatherViewModel.astronomy)
        }
    }
}

@Composable
fun StargazerWeatherDetailsAppBar(
    onBackClick: () -> Unit
) {
    // Implement a custom app bar here with a back button to navigate back to the main screen.
    // You may use the Material TopAppBar component or create a custom one according to your design.
}
