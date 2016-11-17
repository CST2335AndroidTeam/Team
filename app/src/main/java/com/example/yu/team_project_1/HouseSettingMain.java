package com.example.yu.team_project_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class HouseSettingMain extends AppCompatActivity {

    private static String SETTING_MESSAGE ="House Setting Tool Bar";

    String[] houseSettingMenu= {"Garage","House Temperature","Weather"};
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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        ListView menuLists = (ListView)findViewById(R.id.house_setting_menu);
        ArrayAdapter listAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,houseSettingMenu);
        menuLists.setAdapter(listAdapter);

        menuLists.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l){
                String items =(String)adapterView.getItemAtPosition(i);
                Toast.makeText(view.getContext(),"Welcome to "+ items +"!",Toast.LENGTH_LONG).show();

                if(i == 0){
                    Intent intentGarage = new Intent(HouseSettingMain.this,Garage.class);
                    startActivityForResult(intentGarage,2);
                }
                if(i == 1){

                }
                if(i == 2){
                    Intent intentWeather= new Intent(HouseSettingMain.this, Weather.class);
                    startActivity(intentWeather);
                }

            }
        });
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
                //TODO: Developing Custom Themes
                Log.d(SETTING_MESSAGE,"Item1 is selected");


                break;
            case R.id.item2:
                //TODO: Setting device vibrate
                Log.d(SETTING_MESSAGE,"Item2 is selected");

                break;
            case R.id.item4:
                //TODO: Display team information
                Log.d(SETTING_MESSAGE,"Item4 is selected");
                Toast toast3 = Toast.makeText(HouseSettingMain.this , "Version 1.0, by Yu Wang", Toast.LENGTH_SHORT);
                toast3.show();
                break;
        }
        return true;
    }

}
