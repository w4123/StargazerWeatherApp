package com.stargazerweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stargazerweatherapp.data.models.DailyWeather
import com.stargazerweatherapp.ui.screens.MainScreen
//import com.stargazerweatherapp.ui.screens.SettingsScreen
import com.stargazerweatherapp.ui.theme.StargazerWeatherAppTheme
import com.stargazerweatherapp.viewmodels.WeatherViewModel

class MainActivity : ComponentActivity() {
    var globalViewModel: WeatherViewModel = WeatherViewModel()

    @Preview
    @Composable
    fun MainScreen() {
        StargazerWeatherAppTheme {
            Surface {
                val navController = rememberNavController()

                NavHost(navController, startDestination = "main") {
                    composable("main") {
                        MainScreen(
                            { navController.navigate("details") },
                            { navController.navigate("settings") },
                            navController,
                            globalViewModel
                        )
                    }


                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    fun OpenFutureWeather() {
            StargazerWeatherAppTheme {
                Surface {
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = "main") {
                        composable("FutureWeather/{weatherDate}") {
                            navStack ->
                            val weatherDate = navStack.arguments?.getString("weatherDate")
                            val weather: DailyWeather = globalViewModel.getWeatherFromDate(weatherDate);
                            com.stargazerweatherapp.ui.screen.FutureWeather(
                                navigateToDetailsScreen = { navController.navigate("details") },
                                navigateToSettingsScreen = { navController.navigate("settings") },
                                weatherData = weather
                            )
                        }
                    }
                }
            }
    }
}
