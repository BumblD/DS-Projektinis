package com.example.dovydas.kaunasbusroutes;

import BusData.*;
import SparseArray.*;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AllRoutesActivity extends AppCompatActivity {

    SparseArrayFixed<Bus> arr = new SparseArrayFixed<>(70);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_routes);

        TextView tv = findViewById(R.id.textView3);
        tv.setTextColor(Color.BLACK);
        tv.setTextSize(18);

        View v = findViewById(R.id.view);
        v.setBackgroundColor(Color.DKGRAY);

        getAllRoutesList();
    }

    private void getAllRoutesList() {
        try {
            InputStream str = getResources().getAssets().open("bus_stops_data.txt");
            arr = DataReader.readBusData(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // all-appTheme
        LinearLayout routesLayout = findViewById(R.id.routesListLayout);

        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < 71; i ++) {
            if (arr.get(i) != null) {
                TextView tv = new TextView(this);
                tv.setLayoutParams(lparams);
                tv.setText("Nr." + i + " maršruto: " + arr.get(i).getRouteName() + " stotelių sąrašas:");
                tv.setTextColor(Color.BLACK);
                routesLayout.addView(tv);
                List<String> routes = arr.get(i).getRouteList();
                for (int j = 0; j < routes.size(); j++) {
                    TextView tv2 = new TextView(this);
                    tv2.setLayoutParams(lparams);
                    tv2.setText("    ➟ " + routes.get(j));
                    tv2.setTextColor(Color.BLACK);
                    routesLayout.addView(tv2);
                }
                TextView tv3 = new TextView(this);
                tv3.setLayoutParams(lparams);
                tv3.setText("\n");
                routesLayout.addView(tv3);
            }
        }
    }
}
