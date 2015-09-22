package com.metova.designpatterns;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.metova.designpatterns.data.DataManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DataManager.getInstance().initialize(this);

        TextView testText = (TextView) findViewById(R.id.restaurant_size_text);
        testText.setText("There are " + DataManager.getInstance().getRestaurantList().size()
                + " hot chicken restaurants in Nashville");
    }

}
