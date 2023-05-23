package com.stargazerweatherapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stargazerweatherapp.R
import com.stargazerweatherapp.data.models.DailyWeather
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FutureWeatherCardLarge(weather: DailyWeather?, modifier: Modifier = Modifier, navigateToGlossary : () -> Unit) {
    // A Large weather card for showing weather in a day in the future,
    // This matches the style of the weather card to create a more universal theme
    var tooltipStateCloudCover by remember{ mutableStateOf(PlainTooltipState()) }
    var tooltipStateVisibility by remember{ mutableStateOf(PlainTooltipState()) }
    var tooltipStateTransparency by remember{ mutableStateOf(PlainTooltipState()) }
    val scope = rememberCoroutineScope()
    Card(modifier = modifier.fillMaxWidth()) {
        weather?.let { nonNullWeather ->
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = nonNullWeather.date,
                    fontSize = 26.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(horizontal = 16.dp, vertical = 8.dp))

                Text(text = nonNullWeather.location.name,
                    fontSize = 36.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(horizontal = 16.dp, vertical = 8.dp))

                Row(verticalAlignment = Alignment.Top,
                    modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(weather.getSunset(),
                        textAlign = TextAlign.Left,
                        fontSize = 26.sp
                    )
                    Spacer(Modifier.weight(1.0f))

                    Box(
                        modifier = Modifier
                            .border(2.dp, Color.White)
                            .size(48.dp)
                            .padding(horizontal = 3.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.wi_moon_waxing_crescent_1),
                            fontSize = 36.sp,
                            fontFamily = FontFamily(Font(R.font.weathericons))
                        )
                    }

                    Box(
                        modifier = Modifier
                            .border(2.dp, Color.White)
                            .size(48.dp)
                            .padding(horizontal = 3.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "5",
                            fontSize = 36.sp,
                            fontFamily = FontFamily(Font(R.font.weathericons))
                        )
                    }
                }
                Text(
                    text = stringResource(id = nonNullWeather.weatherType.icon),
                    fontSize = 128.sp,
                    fontFamily = FontFamily(Font(R.font.weathericons))
                )
                Text(text = nonNullWeather.weatherType.description, fontSize = 26.sp)
                Text(text = "${nonNullWeather.temperatureMin}°C", fontSize = 26.sp)

                Spacer(Modifier.height(16.dp))

                Text(text = "Cloud Cover: ${nonNullWeather.cloudCoverAtSunset}%", modifier = Modifier.clickable {
                    scope.launch {
                        tooltipStateCloudCover.show()
                    }
                })

                PlainTooltipBox(tooltip = {
                    Column(Modifier.padding(12.dp)) {
                        Text(text = "Cloud Cover", fontWeight= FontWeight.Bold)
                        Text(
                            buildAnnotatedString {
                                append("Lorem ipsum dolor sit amet, consectetur adipiscing elit ")
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                                    append("cloud cover")
                                }
                                append(" euismod eleifend arcu eget placerat. Donec interdum mauris sit amet mi pharetra faucibus")
                            }
                        )
                    } }, tooltipState = tooltipStateCloudCover) {}

                Text(text = "Visibility: ${nonNullWeather.visibility}m", modifier = Modifier.clickable {
                    scope.launch {
                        tooltipStateVisibility.show()
                    }
                })

                PlainTooltipBox(tooltip = {
                    Column(Modifier.padding(12.dp)) {
                        Text(text = "Visibility", fontWeight= FontWeight.Bold)
                        Text(
                            buildAnnotatedString {
                                append("Lorem ipsum dolor sit amet, consectetur adipiscing elit ")
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                                    append("visibility")
                                }
                                append(" euismod eleifend arcu eget placerat. Donec interdum mauris sit amet mi pharetra faucibus")
                            }
                        )
                    } }, tooltipState = tooltipStateVisibility) {}

                Text(text = "Transparency: ${weather.transparency}", modifier = Modifier.clickable {
                    scope.launch {
                        tooltipStateTransparency.show()
                    }
                })

                PlainTooltipBox(tooltip = {
                    Column(Modifier.padding(12.dp)) {
                        Text(text = "Transparency", fontWeight= FontWeight.Bold)
                        Text(
                            buildAnnotatedString {
                                append("Lorem ipsum dolor sit amet, consectetur adipiscing elit ")
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                                    append("cloud cover")
                                }
                                append(" euismod eleifend arcu eget placerat. Donec interdum mauris sit amet mi pharetra faucibus")
                            }
                        )
                    } }, tooltipState = tooltipStateTransparency) {}

            }
        }
    }
}
