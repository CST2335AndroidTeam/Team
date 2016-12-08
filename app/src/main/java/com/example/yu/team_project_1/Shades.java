package com.example.yu.team_project_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Shades extends AppCompatActivity {

    private Switch shade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shades);

         shade = (Switch)findViewById(R.id.Shades);

        shade.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CharSequence text;
                int duration;
                if(isChecked){
                    text= "Shades are open";
                    duration = Toast.LENGTH_SHORT;

                }else{
                    text = "Shades are closed";
                    duration = Toast.LENGTH_LONG;

                }
                Toast toast = Toast.makeText(Shades.this , text, duration);
                toast.show(); //display your message box

            }
        });
    }
}
