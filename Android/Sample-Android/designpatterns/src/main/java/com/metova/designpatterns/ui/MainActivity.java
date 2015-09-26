package com.metova.designpatterns.ui;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.metova.designpatterns.R;
import com.metova.designpatterns.data.DataManager;

public class MainActivity extends AppCompatActivity {


    private ListView mListView;

    private HotChickenListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = (ListView) findViewById(R.id.hot_chicken_list_view);

        loadList();
    }

    public void loadList(){

        DataManager.getInstance().initialize(this);

        mListAdapter = new HotChickenListAdapter(DataManager.getInstance().getRestaurantList(), this);
        mListView.setAdapter(mListAdapter);
    }

}
