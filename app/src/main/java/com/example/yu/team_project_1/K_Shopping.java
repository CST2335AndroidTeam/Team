package com.example.yu.team_project_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by CARLOS MENA... (c) Camesys.
 *
 * Class to display the menu for the shopping option.
 */
public class K_Shopping extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "K_Shopping";
    Button showList;
    Button showFlyers;
    Button reminder;
    Button goback;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.k_go_shopping);
        setTitle(R.string.carlos_app_name);

        showList = (Button) findViewById(R.id.K_Shop_B1);
        showFlyers = (Button) findViewById(R.id.K_Shop_B2);
        reminder = (Button) findViewById(R.id.K_Shop_B3);
        goback = (Button) findViewById(R.id.K_Shop_B4);

        showList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(K_Shopping.this, K_Shopping_List.class);
                startActivityForResult(intent, 5);
            }
        });

        showFlyers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(K_Shopping.this, K_FlyerListActivity.class);
                startActivityForResult(intent, 5);
            }
        });

        reminder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Snackbar.make(view, getString(R.string.K_Snack1), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        goback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

} // *-* End of file *-*

