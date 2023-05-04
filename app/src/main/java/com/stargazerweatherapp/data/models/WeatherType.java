package com.stargazerweatherapp.data.models;

import androidx.compose.runtime.Immutable;

import com.stargazerweatherapp.R;

@Immutable
public class WeatherType {
    private final Integer id;
    public WeatherType(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        switch (id) {
            case 0: return "Clear Sky";
            case 1: return "Mainly Clear";
            case 2: return "Partly Cloudy";
            case 3: return "Overcast";
            case 45: return "Fog";
            case 48: return "Depositing Rime Fog";
            case 51: return "Light Drizzle";
            case 53: return "Moderate Drizzle";
            case 55: return "Dense Drizzle";
            case 56: return "Light Freezing Drizzle";
            case 57: return "Dense Freezing Drizzle";
            case 61: return "Slight Rain";
            case 63: return "Moderate Rain";
            case 65: return "Heavy Rain";
            case 66: return "Light Freezing Rain";
            case 67: return "Heavy Freezing Rain";
            case 71: return "Slight Snow";
            case 73: return "Moderate Snow";
            case 75: return "Heavy Snow";
            case 77: return "Snow Grains";
            case 80: return "Slight Rain Shower";
            case 81: return "Moderate Rain Shower";
            case 82: return "Violent Rain Shower";
            case 85: return "Slight Snow Shower";
            case 86: return "Heavy Snow Shower";
            case 95: return "Thunderstorm";
            case 96: return "Thunderstorm with Slight Hail";
            case 99: return "Thunderstorm with Heavy Hail";
        }
        return "Unknown";
    }

    public Integer getId() {
        return id;
    }

    public Integer getIcon() {
        switch (id) {
            case 0: return R.string.wi_day_sunny;
            case 1:
            case 2: return R.string.wi_day_sunny_overcast;
            case 3: return R.string.wi_cloudy;
            case 45:
            case 48: return R.string.wi_fog;
            case 51:
            case 53:
            case 55:
            case 56:
            case 57: return R.string.wi_sprinkle;
            case 61:
            case 63:
            case 65:
            case 66:
            case 67: return R.string.wi_rain;
            case 71:
            case 73:
            case 75:
            case 77: return R.string.wi_snow;
            case 80:
            case 81:
            case 82: return R.string.wi_showers;
            case 85:
            case 86: return R.string.wi_snow;
            case 95:
            case 96:
            case 99: return R.string.wi_thunderstorm;
        }
        return R.string.wi_na;
    }
}
