package com.example.yu.team_project_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Bryan on 2016-11-28.
 */

public class Lighting2 extends AppCompatActivity {

    private Switch light;
    private SeekBar seekBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lighting_2);

        light = (Switch)findViewById(R.id.light);

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
                Toast toast = Toast.makeText(Lighting2.this , text, duration);
                toast.show(); //display your message box

            }
        });
        seekBar = (SeekBar)findViewById(R.id.seekBar1);
        textView = (TextView)findViewById(R.id.textView1);
        textView.setText("Light Output: " + seekBar.getProgress() + "%" );


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView.setText("Light Output: " + seekBar.getProgress() + "%");
            }
        });
    }



}