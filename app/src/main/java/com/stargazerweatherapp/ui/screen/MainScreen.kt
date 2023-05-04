package com.stargazerweatherapp.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.SearchView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stargazerweatherapp.data.models.Weather
import com.stargazerweatherapp.data.models.WeatherType
import com.stargazerweatherapp.ui.components.FutureWeatherCard
//import com.stargazerweatherapp.ui.components.AstronomyCard
import com.stargazerweatherapp.ui.components.WeatherCard
import com.stargazerweatherapp.viewmodels.WeatherViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navigateToDetailsScreen: () -> Unit,
    navigateToSettingsScreen: () -> Unit
) {
    val weatherViewModel: WeatherViewModel = viewModel()

    Scaffold(
        topBar = {
            StargazerWeatherAppBar(
                onDetailsClick = navigateToDetailsScreen,
                onSettingsClick = navigateToSettingsScreen
            )
        }
    ) {
        Column(modifier = Modifier.padding(16.dp).fillMaxHeight()) {
            WeatherCard(
                weather = weatherViewModel.currentWeather.value,
                modifier = Modifier.weight(1f)
            )
            FutureWeatherCard(
                weatherData = weatherViewModel.futureWeather.value
            )
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        },
        placeholder = { Text(text = "Search") },
        textStyle = TextStyle(fontSize = 15.sp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@Composable
fun StargazerWeatherAppBar(
    onDetailsClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    TopAppBar(
        title = {
            SearchBar(query = "Cambridge", onQueryChange = {

                Log.d("TEST", it)
            })
        },
        actions = {
            Row {
                IconButton(onClick = onDetailsClick) {
                    Icon(Icons.Filled.Info, contentDescription = "Details")
                }
                IconButton(onClick = onSettingsClick) {
                    Icon(Icons.Filled.Settings, contentDescription = "Settings")
                }
            }
        },
        modifier = Modifier.fillMaxWidth().height(65.dp)
    )
}

