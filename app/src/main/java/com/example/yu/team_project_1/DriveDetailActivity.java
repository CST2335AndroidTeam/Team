package com.example.yu.team_project_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DriveDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive_detail);

        final DriveFragment driveFragment = new DriveFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, driveFragment).commit();


    }
}
