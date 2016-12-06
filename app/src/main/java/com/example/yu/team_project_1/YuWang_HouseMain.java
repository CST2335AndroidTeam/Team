package com.example.yu.team_project_1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is an activity of the main house setting interface, it includes top toolbar menu and
 * a drawer list of garage, house temperature and weather setting interface
 *
 * @author  Yu Wang  2016.12.05
 * @version 2.2.2
 */
public class YuWang_HouseMain extends AppCompatActivity {

    /**a dialog title of "about us" */
    private static String INFORMATION = "Information";
    /**a dialog title of "theme setting"*/
    private static String themeSetting="Set your theme";
    /**the drawer toggle from toolbar menu*/
    private ActionBarDrawerToggle actionBarDrawerToggle;
    /**the layout of drawer */
    private DrawerLayout drawerLayout;
    /**the list items of listView, includes garage, house temperature and weather*/
    private ListView navListView;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    /**the spinner of theme, which includes normal, console and navy */
    private Spinner themeSpinner;
    /**the adapter that adapt theme spinner */
    private ArrayAdapter<String> themeAdapter;


    /**list items of house setting menu*/
    String[] houseSettingMenu= {"YuWang_Garage","House Temperature","YuWang_Weather"};
    /**assign array list of string to the array list, it will match to list view items*/
    protected ArrayList<String> menuItems = new ArrayList<>(Arrays.asList(houseSettingMenu));

    /**
     * Initialize activity
     * @param savedInstanceState
     */
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
        //create a fragment when user click the each of item from list
        navListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        YuWang_HouseMainFragment1 garageFragment = new YuWang_HouseMainFragment1();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentHolder,garageFragment);
                        fragmentTransaction.commit();
                        break;
                    case 1:
                        YuWang_HouseMainFragment2 tempFragment = new YuWang_HouseMainFragment2();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentHolder,tempFragment);
                        fragmentTransaction.commit();
                        break;
                    case 2:
                        YuWang_HouseMainFragment3 weatherFragment = new YuWang_HouseMainFragment3();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentHolder,weatherFragment);
                        fragmentTransaction.commit();
                        break;
                }
                drawerLayout.closeDrawer(navListView);
            }
        });

        //there is a home icon on the left of toolbar, set this home icon to be dynamically
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.openDrawer,R.string.closeDrawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


    }

    /**
     * The private class of houseSetting, it provides a common algorithm to each row of the list
     */
    private class HouseAdapter extends ArrayAdapter<String> {
        /**
         * Conductor: conducts a new object of HouseAdapter object
         * @param ctx  the activity that holds the listview
         */
        HouseAdapter(Context ctx) {
            super(ctx, 0);
        }

        /**
         * Returns the number of items
         * @return  the number of items
         */
        @Override
        public int getCount() {
            return menuItems.size();
        }

        /**
         * Returns a specific item
         * @param position  the position of the item
         * @return a specific item
         */
        @Override
        public String getItem(int position) {
            return menuItems.get(position);
        }

        /**
         * Creates the layout for the specific row
         * @param position  the position of the item
         * @param convertView the convert view of the item
         * @param parent
         * @return view of the list item
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //set layout of the activity
            LayoutInflater inflater = YuWang_HouseMain.this.getLayoutInflater();
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

    /**
     * Called when activity start-up is complete (after onStart() and onRestoreInstanceState(Bundle) have been called)
     * @param savedInstanceState
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    /**
     * Inflates the menu from R.menu.toolbar from XML layout
     * @param m  the menu item that built from xml layout
     * @return  true
     */
    @Override
    public boolean onCreateOptionsMenu (Menu m){
        getMenuInflater().inflate(R.menu.toolbar,m);
        return true;
    }

    /**
     * Responds to one of the items being selected
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem mi){
        int id = mi.getItemId();
        switch(id){
            //home icon is selected
            case android.R.id.home:
                 if(drawerLayout.isDrawerOpen(navListView)){
                     drawerLayout.closeDrawer(navListView);
                 }else{
                     drawerLayout.openDrawer(navListView);
                 }
                 break;
            case R.id.item1:

                Intent intentGarage = new Intent(YuWang_HouseMain.this,YuWang_Garage.class);
                startActivityForResult(intentGarage,2);

                break;
            case R.id.item2:
                Intent intentTemp = new Intent(YuWang_HouseMain.this,YuWang_HouseTemp.class);
                startActivityForResult(intentTemp,1);

                break;
            case R.id.item3:

                Intent intentWeather= new Intent(YuWang_HouseMain.this, YuWang_Weather.class);
                startActivity(intentWeather);

                break;
            case R.id.item4:

                AlertDialog.Builder b = new AlertDialog.Builder(this);
                View dView = getLayoutInflater().inflate(R.layout.themedialog,null);
                b.setTitle(themeSetting);
                themeSpinner = (Spinner)dView.findViewById(R.id.themeSpinner);
                themeAdapter = new ArrayAdapter<>(YuWang_HouseMain.this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.themelist));
                themeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                themeSpinner.setAdapter(themeAdapter);
                b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(themeSpinner.getSelectedItem().toString().equals("Console")){
                            navListView.setBackgroundResource(R.drawable.concole);
                        }else if(themeSpinner.getSelectedItem().toString().equals("Navy")){
                            navListView.setBackgroundResource(R.drawable.navy);
                        }else if(themeSpinner.getSelectedItem().toString().equals("Normal")){
                            navListView.setBackgroundResource(R.drawable.normal);
                        }


                    }
                });
                b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                b.setView(dView);
                b.create().show();
                break;
            case R.id.item5:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(INFORMATION);
                // Add the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                    }
                });
                builder.setMessage("Author: Yu Wang\nVersion: 2.2.2\nInstruction: House Setting interface includes the list of garage, house temperature, and weather;" +
                        "\nIn the garage control page, you are allowed to open/close the garage door and turn on/off the light." +
                        " Also, opening the garage door will turn on the light automatically." +
                        "\nIn house temperature control page, you are allowed to change house temperature and this temperature is stored till next time open the app." +
                        " Also, customer is allowed to create a schedule by adding time/temperature settings when tab the calender icon." +
                        "\nIn weather control page, it will display the current outside temperature." +
                        "\nIn theme control page, it will display a dialog that ask user to choose the theme background.");

                // Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }
        return true;
    }

}
