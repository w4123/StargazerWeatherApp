package com.stargazerweatherapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stargazerweatherapp.ui.screen.CalendarScreen
import androidx.navigation.navArgument
import com.stargazerweatherapp.data.models.DailyWeather
import com.stargazerweatherapp.ui.screen.FutureWeather
import com.stargazerweatherapp.ui.screen.AlertPage
import com.stargazerweatherapp.ui.screen.Glossary
import com.stargazerweatherapp.ui.screens.MainScreen
//import com.stargazerweatherapp.ui.screens.SettingsScreen
import com.stargazerweatherapp.ui.theme.StargazerWeatherAppTheme
import com.stargazerweatherapp.viewmodels.WeatherViewModel

class MainActivity : ComponentActivity() {
    var globalViewModel: WeatherViewModel = WeatherViewModel()

    @RequiresApi(Build.VERSION_CODES.O)
    @Preview
    @Composable
    fun MainScreen() {
        StargazerWeatherAppTheme {
            Surface {
                val navController = rememberNavController()

                NavHost(navController, startDestination = "main") {
                    composable("main") {
                        getNewMainScreen(navController)
                    }

                    composable("NewLocation/{locationName}",
                    arguments = listOf(navArgument("locationName") { type = NavType.StringType })
                    ){ backStackEntry ->

                        val locationName = backStackEntry.arguments?.getString("locationName");
                        Log.d("New location Navigation","Navigating to $locationName")
                        globalViewModel.fetchWeatherData(locationName!!)
                        getNewMainScreen(navController)
                    }

                    composable(
                        "FutureWeather/{weatherDate}",
                        arguments = listOf(navArgument("weatherDate") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val weatherDate = backStackEntry.arguments?.getString("weatherDate")
                        if (globalViewModel.dateIsCurrent(weatherDate!!)){
                            getNewMainScreen(navController)
                        }
                        else{
                            val weather: DailyWeather = globalViewModel.getDailyWeatherFromDate(weatherDate);
                            FutureWeather(
                                navigateToDetailsScreen = { navController.navigate("details") },
                                navigateToSettingsScreen = { navController.navigate("settings") },
                                weatherData = weather,
                                navigateToFutureWeather = { navController.navigate("FutureWeather/${it}") },
                                navigateToNewLocation = {navController.navigate("NewLocation/${it}")},
                                navigateToGlossary = { navController.navigate("glossary") },
                                navigateToAlertsScreen = { navController.navigate("alerts") },
                                navigateToCalendarScreen = { navController.navigate("calendar") },
                                weatherViewModel = globalViewModel
                            )
                        }
                    }

                    composable("calendar") {
                        CalendarScreen { navController.popBackStack() }
                    }

                    composable("alerts") {
                        AlertPage(navigateBack = {navController.popBackStack()})
                    }

                    composable("glossary") {
                        Glossary { navController.popBackStack() }
                    }
                }
            }
        }
    }

    @Composable
    private fun getNewMainScreen(navController: NavHostController) {
        Log.d("Main screen location","Main screen location ${globalViewModel.currentLocation.value?.name}")
        MainScreen(
            { navController.navigate("details") },
            { navController.navigate("glossary") },
            { navController.navigate("alerts") },
            { navController.navigate("calendar") },
            { navController.navigate("FutureWeather/${it}") },
            { navController.navigate("NewLocation/${it}")},
            globalViewModel.currentWeather.value,
            globalViewModel
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}
