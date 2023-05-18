package com.stargazerweatherapp.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.SearchView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.isContainer
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.stargazerweatherapp.data.models.Weather
import com.stargazerweatherapp.data.models.WeatherType
import com.stargazerweatherapp.data.repository.LocationRepository
import com.stargazerweatherapp.ui.components.FutureWeatherCardSmall
//import com.stargazerweatherapp.ui.components.AstronomyCard
import com.stargazerweatherapp.ui.components.WeatherCard
import com.stargazerweatherapp.viewmodels.WeatherViewModel
import kotlin.math.max
import kotlin.math.min

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navigateToDetailsScreen: () -> Unit,
    navigateToSettingsScreen: () -> Unit,
    navController: NavController,
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
            WeatherCard(
                weather = weatherViewModel.currentWeather.value,
                modifier = Modifier.weight(1f)
            )
            FutureWeatherCardSmall(
                weatherData = weatherViewModel.futureWeather.value,
                navController
            )
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar() {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    var loc = LocationRepository()
    Box(
        Modifier
            .semantics { isContainer = true }
            .zIndex(1f)
            .padding(6.dp)
            .fillMaxWidth()) {
        SearchBar(
            modifier = Modifier.align(Alignment.TopCenter),
            query = text,
            onQueryChange = { text = it },
            onSearch = { active = false },
            active = active,
            onActiveChange = {
                active = it
            },
            placeholder = { Text("Search Your Location") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                val possibleLocations = loc.locationData.filter {
                    it.name.startsWith(text, true)
                }
                items(possibleLocations.size) { idx ->
                    val resultText = possibleLocations[idx].name
                    ListItem(
                        headlineContent = { Text(resultText) },
                        modifier = Modifier.clickable {
                            text = resultText
                            active = false
                        }
                    )
                }
            }
        }
    }
}

