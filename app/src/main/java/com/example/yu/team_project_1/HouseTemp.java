package com.example.yu.team_project_1;

import android.app.Activity;
import android.app.Dialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.id.message;
import static com.example.yu.team_project_1.R.string.set;

public class HouseTemp extends AppCompatActivity{
    TextView tempCur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_temp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Welcome to set temperature", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        tempCur = (TextView)findViewById(R.id.cur_temp);

    }
    public boolean onCreateOptionsMenu (Menu m){
        getMenuInflater().inflate(R.menu.temp,m);
        return true;
    }

    //responds to one of the items being selected
    public boolean onOptionsItemSelected(MenuItem mi){
        int id = mi.getItemId();
        switch(id){
            case R.id.t1:
                //Set tempereture dialog
                NumberPicker numberPicker = new NumberPicker(this);
                numberPicker.setMaxValue(40);
                numberPicker.setMinValue(0);
                numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        tempCur.setText((newVal + " \u2103"));
                    }
                });
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this).setView(numberPicker);

                builder2.setTitle("Set Temperature To")
                        .setIcon(R.drawable.pig)
                        .setPositiveButton(R.string.set, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK button
                                Intent resultIntent = new Intent(  );
                                setResult(Activity.RESULT_OK, resultIntent);
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                                dialog.cancel();
                            }
                        });

                builder2.create().show();

                break;
            case R.id.t2:
                //TODO: Setting device vibrate


                break;
            case R.id.t3:
                //TODO: Display team information

                Toast toast3 = Toast.makeText(HouseTemp.this , "Version 1.0, by Yu Wang", Toast.LENGTH_SHORT);
                toast3.show();
                break;
        }
        return true;
    }


}
