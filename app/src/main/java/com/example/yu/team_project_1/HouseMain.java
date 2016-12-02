package com.example.yu.team_project_1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DrawableUtils;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class HouseMain extends AppCompatActivity {
    private static String INFORMATION = "Information";
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private ListView navListView;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;


    String[] houseSettingMenu= {"Garage","House Temperature","Weather"};
    protected ArrayList<String> menuItems = new ArrayList<>(Arrays.asList(houseSettingMenu));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_main);

        drawerLayout = (DrawerLayout)findViewById(R.id.houseMain_drawer);

        navListView = (ListView)findViewById(R.id.navList);
        HouseAdapter adapter = new HouseAdapter(this);
        //only allow click one list item
        navListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        fragmentManager =getSupportFragmentManager();


        navListView.setAdapter(adapter);
        navListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        HouseMainFragment1 garageFragment = new HouseMainFragment1();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentHolder,garageFragment);
                        fragmentTransaction.commit();
                        break;
                    case 1:
                        HouseMainFragment2 tempFragment = new HouseMainFragment2();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentHolder,tempFragment);
                        fragmentTransaction.commit();
                        break;
                    case 2:
                        HouseMainFragment3 weatherFragment = new HouseMainFragment3();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentHolder,weatherFragment);
                        fragmentTransaction.commit();
                        break;
                }
                drawerLayout.closeDrawer(navListView);
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.openDrawer,R.string.closeDrawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


    }


    private class HouseAdapter extends ArrayAdapter<String> {
        HouseAdapter(Context ctx) {
            super(ctx, 0);
        }

        @Override
        public int getCount() {
            return menuItems.size();
        }

        @Override
        public String getItem(int position) {
            return menuItems.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = HouseMain.this.getLayoutInflater();
            View result = inflater.inflate(R.layout.menu_list, null);

            int rowId = 0;
            switch (position) {
                case 0:
                    rowId = R.drawable.garage2;
                    break;
                case 1:
                    rowId = R.drawable.temp2;
                    break;
                case 2:
                    rowId = R.drawable.weather2;
                    break;

            }
            ImageView imageView = (ImageView) result.findViewById(R.id.house_menu_image);
            imageView.setImageResource(rowId);

            TextView textView = (TextView) result.findViewById(R.id.house_menu_text);
            textView.setText(getItem(position));


            return result;
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    @Override
    public boolean onCreateOptionsMenu (Menu m){
        getMenuInflater().inflate(R.menu.toolbar,m);
        return true;
    }

    @Override
    //responds to one of the items being selected
    public boolean onOptionsItemSelected(MenuItem mi){
        int id = mi.getItemId();
        switch(id){
            case android.R.id.home:
                 if(drawerLayout.isDrawerOpen(navListView)){
                     drawerLayout.closeDrawer(navListView);
                 }else{
                     drawerLayout.openDrawer(navListView);
                 }
                 break;
            case R.id.item1:

                Intent intentGarage = new Intent(HouseMain.this,Garage.class);
                startActivityForResult(intentGarage,2);

                break;
            case R.id.item2:
                Intent intentTemp = new Intent(HouseMain.this,HouseTemp.class);
                startActivityForResult(intentTemp,1);

                break;
            case R.id.item3:

                Intent intentWeather= new Intent(HouseMain.this, Weather.class);
                startActivity(intentWeather);

                break;
            case R.id.item4:
                //TODO: Setting device vibrate

                break;
            case R.id.item5:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(INFORMATION);
                // Add the buttons
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                    }
                });
                builder.setMessage("Author: Yu Wang\nVersion: 2.2.2\nInstruction: House Setting interface includes the list of garage, house temperature, and weather;" +
                        "\nIn the garage control page, you are allowed to open/close the garage door and turn on/off the light." +
                        "Also, opening the garage door will turn on the light automatically." +
                        "\nIn house temperature control page, you are allowed to change house temperature and this temperature is stored till next time open the app;" +
                        "Also, customer is allowed to create a schedule by adding time/temperature settings when tab the calender icon." +
                        "\nIn weather control page, it will display the current outside temperature.");

                // Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }
        return true;
    }

}
