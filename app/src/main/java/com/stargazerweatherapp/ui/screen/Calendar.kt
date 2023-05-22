package com.stargazerweatherapp.ui.screen


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stargazerweatherapp.R
import com.stargazerweatherapp.ui.screens.MySearchBar
import com.stargazerweatherapp.ui.theme.*
import com.stargazerweatherapp.viewmodels.WeatherViewModel
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.SelectionState
import java.time.LocalDate
import java.time.temporal.ChronoUnit.DAYS
import java.util.function.ToIntFunction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(navigateBack: () -> Unit = {}, navigateAlerts: () -> Unit = {}, weatherModel: WeatherViewModel) {
    // A screen containing an interactive calendar

    val calendarState = rememberSelectableCalendarState()

    Scaffold(topBar = {
        TopAppBar(title = {
            Text("Calendar")
        },
            navigationIcon = {
                IconButton(onClick = navigateBack) {
                    Icon(Icons.Default.ArrowBack, "Back")
                }
            })
    }
    ) {

        Column(
            Modifier
                .background(color = Color.DarkGray)
                .fillMaxHeight()
                .padding(it)
        ) {

            // Search bar to change location
            MySearchBar()

            // Calendar widget from boguszpawlowski on GitHub
            SelectableCalendar(
                calendarState = calendarState,
                modifier = Modifier.background(color = Color.Gray),
                dayContent = { DayRendering(it, navAlert = navigateAlerts, viewModel = weatherModel) }
            )
        }
    }

}

@Composable
fun <T: SelectionState> DayRendering(state: DayState<T>,
                                     modifier: Modifier = Modifier,
                                     currentDayColor: Color = Teal200,
                                     onClick: (LocalDate) -> Unit = {},
                                     navAlert: () -> Unit = {},
                                     viewModel: WeatherViewModel
) {
    // To be rendered for each day in the month
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
                navAlert()
                println(date)
            },
        elevation = if (state.isFromCurrentMonth) CardDefaults.cardElevation(4.dp) else CardDefaults.cardElevation(0.dp),
        border = if (state.isCurrentDay) BorderStroke(1.dp, currentDayColor) else null,
        colors = cardColour(isSelected = isSelected, isInRange = viewModel.dateInRange(date.toString()))
    ) {
        // Day number
        Box(
            contentAlignment = Alignment.TopCenter,
        ) {
            Text(text = date.dayOfMonth.toString())
        }
    }

    // Weather Icon
    if (viewModel.dateInRange(date.toString())) {
        // If we have weather data for the date...

        val currentDate = LocalDate.parse(viewModel.futureWeather.value!![0].date)
        // Calculate the index into the future weather values
        val between = DAYS.between(currentDate, date).toInt()

        // Add the corresponding icon to the day
        Text(
            text = stringResource(id = viewModel.futureWeather.value!![between].weatherType.icon),
            fontSize = 30.sp,
            fontFamily = FontFamily(Font(R.font.weathericons)),
            modifier = Modifier.padding(start = 10.dp, top = 12.dp, bottom = 0.dp),
            color = if (isSelected) Purple2 else Color(0xFFFFFFFF)
        )
    }
}

@Composable
fun cardColour(isSelected: Boolean, isInRange: Boolean): CardColors {
    // Creates a card colour object with the right attributes
    // Distinguishes between a selected date and a date with no weather data

    if (isSelected and isInRange)
        // Is selected and we have data
        return CardDefaults.cardColors(contentColor = Purple2)
    if (isSelected)
        // Is selected and we don't have data
        return CardDefaults.cardColors(contentColor = Purple2, containerColor = Color(0xFF696370))
    if (isInRange)
        // Isn't selected and we have data
        return CardDefaults.cardColors(contentColor = Color(0xFFFFFFFF))
    // Isn't selected and we don't have date
    return CardDefaults.cardColors(contentColor = Color(0xFFFFFFFF), containerColor = Color(0xFF696370))
}