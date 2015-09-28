package com.metova.designpatterns.ui;

import com.metova.designpatterns.R;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {

    public ImageView image;
    public TextView name;
    public TextView location;
    public ImageView stars;
    public ImageView type;

    public ViewHolder(View view) {
        image = (ImageView) view.findViewById(R.id.image);
        name = (TextView) view.findViewById(R.id.name);
        location = (TextView) view.findViewById(R.id.location);
        stars = (ImageView) view.findViewById(R.id.stars);
        type = (ImageView) view.findViewById(R.id.type);

        view.setTag(this);
    }

}


