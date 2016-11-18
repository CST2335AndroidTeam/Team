package com.example.yu.team_project_1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class HouseTemp extends AppCompatActivity {
    NumberPicker numberpicker;
    TextView textview;
    private static String MASSAGE ="Set House Temperature To: ";

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        numberpicker = (NumberPicker)findViewById(R.id.numberPicker1);
        textview = (TextView)findViewById(R.id.textView1);
        numberpicker.setMinValue(0);
        numberpicker.setMaxValue(30);
        numberpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                textview.setText((MASSAGE + newVal + " \u2103"));
            }
        });

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
                //TODO: Developing Custom Themes

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
