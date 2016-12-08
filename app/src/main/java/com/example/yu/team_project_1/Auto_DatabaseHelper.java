package com.example.yu.team_project_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by james on 12/8/2016.
 */

public class Auto_DatabaseHelper extends SQLiteOpenHelper {
    protected final static String DATABASE_NAME = "Chats.db";
    protected static int VERSION_NUM = 1;
    public static final String MESSAGE_TABLE ="ChatMessage";
    public static final String KEY_ID = "id";
    public static final String KEY_MESSAGE = "message";



    public Auto_DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("Auto_DatabaseHelper", "Calling onCreate");

        db.execSQL("CREATE TABLE " + MESSAGE_TABLE + " ( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_MESSAGE + " TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVersion + " newVersion=" + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + MESSAGE_TABLE);
        onCreate(db);
    }
}
