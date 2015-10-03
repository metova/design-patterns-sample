package com.metova.designpatterns.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.metova.designpatterns.R;
import com.metova.designpatterns.data.DataManager;

public class MainActivity extends AppCompatActivity {


    private ListView mListView;

    private ListAdapter mListAdapter;

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

        mListAdapter = new ListAdapter(DataManager.getInstance().getRestaurantList());
        mListView.setAdapter(mListAdapter);
    }

}
