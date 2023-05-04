package com.stargazerweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stargazerweatherapp.ui.screens.DetailsScreen
import com.stargazerweatherapp.ui.screens.MainScreen
//import com.stargazerweatherapp.ui.screens.SettingsScreen
import com.stargazerweatherapp.ui.theme.StargazerWeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
                        composable("details") {
                            DetailsScreen(
                                onBackClick = { navController.popBackStack() }
                            )
                        }
                        //composable("settings") {
                        //    SettingsScreen(
                        //        onBackClick = { navController.popBackStack() }
                        //    )
                        //}
                    }
                }
            }
        }
    }
}
