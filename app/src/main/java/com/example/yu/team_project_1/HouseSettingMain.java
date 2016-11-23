package com.example.yu.team_project_1;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class HouseSettingMain extends AppCompatActivity {

    private static String SETTING_MESSAGE ="House Setting Tool Bar";
    private static String INFORMATION = "Information";

    String[] houseSettingMenu= {"Garage","House Temperature","Weather"};
    protected ArrayList<String> menuItems = new ArrayList<>(Arrays.asList(houseSettingMenu));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_setting_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Welcome to YM Smart Home - house setting", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        ListView menuLists = (ListView)findViewById(R.id.house_setting_menu);
        HouseAdapter adapter = new HouseAdapter(this);
        menuLists.setAdapter(adapter);


//
//        menuLists.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView adapterView, View view, int i, long l){
//                String items =(String)adapterView.getItemAtPosition(i);
//                Toast.makeText(view.getContext(),"Welcome to "+ items +"!",Toast.LENGTH_LONG).show();
//
//                if(i == 0){
//                    Intent intentGarage = new Intent(HouseSettingMain.this,Garage.class);
//                    startActivityForResult(intentGarage,2);
//                }
//                if(i == 1){
//                    //TODO : Temperature
//                    Intent intentTemp = new Intent(HouseSettingMain.this,HouseTemp.class);
//                    startActivityForResult(intentTemp,1);
//                }
//                if(i == 2){
//                    Intent intentWeather= new Intent(HouseSettingMain.this, Weather.class);
//                    startActivity(intentWeather);
//                }
//
//            }
//        });

    }

    private class HouseAdapter extends ArrayAdapter<String> {
        HouseAdapter(Context ctx) {
            super(ctx, 0);
        }

        @Override
        public int getCount() {
            return menuItems.size();
        }

        @Override
        public String getItem(int position) {
            return menuItems.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = HouseSettingMain.this.getLayoutInflater();
            View result = inflater.inflate(R.layout.menu_list, null);

            int rowId = 0;
            switch (position) {
                case 0:
                    rowId = R.drawable.garage;
                    break;
                case 1:
                    rowId = R.drawable.temp;
                    break;
                case 2:
                    rowId = R.drawable.weather;
                    break;

            }
            ImageView imageView = (ImageView) result.findViewById(R.id.house_menu_image);
            imageView.setImageResource(rowId);

            TextView textView = (TextView) result.findViewById(R.id.house_menu_text);
            textView.setText(getItem(position));


            return result;
        }
    }


    public boolean onCreateOptionsMenu (Menu m){
        getMenuInflater().inflate(R.menu.toolbar,m);
        return true;
    }

    //responds to one of the items being selected
    public boolean onOptionsItemSelected(MenuItem mi){
        int id = mi.getItemId();
        switch(id){
            case R.id.item1:

                Intent intentGarage = new Intent(HouseSettingMain.this,Garage.class);
                startActivityForResult(intentGarage,2);

                break;
            case R.id.item2:
                Intent intentTemp = new Intent(HouseSettingMain.this,HouseTemp.class);
                startActivityForResult(intentTemp,1);

                break;
            case R.id.item3:

                Intent intentWeather= new Intent(HouseSettingMain.this, Weather.class);
                startActivity(intentWeather);

                break;
            case R.id.item4:
                //TODO: Setting device vibrate
                Log.d(SETTING_MESSAGE,"Item2 is selected");

                break;
            case R.id.item5:
                //TODO: Display team information
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(INFORMATION);
                // Add the buttons
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                    }
                });
                //TODO: add more information to this set message about how to use this interface
                builder.setMessage("Author: Yu Wang\nVersion: 2.2.2\nInstruction: House Setting interface include the list of garage, house" +
                        "temperature, and weather;\n");

                // Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }
        return true;
    }

}
