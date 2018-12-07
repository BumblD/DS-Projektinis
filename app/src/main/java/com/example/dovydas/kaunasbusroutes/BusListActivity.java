package com.example.dovydas.kaunasbusroutes;

import BusData.Bus;
import BusData.DataReader;
import SparseArray.SparseArrayFixed;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class BusListActivity extends AppCompatActivity {

    SparseArrayFixed<Bus> arr = new SparseArrayFixed<>(70);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_list);

            getBusList();
    }

    private void getBusList() {
        try {
            InputStream str = getResources().getAssets().open("bus_stops_data.txt");
            arr = DataReader.readBusData(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LinearLayout busLayout = findViewById(R.id.busListLayout);
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < 71; i ++) {
            if (arr.get(i) != null) {
                TextView tv = new TextView(this);
                tv.setLayoutParams(lparams);
                tv.setText(i + " -> " + arr.get(i).getRouteName() + "\n");
                busLayout.addView(tv);
            }
        }
    }
}
