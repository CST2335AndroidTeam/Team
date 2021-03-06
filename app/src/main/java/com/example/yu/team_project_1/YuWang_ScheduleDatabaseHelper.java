package com.example.yu.team_project_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class creates a database that will use to help storing information from user
 *
 * @author  Yu Wang  2016.12.05
 * @version 2.2.2
 */
public class YuWang_ScheduleDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "schedule.bd";
    public static final int VERSION_NUM = 5;

    public static final String TABLE_NAME = "schedule_table";
    public static final String KEY_ID = "_id";
    public static final String KEY_MESSAGE = "information";


    private static final String DATABASE_CREATE = "create table " + TABLE_NAME + " ( "
            + KEY_ID + " integer primary key autoincrement, "
            + KEY_MESSAGE + " text not null);";

    public YuWang_ScheduleDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // .execSQL-->executes a string SQL statement.
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(YuWang_ScheduleDatabaseHelper.class.getName(),"Upgrading database from version " + oldVersion
                + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
