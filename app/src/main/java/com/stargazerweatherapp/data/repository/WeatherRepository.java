package com.stargazerweatherapp.data.repository;

import com.stargazerweatherapp.data.models.DailyWeather;
import com.stargazerweatherapp.data.models.Location;
import com.stargazerweatherapp.data.models.Weather;

import java.util.List;

public interface WeatherRepository {
    List<DailyWeather> getFutureWeatherData(Location location);
    Weather getCurrentWeatherData(Location location);

}
