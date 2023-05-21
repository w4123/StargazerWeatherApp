package com.stargazerweatherapp.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stargazerweatherapp.BuildConfig
import com.stargazerweatherapp.data.models.DailyWeather
import com.stargazerweatherapp.data.models.Location
import com.stargazerweatherapp.data.models.Weather
import com.stargazerweatherapp.data.repository.LocationRepository
import com.stargazerweatherapp.data.repository.WeatherRepository
import com.stargazerweatherapp.data.repository.WeatherRepositoryImpl
import com.stargazerweatherapp.data.repository.WeatherRepositoryMockup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.temporal.ChronoUnit.DAYS

class WeatherViewModel(
    private val weatherRepository: WeatherRepository  =
        if (BuildConfig.DEBUG) WeatherRepositoryMockup() else
            WeatherRepositoryImpl(),
    private val locationRepository: LocationRepository = LocationRepository()
) : ViewModel() {

    val currentWeather = mutableStateOf<Weather?>(null)
    val futureWeather = mutableStateOf<List<DailyWeather>?>(null)
    val isLoading = mutableStateOf(false)
    val isError = mutableStateOf(false)
    val currentLocation = mutableStateOf<Location?>(null)

    init {
        fetchWeatherData()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun dateInRange(dateString : String) : Boolean {
        val date = LocalDate.parse(dateString)
        val startDate = LocalDate.parse(futureWeather.value!![0].date)
        val between = DAYS.between(date,startDate)
        return (between >= 0 && between < futureWeather.value!!.size)

    }

    fun dateIsCurrent(dateString: String) : Boolean{
        return (dateString.equals(futureWeather.value!![0].date));
    }


    fun getDailyWeatherFromDate(date : String?) : DailyWeather{
        if (!date.equals(null)) {
            for (dailyWeather in futureWeather.value!!) {
                if (dailyWeather.date.equals(date)) {
                    return dailyWeather;
                }
            }
        }
        throw NoSuchElementException("Date $date not in range")
    }

    fun fetchWeatherData(locationName : String){
        isLoading.value = true
        isError.value = false

        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    val location = locationRepository.locationData.find {
                        it.name.equals(locationName)
                    }!!
                    currentWeather.value = weatherRepository.getCurrentWeatherData(location);
                    futureWeather.value = weatherRepository.getFutureWeatherData(location)
                    currentLocation.value = location
                }
            } catch (e: Exception) {
                isError.value = true
            } finally {
                isLoading.value = false
            }
        }

    }

    fun fetchWeatherData() {
        fetchWeatherData("Cambridge")
    }
}
