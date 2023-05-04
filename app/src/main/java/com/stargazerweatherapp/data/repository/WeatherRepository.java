package com.stargazerweatherapp.data.repository;

import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;
import com.stargazerweatherapp.data.models.DailyWeather;
import com.stargazerweatherapp.data.models.Location;
import com.stargazerweatherapp.data.models.Weather;
import com.stargazerweatherapp.data.models.WeatherType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherRepository {

    public Weather getCurrentWeatherData(Location location) {

        OkHttpClient client = new OkHttpClient();

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("api.open-meteo.com")
                .addPathSegment("v1")
                .addPathSegment("forecast")
                .addQueryParameter("latitude", location.getLatitude().toString())
                .addQueryParameter("longitude", location.getLongitude().toString())
                .addQueryParameter("current_weather", "true")
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            JSONObject obj = new JSONObject(response.body().string());
            return new Weather(
                    obj.getJSONObject("current_weather").getString("time"),
                    location,
                    obj.getJSONObject("current_weather").getDouble("temperature"),
                    obj.getJSONObject("current_weather").getDouble("windspeed"),
                    obj.getJSONObject("current_weather").getDouble("winddirection"),
                    new WeatherType(obj.getJSONObject("current_weather").getInt("weathercode"))
            );
        }
        catch (IOException e) {
            Log.w("StargazerWeatherApp", "API Request Failed: " + e.getMessage());
        }
        catch (JSONException e) {
            Log.w("StargazerWeatherApp", "JSON Parse Failed: " + e.getMessage());
        }

        return null;
    }

    public List<DailyWeather> getFutureWeatherData(Location location) {

        OkHttpClient client = new OkHttpClient();

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("api.open-meteo.com")
                .addPathSegment("v1")
                .addPathSegment("forecast")
                .addQueryParameter("latitude", location.getLatitude().toString())
                .addQueryParameter("longitude", location.getLongitude().toString())
                .addQueryParameter("daily", "temperature_2m_max,temperature_2m_min,weathercode,sunset")
                .addQueryParameter("timezone", "auto")
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            ArrayList<DailyWeather> result = new ArrayList<>();
            JSONObject obj = new JSONObject(response.body().string());
            JSONObject daily = obj.getJSONObject("daily");
            JSONArray time = daily.getJSONArray("time");
            JSONArray temperature_2m_min = daily.getJSONArray("temperature_2m_min");
            JSONArray temperature_2m_max = daily.getJSONArray("temperature_2m_max");
            JSONArray sunset = daily.getJSONArray("sunset");
            JSONArray weathercode = daily.getJSONArray("weathercode");
            for (int i = 0; i < time.length(); i++) {
                result.add(
                        new DailyWeather(time.getString(i),
                                location,
                                temperature_2m_max.getDouble(i),
                                temperature_2m_min.getDouble(i),
                                sunset.getString(i).substring(time.getString(i).length() + 1),
                                new WeatherType(weathercode.getInt(i)))
                );
            }
            return result;
        }
        catch (IOException e) {
            Log.w("StargazerWeatherApp", "API Request Failed: " + e.getMessage());
        }
        catch (JSONException e) {
            Log.w("StargazerWeatherApp", "JSON Parse Failed: " + e.getMessage());
        }

        return null;
    }
}
