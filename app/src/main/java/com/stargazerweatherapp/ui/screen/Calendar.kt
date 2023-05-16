package com.stargazerweatherapp.ui.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.stargazerweatherapp.ui.theme.Purple200
import com.stargazerweatherapp.ui.theme.Purple500
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState

@Composable
@Preview
fun CalendarA() {
    val calendarState = rememberSelectableCalendarState()
    val navController = rememberNavController()
    Column(Modifier.background(color = Purple500).fillMaxHeight()) {

        SelectableCalendar(
            calendarState = calendarState,
            modifier = Modifier.background(color = Purple200)
        )

        if (calendarState.selectionState.selection.isNotEmpty()){
            // navController.navigate("Alerts")
            println("Went to " + calendarState.selectionState.selection.toString())
        }
    }

}