package com.stargazerweatherapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stargazerweatherapp.ui.screen.CalendarScreen
import com.stargazerweatherapp.ui.screen.AlertPage
import com.stargazerweatherapp.ui.screens.MainScreen
//import com.stargazerweatherapp.ui.screens.SettingsScreen
import com.stargazerweatherapp.ui.theme.StargazerWeatherAppTheme
import io.github.boguszpawlowski.composecalendar.Calendar

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @Preview
    @Composable
    fun MainScreen() {
        StargazerWeatherAppTheme {
            Surface {
                val navController = rememberNavController()

//                NavHost(navController, startDestination = "main") {
//                    composable("main") {
//                        MainScreen(
//                            navigateToDetailsScreen = { navController.navigate("details") },
//                            navigateToSettingsScreen = { navController.navigate("settings") },
//                            navigateToAlertsScreen =  { navController.navigate("alerts") },
//                            navigateToCalendarScreen = {navController.navigate("Calendar")}
//                        )
//                    }
//
//                    composable("alerts") {
//                        AlertPage(navigateBack = { navController.popBackStack() })
//                    }
//
//                    composable("calendar"){
//                        CalendarScreen()
//                    }

                    NavHost(navController = navController, startDestination = "calendar"){
                        composable("calendar"){
                            CalendarScreen()
                        }
                    }
                }
            }
        }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}
