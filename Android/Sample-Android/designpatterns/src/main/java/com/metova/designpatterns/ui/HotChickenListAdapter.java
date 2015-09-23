package com.metova.designpatterns.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.metova.designpatterns.data.Restaurant;

import java.util.ArrayList;

public class HotChickenListAdapter extends BaseAdapter{

    private ArrayList<Restaurant> mRestaurantList;

    public HotChickenListAdapter(ArrayList<Restaurant> restaurantList) {
        mRestaurantList = restaurantList;
    }

    @Override
    public int getCount() {
        return mRestaurantList.size();
    }

    @Override
    public Restaurant getItem(int position) {
        return mRestaurantList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).generated_id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
