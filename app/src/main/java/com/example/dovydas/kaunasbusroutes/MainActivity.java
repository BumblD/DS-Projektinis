package com.example.dovydas.kaunasbusroutes;

import BusData.*;
import SparseArray.SparseArrayFixed;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    public SparseArrayFixed<Bus> arr = new SparseArrayFixed<>(70);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchView search = findViewById(R.id.searchView2);
        search.setQueryHint("Įveskite autobuso ID");
        search.setFocusable(false);

        TextView error = findViewById(R.id.errorText);
        error.setText("");
        error.setTextColor(Color.RED);

        TextView title = findViewById(R.id.textView2);
        title.setTextSize(24);
        title.setAllCaps(true);
        title.setTypeface(null, Typeface.BOLD);
        title.setTextColor(Color.BLACK);
        title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        ImageView iv = findViewById(R.id.imageView);
        iv.setImageResource(R.drawable.herbas);

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

    @Override
    protected void onResume() {
        super.onResume();
        TextView error = findViewById(R.id.errorText);
        error.setText("");

        SearchView search = findViewById(R.id.searchView2);
        search.setQueryHint("Įveskite autobuso ID");
        search.setQuery("", false);
        search.setFocusable(false);
        search.clearFocus();
    }

    private void configureBusListButton() {
        Button busListButton = findViewById(R.id.buttonBusList);
        busListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent busListActivity = new Intent(MainActivity.this, BusListActivity.class);
                startActivity(busListActivity);
            }
        });
    }

    private void configureAllRoutesListButton() {
        Button busListButton = findViewById(R.id.buttonRouteList);
        busListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent allRoutesActivity = new Intent(MainActivity.this, AllRoutesActivity.class);
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
                if (android.text.TextUtils.isDigitsOnly(s)) {
                    if (arr.get(Integer.parseInt(s)) != null) {
                        Intent searchBarActivity = new Intent(MainActivity.this, SearchBarActivity.class).putExtra("number", s);
                        startActivity(searchBarActivity);
                    } else {
                        TextView error = findViewById(R.id.errorText);
                        error.setText("Tokio autobuso ID nėra!");
                    }
                } else {
                    TextView error = findViewById(R.id.errorText);
                    error.setText("Klaidingai įvestas/neįvestas autobuso ID!");
                }
            }
        });
    }
}
