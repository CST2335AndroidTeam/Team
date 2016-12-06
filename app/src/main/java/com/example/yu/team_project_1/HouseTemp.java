package com.example.yu.team_project_1;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;


/**
 * This class is the house temperature setting interface, it includes house current temperature,
 * schedule and help settings
 *
 * @author  Yu Wang  2016.12.05
 * @version 2.2.2
 */
public class HouseTemp extends AppCompatActivity{
    /**the text view of current temperature*/
    TextView tempCur;
    private static String FILENAME ="FileName";
    /**the data version of the database*/
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView picture;

    /**
     * Initialize activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_temp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Welcome to set temperature", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }


    /**
     * Inflates the menu from R.menu.toolbar from XML layout
     * @param m  the menu item that built from xml layout
     * @return  true
     */
    public boolean onCreateOptionsMenu (Menu m){
        getMenuInflater().inflate(R.menu.temp,m);
        return true;
    }

    /**Responds to one of the items being selected*/
    public boolean onOptionsItemSelected(MenuItem mi){
        tempCur = (TextView)findViewById(R.id.cur_temp);
        int id = mi.getItemId();
        switch(id){
            case R.id.t1:
                //Set tempereture dialog
                NumberPicker numberPicker = new NumberPicker(this);
                numberPicker.setMaxValue(40);
                numberPicker.setMinValue(0);
                numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        tempCur.setText((newVal + " \u2103"));
                        SharedPreferences pre = getSharedPreferences(FILENAME,Context.MODE_PRIVATE) ;
                        SharedPreferences.Editor editor = pre.edit();
                        editor.putString("Default",tempCur.getText().toString() );
                        editor.commit();

                    }
                });
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this).setView(numberPicker);

                builder2.setTitle("Set Temperature To")
                        .setIcon(R.drawable.pig)
                        .setPositiveButton(R.string.set, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK button
                                Intent resultIntent = new Intent(  );
                                setResult(Activity.RESULT_OK, resultIntent);
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                                dialog.cancel();
                            }
                        });

                builder2.create().show();

                break;

            case R.id.t2:

                Intent intentSchedule = new Intent(this,ScheduleTimeTemp.class);
                startActivity(intentSchedule);

                break;
            case R.id.t3:
                // invokes an intent to capture a photo
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
                break;

            case R.id.t4:

                Snackbar.make(findViewById(R.id.toolbar), "Enjoying using our app", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                break;
            case R.id.t5:

                Toast toast3 = Toast.makeText(HouseTemp.this , "Version 2.2.2 , by Yu Wang", Toast.LENGTH_SHORT);
                toast3.show();
                break;
        }
        return true;
    }

    @Override
    public void onStart(){
        super.onStart();
        // save data to the context, think about Scanner(System.in)
        SharedPreferences prs = getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        tempCur = (TextView)findViewById(R.id.cur_temp);
        String defaultTemp = prs.getString("Default", "20 \u2103");
        tempCur.setText(defaultTemp);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // replace the button's image with your new picture
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            picture = (ImageView)findViewById(R.id.imagePicture);
            picture.setImageBitmap(imageBitmap);

        }
    }


}
