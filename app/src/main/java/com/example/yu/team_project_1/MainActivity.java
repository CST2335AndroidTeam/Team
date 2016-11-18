package com.example.yu.team_project_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button livingRoomButton;
    private Button kitchenButton;
    private Button houseSettingButton;
    private Button automobileButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        livingRoomButton = (Button) findViewById(R.id.living_room_button);
        livingRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Living_Room.class));
            }
        });
        kitchenButton= (Button) findViewById(R.id.kitchen_button);
        kitchenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                //start kitchen acitivty
            }
        });
        houseSettingButton = (Button) findViewById(R.id.house_setting_button);
        houseSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HouseSettingMain.class));
            }
        });
        automobileButton = (Button)findViewById(R.id.automobile_button);
        automobileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AutomobileMainActivity.class));
            }
        });
    }
}
