package com.stargazerweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.stargazerweatherapp.data.models.DailyWeather
import com.stargazerweatherapp.ui.screen.AlertPage
import com.stargazerweatherapp.ui.screen.CalendarScreen
import com.stargazerweatherapp.ui.screen.FutureWeather
import com.stargazerweatherapp.ui.screen.Glossary
import com.stargazerweatherapp.ui.screens.MainScreen
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
                            { navController.navigate("glossary") },
                            { navController.navigate("alerts") },
                            { navController.navigate("calendar") },
                            { navController.navigate("FutureWeather/${it}") },
                            globalViewModel.currentWeather.value,
                            globalViewModel
                        )
                    }

                    composable(
                        "FutureWeather/{weatherDate}",
                        arguments = listOf(navArgument("weatherDate") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val weatherDate = backStackEntry.arguments?.getString("weatherDate")
                        if (globalViewModel.dateIsCurrent(weatherDate!!)){
                            MainScreen(
                                { navController.navigate("glossary") },
                                { navController.navigate("alerts") },
                                { navController.navigate("calendar") },
                                { navController.navigate("FutureWeather/${it}") },
                                globalViewModel.currentWeather.value,
                                globalViewModel
                            )
                        }
                        else{
                            val weather: DailyWeather = globalViewModel.getDailyWeatherFromDate(weatherDate);
                            FutureWeather(
                                weatherData = weather,
                                { navController.navigate("FutureWeather/${it}") },
                                globalViewModel
                            )
                        }
                    }

                    composable("calendar") {
                        CalendarScreen (
                            { navController.popBackStack() },
                            { navController.navigate("alerts") },
                            globalViewModel
                        )
                    }

                    composable("alerts") {
                        AlertPage(navigateBack = { navController.popBackStack() })
                    }

                    composable("glossary") {
                        Glossary { navController.popBackStack() }
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
