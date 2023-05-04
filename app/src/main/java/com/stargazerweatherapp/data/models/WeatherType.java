package com.stargazerweatherapp.data.models;

public class WeatherType {
    private final Integer id;
    public WeatherType(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        switch (id)
        {
            case 0: return "Clear Sky";
            case 1: return "Mainly Clear";
            case 2: return "Partly Cloudy";
            case 3: return "Overcast";
            case 45: return "Fog";
        }
        return "";
    }

    public Integer getId() {
        return id;
    }
}
