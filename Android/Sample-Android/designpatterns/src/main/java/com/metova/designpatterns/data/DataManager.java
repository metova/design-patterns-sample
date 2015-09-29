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
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;

/**
 * SINGLETON PATTERN
 * <p/>
 * PROBLEM: Application needs one, and only one, instance of an object. Additionally, lazy initialization and global access are necessary.
 * <p/>
 * INTENT: Ensure a class has only one instance, and provide a global point of access to it.
 * Encapsulated "just-in-time initialization" or "initialization on first use"
 * <p/>
 * INFO: The static getInstance() method provides any class with the same instance of this class.
 * The the first time getInstance() is called, a new DataManager object is instantiated.
 * Future calls to getInstance() will return the same instance of the DataManager object.
 *
 */

public class DataManager {

    private static final String TAG = DataManager.class.getSimpleName();

    /** Check list item 1: define a private static attribute in the singleton class */
    private static DataManager dataManager;

    private ArrayList<Restaurant> restaurantList;

    /** Check list item 2: define a public static accessor function in the class */
    public static DataManager getInstance() {

    /** Check list item 3: do "lazy instantiation" in the accessor function
    * Check list item 4: private/protected constructor */
        if (dataManager == null) {
            dataManager = new DataManager();
        }

        return dataManager;
    }

    public void initialize(Context context) {

        restaurantList = new ArrayList<>();

        JSONObject object = openJsonData(context, R.raw.hot_chicken);
        parseJson(object, RestaurantFactory.RestaurantType.HOT_CHICKEN);
        object = openJsonData(context, R.raw.burger);
        parseJson(object, RestaurantFactory.RestaurantType.BURGER);

    }

    public ArrayList<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    private void parseJson(JSONObject parentObject, RestaurantFactory.RestaurantType type) {

        try {
            JSONArray businessArray = parentObject.getJSONArray("businesses");

            JSONObject restaurantObject;
            Restaurant restaurant;
            for (int i = 0; i < businessArray.length(); i++) {
                restaurantObject = businessArray.getJSONObject(i);
                restaurant = parseRestaurant(restaurantObject, i, type);

                if (restaurant != null) {
                    restaurantList.add(restaurant);
                    Log.d(TAG, "Added new restaurant: " + restaurant.getName());
                }
            }

            Collections.sort(restaurantList, new Comparator<Restaurant>() {
                @Override
                public int compare(Restaurant lhs, Restaurant rhs) {
                    return  lhs.getName().compareTo(rhs.getName());
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Restaurant parseRestaurant(JSONObject restaurantObject, long id, RestaurantFactory.RestaurantType type) {

        Restaurant restaurant = RestaurantFactory.getRestaurant(type);

        if (restaurant != null) {
            try {
                restaurant.setGenerated_id(id);
                restaurant.setId(restaurantObject.getString("id"));
                restaurant.setName(restaurantObject.getString("name"));
                restaurant.setImageUrl(restaurantObject.getString("image_url"));
                restaurant.setPhone(restaurantObject.getString("phone"));
                restaurant.setUrl(restaurantObject.getString("url"));
                restaurant.setRating(restaurantObject.getDouble("rating"));
                restaurant.setRatingUrl(restaurantObject.getString("rating_img_url"));
                restaurant.setReviewCount(restaurantObject.getInt("review_count"));

                JSONObject locationObject = restaurantObject.getJSONObject("location");
                restaurant.setCity(locationObject.getString("city"));
                restaurant.setAddress(locationObject.getString("address"));
                restaurant.setZipCode(locationObject.getString("postal_code"));
                restaurant.setState(locationObject.getString("state_code"));

            } catch (JSONException e) {
                Log.e(TAG, "Error parsing restaurant", e);
                return null;
            }
        }

        return restaurant;
    }

    private JSONObject openJsonData(Context context, int resourceId) {

        InputStream is = context.getResources().openRawResource(resourceId);
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

}
