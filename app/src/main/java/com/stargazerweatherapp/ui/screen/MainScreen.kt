package com.stargazerweatherapp.ui.screens

import android.util.Log
import android.widget.SearchView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
//import com.stargazerweatherapp.ui.components.AstronomyCard
import com.stargazerweatherapp.ui.components.WeatherCard
import com.stargazerweatherapp.viewmodels.WeatherViewModel

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
        Column(modifier = Modifier.padding(16.dp)) {
            WeatherCard(
                weather = weatherViewModel.weather.value,
                modifier = Modifier.padding(bottom = 16.dp)
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
fun StargazerWeatherAppBar(
    onDetailsClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
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
        modifier = Modifier.fillMaxWidth()
    )
}

