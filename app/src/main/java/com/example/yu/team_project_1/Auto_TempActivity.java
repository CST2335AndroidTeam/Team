package com.example.yu.team_project_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**Automobile Temperature Activity contains only the fragment.
 * For phone with width < 600dp
 *
 * @author Mochen Jin
 */
public class Auto_TempActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_activity_temp);
        final Auto_TempFragment tempFragment = new Auto_TempFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.auto_temp_fragment_container, tempFragment).commit();
    }
}
