package com.stargazerweatherapp.data.repository;

import com.stargazerweatherapp.data.models.DailyWeather;
import com.stargazerweatherapp.data.models.Location;
import com.stargazerweatherapp.data.models.Weather;

import java.util.List;

public interface WeatherRepository {
    /*
    An interface for something that returns some weather.
    This is created to allow the mockup to fetch weather as well as the original
     */
    List<DailyWeather> getFutureWeatherData(Location location);
    Weather getCurrentWeatherData(Location location);

}
