package com.stargazerweatherapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PlainTooltipBox
import androidx.compose.material3.PlainTooltipState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.stargazerweatherapp.data.models.Weather
import com.stargazerweatherapp.viewmodels.WeatherViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherCard(weather: Weather?, modifier: Modifier = Modifier, weatherViewModel: WeatherViewModel, navigateToGlossary : ()-> Unit) {
    var tooltipStateCloudCover by remember{ mutableStateOf(PlainTooltipState()) }
    var tooltipStateVisibility by remember{ mutableStateOf(PlainTooltipState()) }
    var tooltipStateTransparency by remember{ mutableStateOf(PlainTooltipState()) }
    val scope = rememberCoroutineScope()
    //A card for the current weather used in the main page, it takes some weather and displays it
    Card(modifier = modifier.fillMaxWidth()) {
        weather?.let { nonNullWeather ->
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = nonNullWeather.datetime.dropLast(6),
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
                    Text(weatherViewModel.futureWeather.value!![0].getSunset(),
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
                Text(text = "${nonNullWeather.temperature}°C", fontSize = 26.sp)

                Spacer(Modifier.height(16.dp))

                Text(text = "Cloud Cover: ${nonNullWeather.cloudCover}%", modifier = Modifier.clickable {
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

                Text(text = "Transparency: ${weatherViewModel.futureWeather.value!![0].transparency}", modifier = Modifier.clickable {
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
