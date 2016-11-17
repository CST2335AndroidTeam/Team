package com.example.yu.team_project_1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class AutomobileMainActivity extends AppCompatActivity {
    private ListView listview;


    final static int TEMPATURE = 0;
    final static int FUEL = 1;
    final static int RADIO = 2;
    final static int GPS = 3;
    final static int LIGHT = 4;
    final static int ODOMETER = 5;
    final static int DRIVE = 6;

    //An array setting names.
    String[] settingItemsArr  = {
            "Temperature",
            "Fuel",
            "Radio",
            "GPS Directions",
            "Lights",
            "Odometer",
            "Drive" };

    protected ArrayList<String> settingItems = new ArrayList<>(Arrays.asList(settingItemsArr));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile_main);


        listview = (ListView)findViewById(R.id.automobile_list);
        MyAdapter adapter = new MyAdapter(this);
        listview.setAdapter(adapter);



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case TEMPATURE:
                        break;
                    case FUEL:
                        break;
                    case RADIO:
                        break;
                    case GPS:
                        break;
                    case LIGHT:
                        break;
                    case ODOMETER:
                        break;
                    case DRIVE:
                        startActivity(new Intent(AutomobileMainActivity.this, DriveDetailActivity.class));
                        break;
                }
            }
        });
    }


    private class MyAdapter extends ArrayAdapter<String> {
        MyAdapter(Context ctx){
            super(ctx,0);
        }

        @Override
        public int getCount() {
            return settingItems.size();
        }

        @Override
        public String getItem(int position) {
            return settingItems.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = AutomobileMainActivity.this.getLayoutInflater();
            View result = inflater.inflate(R.layout.auto_setting_row, null);

            int imageid = 0;
            switch(position){
                case TEMPATURE:
                    imageid = R.drawable.temperature;
                    break;
                case FUEL:
                    imageid = R.drawable.fuel;
                    break;
                case RADIO:
                    imageid = R.drawable.radio;
                    break;
                case GPS:
                    imageid = R.drawable.map;
                    break;
                case LIGHT:
                    imageid = R.drawable.light;
                    break;
                case ODOMETER:
                    imageid = R.drawable.odometer;
                    break;
                case DRIVE:
                    imageid = R.drawable.drive;
                    break;

            }
            ImageView imageView = (ImageView) result.findViewById(R.id.setting_image);
            imageView.setImageResource(imageid);

            TextView textView = (TextView) result.findViewById(R.id.setting_text);
            textView.setText(getItem(position));


            return result;
        }
    }
}
