package com.metova.designpatterns.ui;

import com.koushikdutta.ion.Ion;
import com.metova.designpatterns.R;
import com.metova.designpatterns.data.Burger;
import com.metova.designpatterns.data.Restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private static final String TAG = ListAdapter.class.getSimpleName();

    private ArrayList<Restaurant> mRestaurantList;

    private Context mContext;

    public ListAdapter(ArrayList<Restaurant> restaurantList, Context context) {
        mRestaurantList = restaurantList;
        mContext = context;
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
        return getItem(position).getGenerated_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Restaurant restaurant = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item, null);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (restaurant instanceof Burger) {
            holder.type.setImageResource(R.drawable.burger);
        } else {
            holder.type.setImageResource(R.drawable.chicken);
        }

        Ion.with(mContext)
                .load(restaurant.getImageUrl())
                .withBitmap()
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .intoImageView(holder.image);

        Ion.with(mContext)
                .load(restaurant.getRatingUrl())
                .withBitmap()
                .centerInside()
                .intoImageView(holder.stars);

        holder.name.setText(restaurant.getName());
        holder.location.setText(restaurant.getCity());

        return convertView;
    }


}
