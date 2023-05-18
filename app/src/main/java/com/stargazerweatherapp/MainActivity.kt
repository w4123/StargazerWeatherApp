package com.stargazerweatherapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stargazerweatherapp.ui.screen.CalendarScreen
import androidx.navigation.navArgument
import com.stargazerweatherapp.data.models.DailyWeather
import com.stargazerweatherapp.ui.screen.FutureWeather
import com.stargazerweatherapp.ui.screen.AlertPage
import com.stargazerweatherapp.ui.screens.MainScreen
//import com.stargazerweatherapp.ui.screens.SettingsScreen
import com.stargazerweatherapp.ui.theme.StargazerWeatherAppTheme
import io.github.boguszpawlowski.composecalendar.Calendar
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
                            { navController.navigate("alerts") },
                            { navController.navigate("calendar") },
                            { navController.navigate("FutureWeather/${it}") },
                            globalViewModel
                        )
                    }

                    composable(
                        "FutureWeather/{weatherDate}",
                        arguments = listOf(navArgument("weatherDate") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val weatherDate = backStackEntry.arguments?.getString("weatherDate")
                        val weather: DailyWeather = globalViewModel.getWeatherFromDate(weatherDate);
                        FutureWeather(
                            navigateToDetailsScreen = { navController.navigate("details") },
                            navigateToSettingsScreen = { navController.navigate("settings") },
                            weatherData = weather
                        )
                    }

                    composable("calendar") {
                        CalendarScreen { navController.popBackStack() }
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
}
