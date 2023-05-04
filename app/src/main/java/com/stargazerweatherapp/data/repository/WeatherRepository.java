package com.stargazerweatherapp.data.repository;

import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;
import com.stargazerweatherapp.data.models.Weather;
import com.stargazerweatherapp.data.models.WeatherType;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherRepository {
    public Weather getWeatherData(Double latitude, Double longitude) {


        OkHttpClient client = new OkHttpClient();

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("api.open-meteo.com")
                .addPathSegment("v1")
                .addPathSegment("forecast")
                .addQueryParameter("latitude", latitude.toString())
                .addQueryParameter("longitude", longitude.toString())
                .addQueryParameter("hourly", "temperature_2m,precipitation_probability,weathercode,cloudcover,visibility")
                .addQueryParameter("current_weather", "true")
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            JSONObject obj = new JSONObject(response.body().string());
            return new Weather(
                    latitude,
                    longitude,
                    obj.getJSONObject("current_weather").getDouble("temperature"),
                    obj.getJSONObject("current_weather").getDouble("windspeed"),
                    obj.getJSONObject("current_weather").getDouble("winddirection"),
                    new WeatherType(obj.getJSONObject("current_weather").getInt("weathercode"))
            );
        }
        catch (IOException e) {
            Log.i("StargazerWeatherApp", "API Request Failed: " + e.getMessage());
        }
        catch (JSONException e) {
            Log.i("StargazerWeatherApp", "JSON Parse Failed: " + e.getMessage());
        }

        return null;
    }
}
