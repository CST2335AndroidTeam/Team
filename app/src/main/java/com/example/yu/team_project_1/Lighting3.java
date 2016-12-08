package com.example.yu.team_project_1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Bryan on 2016-11-28.
 */

public class Lighting3 extends AppCompatActivity {

    private Switch light;
    private SeekBar red;
    private SeekBar blue;
    private SeekBar green;
    private SeekBar dimmer;

    private TextView textRed;
    private TextView textBlue;
    private TextView textGreen;
    private TextView dimmerText;
    private View ledView;

    public int redComponent;
    public int blueComponent;
    public int greenComponet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lighting_3);

        light = (Switch) findViewById(R.id.light);

        light.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CharSequence text;
                int duration;
                if (isChecked) {
                    text = "Light is on";
                    duration = Toast.LENGTH_SHORT;

                    red.setEnabled(true);
                    textRed.setEnabled(true);
                    blue.setEnabled(true);
                    textBlue.setEnabled(true);
                    green.setEnabled(true);
                    textGreen.setEnabled(true);
                    dimmer.setEnabled(true);
                    dimmerText.setEnabled(true);

                } else {
                    text = "Light is off";
                    duration = Toast.LENGTH_LONG;

                    red.setEnabled(false);
                    textRed.setEnabled(false);
                    blue.setEnabled(false);
                    textBlue.setEnabled(false);
                    green.setEnabled(false);
                    textGreen.setEnabled(false);
                    dimmer.setEnabled(false);
                    dimmerText.setEnabled(false);

                }
                Toast toast = Toast.makeText(Lighting3.this, text, duration);
                toast.show(); //display your message box

            }
        });

        red = (SeekBar)findViewById(R.id.red);
        red.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
              return !red.isEnabled();
            }
        });
        textRed= (TextView)findViewById(R.id.textRed);
        textRed.setText("Red Output: " + red.getProgress());
        blue = (SeekBar)findViewById(R.id.blue);
        textBlue = (TextView)findViewById(R.id.textBlue);
        textBlue.setText("Blue Output: " + blue.getProgress());
        green = (SeekBar)findViewById(R.id.green);
        textGreen = (TextView)findViewById(R.id.textGreen);
        textGreen.setText("Green Output: " + green.getProgress());
        dimmer = (SeekBar)findViewById(R.id.dimmer);
        dimmerText = (TextView)findViewById(R.id.dimmerTextView);
        dimmerText.setText("Light Output: " + dimmer.getProgress());

        red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar red, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar red) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar red) {
                textRed.setText("Red Output: " + red.getProgress());
                updateBackground();

            }
        });


        blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar blue, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar blue) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar blue) {
                textBlue.setText("Blue Output: " + blue.getProgress());
                updateBackground();
            }
        });


        green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar green, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar green) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar green) {
                textGreen.setText("Green Output: " + green.getProgress());
                updateBackground();
            }
        });


        dimmer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar dimmer, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar dimmer) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar dimmer) {
                dimmerText.setText("Light Output: " + dimmer.getProgress() + "%");
            }
        });
        ledView = (View)findViewById(R.id.ledView);
    }

    private void updateBackground() {
        ledView.setBackgroundColor(Color.argb(255, red.getProgress(),green.getProgress(), blue.getProgress()));
    }}