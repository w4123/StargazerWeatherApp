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
import com.stargazerweatherapp.ui.screen.CalendarA
import com.stargazerweatherapp.ui.screens.MainScreen
//import com.stargazerweatherapp.ui.screens.SettingsScreen
import com.stargazerweatherapp.ui.theme.StargazerWeatherAppTheme
import io.github.boguszpawlowski.composecalendar.Calendar

class MainActivity : ComponentActivity() {
    @Preview
    @Composable
    fun MainScreen() {
        StargazerWeatherAppTheme {
            Surface {
                val navController = rememberNavController()

                NavHost(navController, startDestination = "main") {
                    composable("main") {
                        // MainScreen(
                        //    navigateToDetailsScreen = { navController.navigate("details") },
                        //    navigateToSettingsScreen = { navController.navigate("settings") }
                        // )
                        CalendarA()
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
