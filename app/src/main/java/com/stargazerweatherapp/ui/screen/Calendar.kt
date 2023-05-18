package com.stargazerweatherapp.ui.screen


import android.content.res.Resources.Theme
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.stargazerweatherapp.ui.components.FutureWeatherCard
import com.stargazerweatherapp.ui.components.WeatherCard
import com.stargazerweatherapp.ui.screens.MySearchBar
import com.stargazerweatherapp.ui.theme.*
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.day.DefaultDay
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import io.github.boguszpawlowski.composecalendar.selection.SelectionState
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun CalendarScreen() {


    val calendarState = rememberSelectableCalendarState()
    val navController = rememberNavController()
    Column(
        Modifier
            .background(color = Color.DarkGray)
            .fillMaxHeight()) {

        MySearchBar()

        SelectableCalendar(
            calendarState = calendarState,
            modifier = Modifier.background(color = Color.Gray),
            dayContent = { addSomething(it) }
        )

        if (calendarState.selectionState.selection.isNotEmpty()){
            // navController.navigate("Alerts")
            println("Went to " + calendarState.selectionState.selection.toString())
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun <T: SelectionState> addSomething(state: DayState<T>,
                 modifier: Modifier = Modifier,
                 selectionColor: Color = Purple2,
                 currentDayColor: Color = Teal200,
                 onClick: (LocalDate) -> Unit = {},
) {
    val date = state.date
    val selectionState = state.selectionState

    val isSelected = selectionState.isDateSelected(date)

    Card(
        modifier = modifier
            .aspectRatio(1f)
            .padding(2.dp)
            .clickable {
                onClick(date)
                selectionState.onDateSelected(date)
            },
        elevation = if (state.isFromCurrentMonth) CardDefaults.cardElevation(4.dp) else CardDefaults.cardElevation(0.dp),
        border = if (state.isCurrentDay) BorderStroke(1.dp, currentDayColor) else null,
        colors = if (isSelected) CardDefaults.cardColors(contentColor = Purple2) else CardDefaults.cardColors(contentColor = Color(0xFFFFFFFF))
    ) {
        Box(
            contentAlignment = Alignment.TopCenter,
        ) {
            Text(text = date.dayOfMonth.toString())
        }
    }
}