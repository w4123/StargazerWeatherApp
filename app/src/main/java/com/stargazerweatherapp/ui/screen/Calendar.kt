package com.stargazerweatherapp.ui.screen


import android.content.res.Resources.Theme
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.stargazerweatherapp.R
import com.stargazerweatherapp.data.models.Location
import com.stargazerweatherapp.data.models.Weather
import com.stargazerweatherapp.data.models.WeatherType
import com.stargazerweatherapp.ui.components.WeatherCard
import com.stargazerweatherapp.ui.screens.MySearchBar
import com.stargazerweatherapp.ui.theme.*
import com.stargazerweatherapp.viewmodels.WeatherViewModel
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.day.DefaultDay
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import io.github.boguszpawlowski.composecalendar.selection.SelectionState
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(navigateBack: () -> Unit = {}, navigateAlerts: () -> Unit = {}, weatherModel: WeatherViewModel) {

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

            MySearchBar()

            SelectableCalendar(
                calendarState = calendarState,
                modifier = Modifier.background(color = Color.Gray),
                dayContent = { addSomething(it, navAlert = navigateAlerts, viewModel = weatherModel) }
            )
        }
    }

}

@Composable
fun <T: SelectionState> addSomething(state: DayState<T>,
                                     modifier: Modifier = Modifier,
                                     selectionColor: Color = Purple2,
                                     currentDayColor: Color = Teal200,
                                     onClick: (LocalDate) -> Unit = {},
                                     navAlert: () -> Unit = {},
                                     viewModel: WeatherViewModel
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
                navAlert()
                println(date)
            },
        elevation = if (state.isFromCurrentMonth) CardDefaults.cardElevation(4.dp) else CardDefaults.cardElevation(0.dp),
        border = if (state.isCurrentDay) BorderStroke(1.dp, currentDayColor) else null,
        //colors = if (isSelected) CardDefaults.cardColors(contentColor = Purple2) else CardDefaults.cardColors(contentColor = Color(0xFFFFFFFF)),
        colors = cardColour(isSelected = isSelected, isInRange = viewModel.dateInRange(date.toString()))
    ) {
        Box(
            contentAlignment = Alignment.TopCenter,
        ) {
            Text(text = date.dayOfMonth.toString())
        }
    }

    if (viewModel.dateInRange(date.toString()))
        Text(
            text = stringResource(id = viewModel.currentWeather.value!!.weatherType.icon),
            fontSize = 30.sp,
            fontFamily = FontFamily(Font(R.font.weathericons)),
            modifier = Modifier.padding(start=10.dp, top=12.dp, bottom = 0.dp),
            color = if (isSelected) Purple2 else Color(0xFFFFFFFF)
        )
}

@Composable
fun cardColour(isSelected: Boolean, isInRange: Boolean): CardColors {
    if (isSelected and isInRange)
        return CardDefaults.cardColors(contentColor = Purple2)
    if (isSelected)
        return CardDefaults.cardColors(contentColor = Purple2, containerColor = Color(0xFF696370))
    if (isInRange)
        return CardDefaults.cardColors(contentColor = Color(0xFFFFFFFF))
    return CardDefaults.cardColors(contentColor = Color(0xFFFFFFFF), containerColor = Color(0xFF696370))
}