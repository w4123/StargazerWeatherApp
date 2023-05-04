package com.stargazerweatherapp.data.repository;

import android.util.Log;

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

public class LocationRepository {
    static private List<Location> cachedLocationData = null;

    public List<Location> getLocationData() {
        if (cachedLocationData != null) return cachedLocationData;

        OkHttpClient client = new OkHttpClient();

        // {"Locations":{"Location":[{"elevation":"50.0","id":"14","latitude":"54.9375","longitude":"-2.8092","name":"Carlisle Airport","region":"nw","unitaryAuthArea":"Cumbria"},
        Request request = new Request.Builder()
                .url("http://datapoint.metoffice.gov.uk/public/data/val/wxfcs/all/json/sitelist?key=d1a40113-3ee3-49c3-abe8-be9e5beff359")
                .build();

        try (Response response = client.newCall(request).execute()) {
            ArrayList<Location> result = new ArrayList<>();
            JSONObject obj = new JSONObject(response.body().string());
            JSONObject locs = obj.getJSONObject("Locations");
            JSONArray loc = locs.getJSONArray("Location");
            for (int i = 0; i < loc.length(); i++) {
                JSONObject currLoc = loc.getJSONObject(i);
                result.add(new Location(currLoc.getString("name"),
                        currLoc.getDouble("latitude"),
                        currLoc.getDouble("longitude")));
            }
            cachedLocationData = result;
            return result;
        }
        catch (IOException e) {
            Log.i("StargazerWeatherApp", "Location API Request Failed: " + e.getMessage());
        }
        catch (JSONException e) {
            Log.i("StargazerWeatherApp", "Location JSON Parse Failed: " + e.getMessage());
        }
        return null;
    }
}
