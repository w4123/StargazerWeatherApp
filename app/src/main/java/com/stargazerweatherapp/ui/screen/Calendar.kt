package com.stargazerweatherapp.ui.screen


import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.boguszpawlowski.composecalendar.*
import io.github.boguszpawlowski.composecalendar.header.MonthState

@Composable
@Preview
fun CalendarA() {
    val calendarState = rememberSelectableCalendarState()
    SelectableCalendar(
        calendarState = calendarState,
    )

    Button(onClick = { println(calendarState.selectionState.selection) }, modifier = Modifier.padding(
        Dp(100F)
    )) {

    }

}

fun onClick(){
    println("goToAThing")
}