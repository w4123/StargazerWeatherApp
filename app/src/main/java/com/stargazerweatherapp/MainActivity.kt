package com.stargazerweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.stargazerweatherapp.data.models.DailyWeather
import com.stargazerweatherapp.ui.screen.FutureWeather
import com.stargazerweatherapp.ui.screens.MainScreen
//import com.stargazerweatherapp.ui.screens.SettingsScreen
import com.stargazerweatherapp.ui.theme.StargazerWeatherAppTheme
import com.stargazerweatherapp.viewmodels.WeatherViewModel

class MainActivity : ComponentActivity() {
    @Preview
    @Composable
    fun MainScreen() {
        StargazerWeatherAppTheme {
            Surface {
                val navController = rememberNavController()

                NavHost(navController, startDestination = "main") {
                    composable("main") {
                        MainScreen(
                            navigateToDetailsScreen = { navController.navigate("details") },
                            navigateToSettingsScreen = { navController.navigate("settings") }
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
                        composable("futureWeather/{weatherData}") {
                            var weather: DailyWeather? = null;
                            com.stargazerweatherapp.ui.screen.FutureWeather(
                                navigateToDetailsScreen = { navController.navigate("details") },
                                navigateToSettingsScreen = { navController.navigate("settings") }
                                weather
                            )
                        }
                    }
                }
            }
    }
}
