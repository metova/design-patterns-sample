package com.metova.designpatterns.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.metova.designpatterns.R;
import com.metova.designpatterns.data.Restaurant;

import java.util.ArrayList;

public class HotChickenListAdapter extends BaseAdapter {

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

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_chicken_item, null);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Restaurant restaurant = getItem(position);

        holder.name.setText(restaurant.name);
        holder.location.setText("Nashville");

        return convertView;
    }

    public static class ViewHolder {

        public ImageView image;
        public TextView name;
        public TextView location;

        public ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.image);
            name = (TextView) view.findViewById(R.id.name);
            location = (TextView) view.findViewById(R.id.location);

            view.setTag(this);
        }

    }

}
