package com.example.yu.team_project_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class HouseSettings extends AppCompatActivity {
    String[] houseSettingMenu= {"Garage","House Temperature","Weather"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_settings);

        ListView menuLists = (ListView)findViewById(R.id.house_setting_menu);
        ArrayAdapter listAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,houseSettingMenu);
        menuLists.setAdapter(listAdapter);

        menuLists.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l){
                String items =(String)adapterView.getItemAtPosition(i);
                Toast.makeText(view.getContext(),"Welcome to "+ items +"!",Toast.LENGTH_LONG).show();


                if(i == 2){
                    Intent intentWeather= new Intent(HouseSettings.this, Weather.class);
                    startActivity(intentWeather);
                }

            }
        });
    }
}
