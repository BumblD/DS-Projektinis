package com.example.dovydas.kaunasbusroutes;

import BusData.*;
import SparseArray.SparseArrayFixed;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SearchBarActivity extends AppCompatActivity {

    SparseArrayFixed<Bus> arr = new SparseArrayFixed<>(70);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);

        getSearchBarRoute();
    }

    private void getSearchBarRoute() {
        try {
            InputStream str = getResources().getAssets().open("bus_stops_data.txt");
            arr = DataReader.readBusData(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LinearLayout searchLayout = findViewById(R.id.searchBarRouteLayout);
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        String s = getIntent().getStringExtra("number");
        int num = Integer.parseInt(s);

        TextView tv = findViewById(R.id.textView4);
        tv.setText("Nr." + num + " Maršruto " + arr.get(num).getRouteName() + " stotelių sąrašas :");
        List<String> routesList = arr.get(num).getRouteList();

        for (int i = 0; i < routesList.size(); i++) {
            TextView tv2 = new TextView(this);
            tv2.setLayoutParams(lparams);
            tv2.setText("    └> " + routesList.get(i));
            searchLayout.addView(tv2);
        }
    }
}
