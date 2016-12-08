package com.example.yu.team_project_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

/**
 * Created by CARLOS MENA... (c) Camesys.
 *
 * Class K_FlyerDetailActivity
 */
public class K_FlyerDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.k_activity_flyer_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {

            Bundle arguments = new Bundle();
            arguments.putString(K_FlyerDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(K_FlyerDetailFragment.ARG_ITEM_ID));
            arguments.putString(K_FlyerDetailFragment.ARG_FLYER_PATH,
                    getIntent().getStringExtra(K_FlyerDetailFragment.ARG_FLYER_PATH));
            K_FlyerDetailFragment fragment = new K_FlyerDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.flyer_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, K_FlyerListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}   // *- End of file -*
