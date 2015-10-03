package com.metova.designpatterns.ui;

import com.metova.designpatterns.R;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * FLYWEIGHT PATTERN
 * <p/>
 * PROBLEM: findViewById() is an expensive method because it traverses through the view hierarchy
 * to find a view with a matching id. If called frequently it can affect ListView performance.
 * <p/>
 * INTENT: Create a ViewHolder class to act as a Flyweight object. Only create one instance
 * and then cache it for future use so any unneeded findViewById() calls will not be invoked.
 * <p/>
 * INFO: The ListView will make calls to the ListAdapter's getView() method each time it needs a view
 * at a given index. Because the ListView only holds a certain amount of views in memory at a time
 * it can end up recreating the same views over and over as needed, resulting in a lot of calls to
 * findViewById() for each new view it creates. To avoid this performance hit we can cache each
 * view returned from findViewById() into a ViewHolder object that can be reused the next time
 * the ListView recreates the same view.
 *
 */
public class ViewHolder {

    public final ImageView image;
    public final TextView name;
    public final TextView location;
    public final ImageView stars;
    public final ImageView type;

    public ViewHolder(View view) {
        image = (ImageView) view.findViewById(R.id.image);
        name = (TextView) view.findViewById(R.id.name);
        location = (TextView) view.findViewById(R.id.location);
        stars = (ImageView) view.findViewById(R.id.stars);
        type = (ImageView) view.findViewById(R.id.type);

        /* FLYWEIGHT PATTERN */
        // Cache the ViewHolder object into the tag of the view so that it can be retrieved with
        // a getTag() call for the next time it is needed.
        view.setTag(this);
    }

}


