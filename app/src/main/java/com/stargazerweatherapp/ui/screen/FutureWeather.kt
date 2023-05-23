package com.stargazerweatherapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    navigateToNewLocation: (location: String) -> Unit,
    navigateToGlossary: () -> Unit,
    navigateToAlertsScreen: () -> Unit,
    navigateToCalendarScreen: () -> Unit,
    weatherViewModel: WeatherViewModel
) {
    var menuExpanded by remember { mutableStateOf(false) }
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            MySearchBar(
                leadingButton = {
                    IconButton(onClick = { menuExpanded = !menuExpanded }, modifier = it) {
                        Icon(Icons.Default.Menu, "Menu")
                    }

                    DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }) {
                        DropdownMenuItem(leadingIcon = { Icon(Icons.Default.DateRange, "Calendar") }, text = { Text("Calendar") }, onClick = navigateToCalendarScreen)
                        DropdownMenuItem(leadingIcon = { Icon(Icons.Default.List, "Glossary") }, text = { Text("Glossary") }, onClick = navigateToGlossary)
                        DropdownMenuItem(leadingIcon = { Icon(Icons.Default.Notifications, "Alert") }, text = { Text("Set Alert") }, onClick = navigateToAlertsScreen)
                    }
                },
                navigateToNewLocation
            )
        }
    ) {
        Column(modifier = Modifier
            .padding(it).padding(horizontal = 16.dp)
            .fillMaxHeight()) {
            FutureWeatherCardLarge(
                weather = weatherData,
                modifier = Modifier.weight(1f),
                navigateToGlossary
            )
            FutureWeatherCardSmall(
                weatherData = weatherViewModel.futureWeather.value,
                navigateToFutureWeather
            )
        }
    }


}