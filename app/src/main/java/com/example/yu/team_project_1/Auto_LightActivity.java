package com.example.yu.team_project_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**Automobile Light Activity contains only the fragment.
 * For phone with width < 600dp
 *
 * @author Mochen Jin
 */
public class Auto_LightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto__light);

        final Auto_LightFragment lightFragment = new Auto_LightFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.auto_light_fragment_container, lightFragment).commit();

    }
}
