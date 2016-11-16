package com.example.yu.team_project_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainMenuActivity extends AppCompatActivity {

    private static String TOOLBAR_MESSAGE = "Toolbar message";
    ImageButton house_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Welcome to YM smart home", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        house_setting = (ImageButton)findViewById(R.id.house_button);
        house_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHouse= new Intent(MainMenuActivity.this, HouseSettings.class);
                startActivity(intentHouse);
            }
        });
    }

    public boolean onCreateOptionsMenu (Menu m){
        getMenuInflater().inflate(R.menu.toolbar,m);
        return true;
    }

    //responds to one of the items being selected
    public boolean onOptionsItemSelected(MenuItem mi){
        int id = mi.getItemId();
        switch(id){
            case R.id.item1:
                //TODO: Developing Custom Themes
                Log.d(TOOLBAR_MESSAGE,"Item1 is selected");

                break;
            case R.id.item2:
                //TODO: Setting device vibrate
                Log.d(TOOLBAR_MESSAGE,"Item2 is selected");

                break;
            case R.id.item4:
                //TODO: Display team information
                Log.d(TOOLBAR_MESSAGE,"Item4 is selected");
                Toast toast3 = Toast.makeText(MainMenuActivity.this , "Version 1.0, by Yu Wang", Toast.LENGTH_SHORT);
                toast3.show();
                break;
        }
        return true;
    }

}
