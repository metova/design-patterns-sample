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


/**
 * ADAPTER PATTERN
 * <p/>
 * PROBLEM: It is difficult to reuse different types of views and display them as a list.
 * <p/>
 * INTENT: Creating an intermediary abstraction that translates one component to another. The ListAdapter
 * allows different views to be displayed in a list even though they may have incompatible interfaces.
 * The ListView can display any data provided that is wrapped in a ListAdapter.
 * <p/>
 * INFO: The overridden methods allow the adapter to interact with the underlying data. By calling
 * getCount(), the ListAdapter knows how many items it needs to display. The getView() is then called
 * getCount() number of times. For each call to the getView() method, getItem() is called and the ViewHolder
 * is inflated in that particle view in the ListAdapter. The getId() is used to keep track of the items
 * and for the FlyWeight pattern.
 *
 */

public class ListAdapter extends BaseAdapter {

    private static final String TAG = ListAdapter.class.getSimpleName();

    private ArrayList<Restaurant> mRestaurantList;

    public ListAdapter(ArrayList<Restaurant> restaurantList) {
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
        return getItem(position).getGenerated_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /* FLYWEIGHT PATTERN */
        // Determine if we can need to create a new view or if the list view is recreated a view that
        // it has already received from a getView call. If convertView is null we know that we should
        // create a new view, otherwise we know that getView() has already created the view and we
        // can use our Flyweight ViewHolder object that is cached for us in the tag.
        Restaurant restaurant = getItem(position);
        Context context = parent.getContext();
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.restaurant_item, parent);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (restaurant instanceof Burger) {
            holder.type.setImageResource(R.drawable.burger);
        } else {
            holder.type.setImageResource(R.drawable.chicken);
        }

        Ion.with(context)
                .load(restaurant.getImageUrl())
                .withBitmap()
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .intoImageView(holder.image);

        Ion.with(context)
                .load(restaurant.getRatingUrl())
                .withBitmap()
                .centerInside()
                .intoImageView(holder.stars);

        holder.name.setText(restaurant.getName());
        holder.location.setText(restaurant.getCity());

        return convertView;
    }

}
