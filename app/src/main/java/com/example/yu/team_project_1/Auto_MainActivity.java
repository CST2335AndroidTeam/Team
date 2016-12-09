package com.example.yu.team_project_1;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class Auto_MainActivity extends AppCompatActivity {
    private ListView listview;
    private EditText addressBox;

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
            "Drive"
    };

    protected boolean isTablet;
    protected ArrayList<String> settingItems = new ArrayList<>(Arrays.asList(settingItemsArr));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile_main);

        isTablet = (findViewById(R.id.auto_setting_container) != null);



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
                        if(isTablet) {
                            final Auto_RadioFragment radioFragment = new Auto_RadioFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.auto_setting_container, radioFragment).commit();
                        }else {
                            startActivity(new Intent(Auto_MainActivity.this, Auto_RadioActivity.class));
                        }
                        break;

                    case GPS:
                        goToGPS();
                        break;
                    case LIGHT:
                        if(isTablet) {
                            final Auto_LightFragment lightFragment = new Auto_LightFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.auto_setting_container, lightFragment).commit();
                        }else {
                            startActivity(new Intent(Auto_MainActivity.this, Auto_LightActivity.class));
                        }
                        break;
                    case ODOMETER:
                        break;
                    case DRIVE:
                        if(isTablet) {
                            final Auto_DriveFragment driveFragment = new Auto_DriveFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.auto_setting_container, driveFragment).commit();
                        }else {
                            startActivity(new Intent(Auto_MainActivity.this, Auto_DriveDetailActivity.class));
                        }
                        break;
                }
            }
        });
    }

    private void goToGPS(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dlg = inflater.inflate(R.layout.auto_navigation_dialog, null);
        addressBox =(EditText) dlg.findViewById(R.id.auto_dialog_address) ;
        builder.setView(dlg);
        builder.setMessage(R.string.navigation_dialog_title);
        builder.setPositiveButton(R.string.auto_navigation_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String address = addressBox.getText().toString();
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                Uri.parse("google.navigation:q=" +address ));
                        startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create().show();
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
            LayoutInflater inflater = Auto_MainActivity.this.getLayoutInflater();
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
