package com.example.yu.team_project_1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.yu.team_project_1.Auto_DriveFragment.hideKeyboard;

/**Main class for Automobile section
 * has 2 panel display for screen size larger than 600dp (width),
 * different layout for tablet portrait and landscape mode.
 * Contains a ListView of 6 settings:
 *      GPS Navigation will pop up dialog and then go to Google navigation intent
 *      the other 5 will launch corresponding setting Activity or Fragment (depend on screen size)
 *
 * Support Chinese interface for most of the pages and functions
 * an About menu on top right corner, showing instructions.
 *
 *  @author Mochen Jin
 */
public class Auto_MainActivity extends AppCompatActivity {
    private ListView listview;
    private EditText addressBox;

    final static int TEMPERATURE = 0;
    final static int RADIO = 1;
    final static int GPS = 2;
    final static int LIGHT = 3;
    final static int ODOMETER = 4;
    final static int DRIVE = 5;

    //An array setting names.
    String[] settingItemsArr;

    protected boolean isTablet;
    protected ArrayList<String> settingItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile_main);

        settingItemsArr  = new String[] {
                getString(R.string.temperature),
                getString(R.string.radio),
                getString(R.string.gps_direction),
                getString(R.string.car_light),
                getString(R.string.odometer),
                getString(R.string.drive)
        };
        settingItems = new ArrayList<>(Arrays.asList(settingItemsArr));

        isTablet = (findViewById(R.id.auto_setting_container) != null);



        listview = (ListView)findViewById(R.id.automobile_list);
        MyAdapter adapter = new MyAdapter(this);
        listview.setAdapter(adapter);



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case TEMPERATURE:
                        if(isTablet) {
                            final Auto_TempFragment tempFragment = new Auto_TempFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.auto_setting_container, tempFragment).commit();
                        }else {
                            startActivity(new Intent(Auto_MainActivity.this, Auto_TempActivity.class));
                        }
                        break;
                    case RADIO:
                        if(isTablet) {
                            final Auto_RadioFragment radioFragment = new Auto_RadioFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.auto_setting_container, radioFragment).commit();
                        }else {
                            startActivity(new Intent(Auto_MainActivity.this, Auto_RadioActivity.class));
                        }
                        break;
                    case GPS:
                        goToGPS();
                        break;
                    case LIGHT:
                        if(isTablet) {
                            final Auto_LightFragment lightFragment = new Auto_LightFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.auto_setting_container, lightFragment).commit();
                        }else {
                            startActivity(new Intent(Auto_MainActivity.this, Auto_LightActivity.class));
                        }
                        break;
                    case ODOMETER:
                        if(isTablet) {
                            final Auto_OdometerFragment odoFragment = new Auto_OdometerFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.auto_setting_container, odoFragment).commit();
                        }else {
                            startActivity(new Intent(Auto_MainActivity.this, Auto_OdometerActivity.class));
                        }
                        break;
                    case DRIVE:
                        if(isTablet) {
                            final Auto_DriveFragment driveFragment = new Auto_DriveFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.auto_setting_container, driveFragment).commit();
                        }else {
                            startActivity(new Intent(Auto_MainActivity.this, Auto_DriveDetailActivity.class));
                        }
                        break;
                }
            }
        });
    }

    private void goToGPS(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dlg = inflater.inflate(R.layout.auto_navigation_dialog, null);
        addressBox =(EditText) dlg.findViewById(R.id.auto_dialog_address) ;
        builder.setView(dlg);
        builder.setMessage(R.string.navigation_dialog_title);
        builder.setPositiveButton(R.string.auto_navigation_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String address = addressBox.getText().toString();
                        hideKeyboard(Auto_MainActivity.this);
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                Uri.parse("google.navigation:q=" +address ));
                        startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.auto_menubar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.auto_instruction){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("About")
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                    .setIcon(R.drawable.main_car)
                    .setMessage("AUTOMOBILE SECTION\nAuthor: Mochen Jin\n\n o\tTemperature settings – allow the user to set the temperature in the front, and back of the car (2 zones)\n" +
                    "o\tRadio controls – include preset radio stations (6) that can be configured by the user.\n" +
                    "o\tGPS directions – This should launch the google navigation Intent\n" +
                    "o\tLights – There should be a setting for turning on the headlights (normal, high), as well as a dimmable light inside the car.\n" +
                    "o\tOdometer showing how far the car has driven, and a trip distance counter that can be reset to 0.\n" +
                    "o\tDrive – An “On/Off” button which queries the user how far to drive. The user enters the number of kilometers for this trip, which should update the Odometer, and fuel level based on the fuel economy calculations.  There should also be a “change oil” light that comes on every 6000 km.\n.")
                    .show();
        }

        return true;
    }

    private class MyAdapter extends ArrayAdapter<String> {
        MyAdapter(Context ctx){
            super(ctx,0);
        }

        @Override
        public int getCount() {
            return settingItems.size();
        }

        @Override
        public String getItem(int position) {
            return settingItems.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = Auto_MainActivity.this.getLayoutInflater();
            View result = inflater.inflate(R.layout.auto_setting_row, null);

            int imageid = 0;
            switch(position){
                case TEMPERATURE:
                    imageid = R.drawable.temperature;
                    break;
                case RADIO:
                    imageid = R.drawable.radio;
                    break;
                case GPS:
                    imageid = R.drawable.map;
                    break;
                case LIGHT:
                    imageid = R.drawable.light;
                    break;
                case ODOMETER:
                    imageid = R.drawable.odometer;
                    break;
                case DRIVE:
                    imageid = R.drawable.drive;
                    break;

            }
            ImageView imageView = (ImageView) result.findViewById(R.id.setting_image);
            imageView.setImageResource(imageid);

            TextView textView = (TextView) result.findViewById(R.id.setting_text);
            textView.setText(getItem(position));

            return result;
        }
    }
}
