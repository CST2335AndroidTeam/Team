package com.example.yu.team_project_1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

/**
 * Created by CARLOS MENA... (c) Camesys.
 *
 * Class to create a new shopping list.
 */
public class K_New_List extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "K_New_List";
    Button selectItems;
    Button downloadFlyers;
    Button goback;
    public SQLiteDatabase database;
    K_DatabaseCreator databaseHelper;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.k_new_list);
        setTitle(R.string.carlos_app_name);

        deleteList();   // Deletes the previous shopping list

        selectItems = (Button) findViewById(R.id.K_New_B1);
        downloadFlyers = (Button) findViewById(R.id.K_New_B2);
        goback = (Button) findViewById(R.id.K_New_B3);

        selectItems.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(K_New_List.this, K_Select_Items.class);
                startActivityForResult(intent, 5);
            }
        });

        downloadFlyers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(K_New_List.this, K_Download_Flyers.class);
                startActivityForResult(intent, 5);
            }
        });

        goback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

    /* Deletes all the information from the current list
    *  and the files for the flyers */
    public void deleteList() {

        databaseHelper = new K_DatabaseCreator(K_New_List.this);
        database = databaseHelper.getWritableDatabase();
        database.execSQL("DROP TABLE IF EXISTS Cart;");
        database.execSQL("CREATE TABLE Cart (Id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "Name TEXT, Icon TEXT);");
        database.close();

        String[] flyers = { "200x200_crop-north-force.jpg",
                            "347w7dy.jpg",
                            "Food_basics_flyer_august02.jpg",
                            "independent-grocer-atlantic-flyer-november-24-to-301.jpg",
                            "Metro.jpg",
                            "No-Frills-1.jpg"};

        Log.i(ACTIVITY_NAME, "Flyers being delete if exist...");
        File dir = getFilesDir();
        for (int i = 0; i < flyers.length; i++) {
            try {
                File file = new File(dir, flyers[i]);
                boolean deleted = file.delete();
            } catch (Exception e) {}
        }
    }

} // *-* End of file *-*