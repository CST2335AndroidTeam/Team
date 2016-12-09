package com.example.yu.team_project_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
/**Automobile Radio Activity contains only the fragment.
 * For phone with width < 600dp
 *
 * @author Mochen Jin
 */
public class Auto_RadioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_activity_radio);

        final Auto_RadioFragment radioFragment = new Auto_RadioFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.auto_radio_fragment_container, radioFragment).commit();
    }
}
