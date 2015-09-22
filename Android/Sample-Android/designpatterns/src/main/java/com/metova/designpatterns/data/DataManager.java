package com.metova.designpatterns.data;

import android.content.Context;
import android.util.Log;

import com.metova.designpatterns.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

public class DataManager {

    private static final String TAG = DataManager.class.getSimpleName();

    private static DataManager dataManager;

    private ArrayList<Restaurant> restaurantList;

    public static DataManager getInstance() {

        if (dataManager == null) {
            dataManager = new DataManager();
        }

        return dataManager;
    }

    public void initialize(Context context) {

        restaurantList = new ArrayList<>();

        JSONObject object = openJsonData(context);
        parseHotChickenJson(object);

    }

    public ArrayList<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    private JSONObject openJsonData(Context context) {

        InputStream is = context.getResources().openRawResource(R.raw.hot_chicken);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];

        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error opening JSON data", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String jsonString = writer.toString();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonString);
        } catch (JSONException e) {
            Log.e(TAG, "Error creating JSON object", e);
        }

        return jsonObject;
    }

    private void parseHotChickenJson(JSONObject parentObject) {

        try {
            JSONArray businessArray = parentObject.getJSONArray("businesses");

            JSONObject restaurantObject;
            Restaurant restaurant;
            for (int i = 0; i < businessArray.length(); i++) {
                restaurantObject = businessArray.getJSONObject(i);
                restaurant = parseRestaurant(restaurantObject);

                if (restaurant != null) {
                    restaurantList.add(restaurant);
                    Log.d(TAG, "Added new restaurant: " + restaurant.name);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Restaurant parseRestaurant(JSONObject restaurantObject) {

        Restaurant restaurant = new Restaurant();

        try {
            restaurant.id = restaurantObject.getString("id");
            restaurant.name = restaurantObject.getString("name");
            restaurant.imageUrl = restaurantObject.getString("image_url");
            restaurant.phone = restaurantObject.getString("phone");
            restaurant.url = restaurantObject.getString("url");
            restaurant.rating = restaurantObject.getDouble("rating");
            restaurant.reviewCount = restaurantObject.getInt("review_count");

        } catch (JSONException e) {
            Log.e(TAG, "Error parsing restaurant", e);
            return null;
        }

        return restaurant;
    }

}
