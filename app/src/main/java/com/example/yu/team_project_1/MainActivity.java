package com.example.yu.team_project_1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button livingRoomButton;
    private Button kitchenButton;
    private Button houseSettingButton;
    private Button automobileButton;
    private static String Group ="Team Project Information";


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
                startActivity(new Intent(MainActivity.this, HouseMain.class));
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


    @Override
    public boolean onCreateOptionsMenu (Menu m){
        getMenuInflater().inflate(R.menu.mainmenutoolbar,m);
        return true;
    }

    @Override
    //responds to one of the items being selected
    public boolean onOptionsItemSelected(MenuItem mi) {
        int id = mi.getItemId();
        if(id == R.id.helpMain){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(Group);
            // Add the buttons
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                }
            });
            builder.setMessage("Group member: Byran Mack, Carlos Mena, MoChen Jin, Yu Wang, " +
                    "\nVersion: 2.2.2\nInstruction: \nYM smart home is an integrated household control application," +
                    "There are 4 interfaces for this app(each group member makes their own part application): living room(Byran), " +
                    "kitchen(Carlos), house setting(Yu) and automobile(MoChen).");

            // Create the AlertDialog
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return true;
    }
}
