package com.example.yu.team_project_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Garage extends AppCompatActivity {
    private Switch door;
    private Switch light;

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
}
