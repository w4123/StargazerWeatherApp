package com.stargazerweatherapp.data.repository;

import com.stargazerweatherapp.data.models.DailyWeather;
import com.stargazerweatherapp.data.models.Location;
import com.stargazerweatherapp.data.models.Weather;
import com.stargazerweatherapp.data.models.WeatherType;

import java.util.ArrayList;
import java.util.List;

public class WeatherRepositoryMockup implements WeatherRepository{
    final double latitude = 52.1937;
    final double longitude = 0.1268;

    final Location cambridge_location = new Location("Cambridge", latitude, longitude);

    final Weather currentWeather = new Weather(
            "2023-05-16T14:00",
            cambridge_location,
            15.3,
            16.8,
            318,
            new WeatherType(3),
            100,
            24140
            );

    final List<DailyWeather> futureData;

    public WeatherRepositoryMockup(){
        Integer time = 16;
        futureData = new ArrayList<>(7);
        for(Integer i = 0; i < 7; i++){
            futureData.add(
                    new DailyWeather(
                            "2023-05-"+(i+time),
                            cambridge_location,
                            15.9,
                            8.4,
                            "2023-05-16T20:50",
                            new WeatherType(3),
                            100,
                            24140
                    )
            );
        }
    }
    @Override
    public List<DailyWeather> getFutureWeatherData(Location location)
    {
        return futureData;
    }

    @Override
    public Weather getCurrentWeatherData(Location location)
    {
        return currentWeather;
    }
}
