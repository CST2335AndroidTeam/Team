package com.example.yu.team_project_1;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

/**
 * This class is an activity of the garage interface, it includes garage door and light settings
 *
 * @author  Yu Wang  2016.12.05
 * @version 2.2.2
 */
public class YuWang_Garage extends AppCompatActivity {
    /** the switch of garage door*/
    private Switch door;
    /** the switch of garage light*/
    private Switch light;
    /** the dialog title of help setting information*/
    private static final String GARAGE_SETTING ="House Setting Tool Bar";
    /** the dialog title of go back information when user click back icon*/
    public static final String DIALOG_TITLE = "Do you want to go back?";
    ImageView garageClose;
    ImageView lightControl;
    SeekBar brightControl;
    private static String FILENAME ="FIle";

    /**
     * Initialize activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);

        door = (Switch)findViewById(R.id.door);
        light = (Switch)findViewById(R.id.light);
        garageClose =(ImageView)findViewById(R.id.garageClose);
        lightControl =(ImageView)findViewById(R.id.bright);
        lightControl.setVisibility(View.INVISIBLE);
        brightControl =(SeekBar)findViewById(R.id.brightSeekBar);
        brightControl.setVisibility(View.INVISIBLE);

        brightControl.setProgress(100);
        lightControl.setImageAlpha(50);

        //set brightness of the picture
        brightControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int alpha = 10+brightControl.getProgress();
                lightControl.setImageAlpha(alpha);
                //store brightness value to the File
                SharedPreferences pre = getSharedPreferences(FILENAME, Context.MODE_PRIVATE) ;
                SharedPreferences.Editor editor = pre.edit();
                editor.putInt("ALPHA",alpha);
                editor.commit();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        door.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CharSequence text;
                int duration;
                if(isChecked){
                    text= "YuWang_Garage door is opened";
                    duration = Toast.LENGTH_SHORT;
                    light.setChecked(true);
                    garageClose.setImageResource(R.drawable.garageopen);
                    Toast toast = Toast.makeText(YuWang_Garage.this , text, duration);
                    toast.show(); //display your message box
                    lightControl.setVisibility(View.VISIBLE);
                    brightControl.setVisibility(View.VISIBLE);
                    SharedPreferences pre = getSharedPreferences(FILENAME, Context.MODE_PRIVATE) ;
                    SharedPreferences.Editor editor = pre.edit();
                    editor.putBoolean("YES",true);
                    editor.commit();


                }
                if(!isChecked){
                    text = "YuWang_Garage door is closed ";
                    duration = Toast.LENGTH_SHORT;
                    light.setChecked(false);
                    garageClose.setImageResource(R.drawable.garageclose);
                    Toast toast = Toast.makeText(YuWang_Garage.this , text, duration);
                    toast.show(); //display your message box

                    lightControl.setVisibility(View.INVISIBLE);
                    brightControl.setVisibility(View.INVISIBLE);
                    SharedPreferences pre = getSharedPreferences(FILENAME, Context.MODE_PRIVATE) ;
                    SharedPreferences.Editor editor = pre.edit();
                    editor.putBoolean("YES",false);
                    editor.commit();
                }


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
                    lightControl.setVisibility(View.VISIBLE);
                    brightControl.setVisibility(View.VISIBLE);
                    SharedPreferences pre = getSharedPreferences(FILENAME, Context.MODE_PRIVATE) ;
                    SharedPreferences.Editor editor = pre.edit();
                    editor.putBoolean("LIGHT",true);
                    editor.commit();
                    Toast toast = Toast.makeText(YuWang_Garage.this , text, duration);
                    toast.show();

                }

                if (!isChecked){
                    text = "Light is off";
                    duration = Toast.LENGTH_LONG;
                    lightControl.setVisibility(View.INVISIBLE);
                    brightControl.setVisibility(View.INVISIBLE);
                    SharedPreferences pre = getSharedPreferences(FILENAME, Context.MODE_PRIVATE) ;
                    SharedPreferences.Editor editor = pre.edit();
                    editor.putBoolean("LIGHT",false);
                    editor.commit();

                    Toast toast = Toast.makeText(YuWang_Garage.this , text, duration);
                    toast.show();
                }
                //display your message box

            }
        });

    }

    /**
     * When the app started, all the settings that set before will still keep.
     */
    @Override
    public void onStart(){
        super.onStart();
        SharedPreferences pre = getSharedPreferences(FILENAME, Context.MODE_PRIVATE) ;
        Boolean a = pre.getBoolean("YES",false);
        Boolean b = pre.getBoolean("LIGHT",false);
        int n = pre.getInt("ALPHA",50);
        door.setChecked(a);
        light.setChecked(b);
        brightControl.setProgress(n);
        lightControl.setImageAlpha(n);


    }

    /**
     * Inflates the menu from R.menu.toolbar from XML layout
     * @param m  the menu item that built from xml layout
     * @return  true
     */
    public boolean onCreateOptionsMenu (Menu m){
        getMenuInflater().inflate(R.menu.garage,m);
        return true;
    }

    /**responds to one of the items being selected*/
    public boolean onOptionsItemSelected(MenuItem mi){
        int id = mi.getItemId();
        switch(id){
            case R.id.g1:
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
                Toast toast3 = Toast.makeText(YuWang_Garage.this , "Version 2.2.2, by Yu Wang", Toast.LENGTH_SHORT);
                toast3.show();
                break;
        }
        return true;
    }

}
