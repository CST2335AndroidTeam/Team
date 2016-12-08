package com.example.yu.team_project_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by Bryan on 2016-11-28.
 */

public class Lighting1 extends AppCompatActivity {

    private Switch light;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lighting_1);

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
                Toast toast = Toast.makeText(Lighting1.this , text, duration);
                toast.show(); //display your message box

            }
        });
    }
}
