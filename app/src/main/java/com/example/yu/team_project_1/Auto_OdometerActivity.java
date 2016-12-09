package com.example.yu.team_project_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**Automobile Odometer Activity contains only the fragment.
 * For phone with width < 600dp
 *
 * @author Mochen Jin
 */
public class Auto_OdometerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_activity_odometer);

        final Auto_OdometerFragment odoFragment = new Auto_OdometerFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.auto_odo_fragment_container, odoFragment).commit();

    }
}
