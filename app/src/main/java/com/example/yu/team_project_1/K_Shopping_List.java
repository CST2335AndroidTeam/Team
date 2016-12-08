package com.example.yu.team_project_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by CARLOS MENA... (c) Camesys.
 *
 * Class to display the final shopping list created by the user.
 */
public class K_Shopping_List extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "K_Shopping_List";

    ListView listView1;
    Button doneButton;
    CheckBox checkBox;
    ArrayList<String> itemsBackup = new ArrayList<>();
    ArrayList<String> iconsBackup = new ArrayList<>();
    ArrayList<String> itemsSelected = new ArrayList<>();
    ArrayList<String> itemsSelIcons = new ArrayList<>();
    K_Shopping_List.ItemsAdapter itemsAdapter;

    public SQLiteDatabase database;
    K_DatabaseCreator databaseHelper;
    Cursor cursor;
    ContentValues cValues;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.k_list_shopping);
        setTitle(R.string.K_Go_Shopping);

        doneButton = (Button) findViewById(R.id.K_doneButt);
        listView1 = (ListView) findViewById(R.id.K_It_Sel_ListView);
        checkBox = (CheckBox) findViewById(R.id.K_Checkbox);

        itemsAdapter = new K_Shopping_List.ItemsAdapter(this);
        listView1.setAdapter(itemsAdapter);

        databaseHelper = new K_DatabaseCreator(K_Shopping_List.this);

        database = databaseHelper.getWritableDatabase();

        cursor = database.rawQuery("SELECT * FROM Cart", null);
        cValues = new ContentValues();

        if (cursor.getCount() == 0) {
            Log.i(ACTIVITY_NAME, "List is empty");
            Toast emptyList = Toast.makeText(K_Shopping_List.this,
                    getString(R.string.K_Toast7), Toast.LENGTH_SHORT);
            emptyList.show();
            finish();
        } else {
            cursor.isFirst();
            while (cursor.moveToNext()) {
                String myItems = cursor.getString(cursor.getColumnIndex(databaseHelper.KEY_CART1));
                String myItems2 = cursor.getString(cursor.getColumnIndex(databaseHelper.KEY_CART2));
                itemsSelected.add(myItems);
                itemsSelIcons.add(myItems2);
                itemsBackup.add(myItems);   // Backup for the array list to retrieve all
                iconsBackup.add(myItems2);  // the items if user select them again
            }
        }

        Log.i(ACTIVITY_NAME, "Items in shopping list = " + cursor.getCount());

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {    finish();   }
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
        database.close();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

    /**
     *  Inner Class to inflate the view of the shoppings list items
     */
    private class ItemsAdapter extends BaseAdapter {

        Context context;

        public ItemsAdapter (Context ctx) {
            super();
            this.context= ctx;
        }

        @Override
        public int getCount(){  return itemsBackup.size();    }

        @Override
        public String getItem(int position) {   return itemsBackup.get(position); }


        public String getIcon(int position) {   return iconsBackup.get(position);    }

        @Override
        public long getItemId(int position) {   return 0;   }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = K_Shopping_List.this.getLayoutInflater();

            View result = null;
            result = inflater.inflate(R.layout.k_list_shop_line, null);

            final CheckBox select = (CheckBox) result.findViewById(R.id.K_Checkbox);
            final TextView message = (TextView) result.findViewById(R.id.K_It_Name);
            final ImageView icon = (ImageView) result.findViewById(R.id.imageView);
            final ImageView icon2 = (ImageView) result.findViewById(R.id.K_Im_Checkbox);

            final String messageText = getItem(position);
            message.setText(messageText);
            final String nameIcon = getIcon(position);
            int newIcon = getResources().getIdentifier(nameIcon, "drawable", getPackageName());
            icon.setImageResource(newIcon);
            icon2.setVisibility(View.INVISIBLE);
            select.setChecked(false);

            select.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick (View v) {

                    if (((CheckBox) v).isChecked()) {
                        final String delName = getItem(position);
                        final String delIcon = getIcon(position);
                        message.setEnabled(false);
                        icon.setEnabled(false);
                        icon2.setVisibility(View.VISIBLE);

                        if (itemsSelected.contains(delName)) {
                            itemsSelected.remove(delName);
                            cValues.put(databaseHelper.KEY_CART1, delName);
                            cValues.put(databaseHelper.KEY_CART2, delIcon);

                            database.delete("Cart", databaseHelper.KEY_CART1
                                    + "= ?", new String[]{delName});
                            Log.i(ACTIVITY_NAME, delName + " marked as bought on shopping list");
                        }
                    } else {
                        final String addName = getItem(position);
                        final String addIcon = getIcon(position);
                        message.setEnabled(true);
                        icon.setEnabled(true);
                        icon2.setVisibility(View.INVISIBLE);

                        if (!itemsSelected.contains(addName)) {   // avoiding duplicate items
                            itemsSelected.add(addName);
                            cValues.put(databaseHelper.KEY_CART1, addName);
                            cValues.put(databaseHelper.KEY_CART2, addIcon);

                            database.insert("Cart", null, cValues);
                            Log.i(ACTIVITY_NAME, addName + " re-enter to shopping list");
                        }
                    }
                }});
            return result;
        }
    } // * End of inner class

}   // *- End of file -*