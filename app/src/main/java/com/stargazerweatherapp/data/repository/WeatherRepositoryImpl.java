package com.stargazerweatherapp.data.repository;

import android.util.Log;

import com.stargazerweatherapp.data.models.DailyWeather;
import com.stargazerweatherapp.data.models.Location;
import com.stargazerweatherapp.data.models.Weather;
import com.stargazerweatherapp.data.models.WeatherType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherRepositoryImpl implements WeatherRepository {
    /*
        This is the weather repository that actually fetches the data from the OPEN-METEO API
        It gets the DATA is JSON format and processes
        It returns null if data cannot be found
        There are two methods, one gets the weather for today
        One gets for up to a week in advance
     */
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
                .addQueryParameter("hourly","cloudcover,visibility")
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            JSONObject obj = new JSONObject(response.body().string());
            JSONObject hourly = obj.getJSONObject("hourly");
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            return new Weather(
                    obj.getJSONObject("current_weather").getString("time"),
                    location,
                    obj.getJSONObject("current_weather").getDouble("temperature"),
                    obj.getJSONObject("current_weather").getDouble("windspeed"),
                    obj.getJSONObject("current_weather").getDouble("winddirection"),
                    new WeatherType(obj.getJSONObject("current_weather").getInt("weathercode")),
                    hourly.getJSONArray("cloudcover").getInt(hour),
                    hourly.getJSONArray("visibility").getInt(hour)
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
                .addQueryParameter("daily", "temperature_2m_max,temperature_2m_min,sunset")
                .addQueryParameter("timezone", "auto")
                .addQueryParameter("hourly", "cloudcover,weathercode,visibility")
                .addQueryParameter("is_day", "0")
                .build();

        Log.d("URL", httpUrl.toString());

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            ArrayList<DailyWeather> result = new ArrayList<>();
            JSONObject obj = new JSONObject(response.body().string());
            JSONObject daily = obj.getJSONObject("daily");
            JSONObject hourly = obj.getJSONObject("hourly");
            JSONArray time = daily.getJSONArray("time");
            JSONArray temperature_2m_min = daily.getJSONArray("temperature_2m_min");
            JSONArray temperature_2m_max = daily.getJSONArray("temperature_2m_max");
            JSONArray sunset = daily.getJSONArray("sunset");
            JSONArray hourlyWeathercode = hourly.getJSONArray("weathercode");
            JSONArray hourlyCloudcover = hourly.getJSONArray("cloudcover");
            JSONArray hourlyVisibility = hourly.getJSONArray("visibility");
            ArrayList<Integer> sunsetHours = new ArrayList<>();
            for (int i = 0; i < time.length() - 1; i++) {
                sunsetHours.add(24 * i +
                        Integer.parseInt(
                                sunset
                                        .getString(i)
                                        .substring(time.getString(i).length() + 1,
                                                time.getString(i).length() + 3)));
            }

            for (int i = 0; i < time.length() - 1; i++) {
                result.add(
                        new DailyWeather(time.getString(i),
                                location,
                                temperature_2m_max.getDouble(i),
                                temperature_2m_min.getDouble(i),
                                sunset.getString(i).substring(time.getString(i).length() + 1),
                                new WeatherType(hourlyWeathercode.getInt(
                                        sunsetHours.get(i))
                                ),
                                hourlyCloudcover.getInt(sunsetHours.get(i)),
                                hourlyVisibility.getInt(sunsetHours.get(i))
                        )
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
