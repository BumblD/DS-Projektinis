package com.example.dovydas.kaunasbusroutes;

import BusData.*;
import SparseArray.SparseArrayFixed;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    public SparseArrayFixed<Bus> arr = new SparseArrayFixed<>(70);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            InputStream str = getResources().getAssets().open("bus_stops_data.txt");
            arr = DataReader.readBusData(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

        configureBusListButton();
        configureAllRoutesListButton();
        configureSearchButton();
    }

    private void configureBusListButton() {
        Button busListButton = findViewById(R.id.buttonBusList);
        busListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent busListActivity = new Intent(MainActivity.this, BusListActivity.class)/*.putExtra("dataArr", (Parcelable) arr)*/;
                startActivity(busListActivity);
            }
        });
    }

    private void configureAllRoutesListButton() {
        Button busListButton = findViewById(R.id.buttonRouteList);
        busListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent allRoutesActivity = new Intent(MainActivity.this, AllRoutesActivity.class)/*.putExtra("dataArr", (Parcelable) arr)*/;
                startActivity(allRoutesActivity);
            }
        });
    }

    private void configureSearchButton() {
        Button busListButton = findViewById(R.id.button5);
        busListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchView search = findViewById(R.id.searchView2);
                String s = search.getQuery().toString();
                Intent searchBarActivity = new Intent(MainActivity.this, SearchBarActivity.class).putExtra("number", s);
                startActivity(searchBarActivity);
            }
        });
    }
}
