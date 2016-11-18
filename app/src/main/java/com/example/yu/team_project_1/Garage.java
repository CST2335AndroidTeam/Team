package com.example.yu.team_project_1;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Garage extends AppCompatActivity {
    private Switch door;
    private Switch light;
    private static final String GARAGE_SETTING ="House Setting Tool Bar";
    public static final String DIALOG_TITLE = "Do you want to go back?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);

        door = (Switch)findViewById(R.id.door);
        light = (Switch)findViewById(R.id.light);

        door.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CharSequence text;
                int duration;
                if(isChecked){
                    text= "Garage door is opened";
                    duration = Toast.LENGTH_SHORT;
                    light.setChecked(true);

                }else{
                    text = "Garage door is closed ";
                    duration = Toast.LENGTH_SHORT;
                    light.setChecked(false);
                }
                Toast toast = Toast.makeText(Garage.this , text, duration);
                toast.show(); //display your message box

            }
        });

        light.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CharSequence text;
                int duration;
                if(isChecked){
                    text= "Light is on";
                    duration = Toast.LENGTH_SHORT;

                }else{
                    text = "Light is off";
                    duration = Toast.LENGTH_LONG;

                }
                Toast toast = Toast.makeText(Garage.this , text, duration);
                toast.show(); //display your message box

            }
        });

    }


    public boolean onCreateOptionsMenu (Menu m){
        getMenuInflater().inflate(R.menu.garage,m);
        return true;
    }

    //responds to one of the items being selected
    public boolean onOptionsItemSelected(MenuItem mi){
        int id = mi.getItemId();
        switch(id){
            case R.id.g1:
                //TODO: dialog
                Log.d(GARAGE_SETTING,"Item1 is selected");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(DIALOG_TITLE);
                // Add the buttons
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        Intent resultIntent = new Intent(  );
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.cancel();
                    }
                });
                // Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();



                break;
            case R.id.g2:
                //TODO: Display team information
                Log.d(GARAGE_SETTING,"Item2 is selected");
                Toast toast3 = Toast.makeText(Garage.this , "Version 1.0, by Yu Wang", Toast.LENGTH_SHORT);
                toast3.show();
                break;
        }
        return true;
    }

}
