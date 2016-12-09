package com.example.yu.team_project_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/** A database helper that create and manipulate the Radio station table
 *
 * @author Mochen Jin
 */

public class Auto_DatabaseHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "Chats.db";
    private static int VERSION_NUM = 1;
    static final String RADIO_TABLE ="RadioTable";
    static final String RADIO_KEY = "radio";



    public Auto_DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("Auto_DatabaseHelper", "Calling onCreate");
        db.execSQL("CREATE TABLE " + RADIO_TABLE + " ( " + RADIO_KEY + " TEXT PRIMARY KEY );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("Auto_DatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVersion + " newVersion=" + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + RADIO_TABLE);
        onCreate(db);
    }
}
