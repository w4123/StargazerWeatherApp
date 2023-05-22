package com.stargazerweatherapp.ui.screen


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stargazerweatherapp.R
import com.stargazerweatherapp.ui.screens.MySearchBar
import com.stargazerweatherapp.ui.theme.*
import com.stargazerweatherapp.viewmodels.WeatherViewModel
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.header.MonthState
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.SelectionState
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.time.temporal.ChronoUnit.DAYS
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    navigateBack: () -> Unit = {},
    navigateAlerts: (date: String) -> Unit = {},
    weatherModel: WeatherViewModel,
) {
    // A screen containing an interactive calendar

    val calendarState = rememberSelectableCalendarState()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Calendar")
            },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                })
        },

    ) {

        Column(
            Modifier
                .background(color = Color.DarkGray)
                .fillMaxHeight()
                .padding(it)
                .padding(10.dp)
        ) {

            // Search bar to change location
            MySearchBar(navigateToNewLocation = {location: String -> toNewLocation(location, weatherModel)})

            Text(text = weatherModel.currentLocation.value!!.name,
                fontSize = 36.sp,
                modifier = Modifier.align(Alignment.Start).padding(horizontal=16.dp, vertical = 8.dp))

            // Calendar widget from boguszpawlowski on GitHub
            SelectableCalendar(
                calendarState = calendarState,
                modifier = Modifier.background(color = Color.Gray, shape = RoundedCornerShape(10.dp)).padding(3.dp),
                dayContent = { DayRendering(it, navAlert = navigateAlerts, viewModel = weatherModel)},
                monthHeader = { MonthRendering(it) },
                daysOfWeekHeader = { DaysOfWeekRendering(it) },
            )
        }
    }

}

@Composable
fun <T: SelectionState> DayRendering(state: DayState<T>,
                                     modifier: Modifier = Modifier,
                                     currentDayColor: Color = Teal200,
                                     onClick: (LocalDate) -> Unit = {},
                                     navAlert: (location: String) -> Unit = {},
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
                navAlert(date.toString())
            },
        elevation = if (state.isFromCurrentMonth) CardDefaults.cardElevation(4.dp) else CardDefaults.cardElevation(0.dp),
        border = if (state.isCurrentDay) BorderStroke(1.dp, currentDayColor) else null,
        colors = cardColour(isSelected = isSelected, isInRange = viewModel.dateInRange(date.toString()))
    ) {
        // Day number
        Box(
            contentAlignment = Alignment.TopCenter,
        ) {
            Text(
                text = date.dayOfMonth.toString(),
                modifier = Modifier.padding(start = 2.dp)
            )
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
fun MonthRendering(monthState: MonthState,
                   modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            modifier = Modifier.testTag("Decrement"),
            onClick = { monthState.currentMonth = monthState.currentMonth.minusMonths(1) }
        ) {
            Image(
                imageVector = Icons.Default.KeyboardArrowLeft,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                contentDescription = "Previous",
            )
        }
        Text(
            modifier = Modifier.testTag("MonthLabel"),
            text = monthState.currentMonth.month
                .getDisplayName(TextStyle.FULL, Locale.getDefault())
                .lowercase()
                .replaceFirstChar { it.titlecase() },
            style = MaterialTheme.typography.headlineMedium,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = monthState.currentMonth.year.toString(), style = MaterialTheme.typography.headlineMedium)
        IconButton(
            modifier = Modifier.testTag("Increment"),
            onClick = { monthState.currentMonth = monthState.currentMonth.plusMonths(1) }
        ) {
            Image(
                imageVector = Icons.Default.KeyboardArrowRight,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                contentDescription = "Next",
            )
        }
    }
}

@Composable
fun DaysOfWeekRendering(
    daysOfWeek: List<DayOfWeek>,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        daysOfWeek.forEach { dayOfWeek ->
            Text(
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                modifier = modifier
                    .weight(1f)
                    .wrapContentHeight()
            )
        }
    }
}

@Composable
fun cardColour(isSelected: Boolean, isInRange: Boolean): CardColors {
    // Creates a card colour object with the right attributes
    // Distinguishes between a selected date and a date with no weather data

    val selectedCol = Purple2
    val unSelectedCol = Color(0xFFFFFFFF)
    val noDataCol = Color(0xFF696370)

    if (isSelected and isInRange)
        // Is selected and we have data
        return CardDefaults.cardColors(contentColor = selectedCol)
    if (isSelected)
        // Is selected and we don't have data
        return CardDefaults.cardColors(contentColor = selectedCol, containerColor = noDataCol)
    if (isInRange)
        // Isn't selected and we have data
        return CardDefaults.cardColors(contentColor = unSelectedCol)
    // Isn't selected and we don't have date
    return CardDefaults.cardColors(contentColor = unSelectedCol, containerColor = noDataCol)
}

fun toNewLocation(location: String, weatherModel: WeatherViewModel){
    Log.d("New location Navigation","Navigating to $location")
    weatherModel.fetchWeatherData(location)
}