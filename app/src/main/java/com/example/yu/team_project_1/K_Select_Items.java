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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by CARLOS MENA... (c) Camesys.
 *
 * Class to select the items for the shopping list.
 */
public class K_Select_Items extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "K_Select_Items";

    ListView listView1;
    TextView textView2;
    Button addButton, goBack;
    ArrayList<String> itemsNames = new ArrayList<>();
    ArrayList<String> itemsIcons = new ArrayList<>();
    ArrayList<String> newList = new ArrayList<>();
    ItemsAdapter itemsAdapter;

    public SQLiteDatabase database;
    K_DatabaseCreator databaseHelper;
    Cursor cursor, cursor2;
    ContentValues cValues,cValues2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.k_items_select);
        setTitle(R.string.K_SelectItems);

        addButton = (Button) findViewById(R.id.K_AddButt);
        textView2 = (TextView) findViewById(R.id.K_It_Sel_TextView);
        listView1 = (ListView) findViewById(R.id.K_It_Sel_ListView);
        goBack = (Button) findViewById(R.id.K_BackButt);

        itemsAdapter = new ItemsAdapter(this);
        listView1.setAdapter(itemsAdapter);

        databaseHelper = new K_DatabaseCreator(K_Select_Items.this);

        database = databaseHelper.getWritableDatabase();    // Open or Create Database

        cursor = database.rawQuery("SELECT * FROM Items", null);
        cValues = new ContentValues();
        cursor2 = database.rawQuery("SELECT * FROM Cart", null);
        cValues2 = new ContentValues();

        cursor.isFirst();
        while (cursor.moveToNext()) {
            String myItems = cursor.getString(cursor.getColumnIndex(databaseHelper.KEY_MESSAGE));
            String myItems2 = cursor.getString(cursor.getColumnIndex(databaseHelper.KEY_MESSAGE2));
            itemsNames.add(myItems);
            itemsIcons.add(myItems2);
        }
        if (cursor2.getCount() == 0) {
            Log.i(ACTIVITY_NAME, "List is empty, ready to select items");
        } else {
            cursor2.isFirst();
            while (cursor2.moveToNext()) {
                String myItems = cursor2.getString(cursor2.getColumnIndex(databaseHelper.KEY_CART1));
                newList.add(myItems);
            }
        }

        Log.i(ACTIVITY_NAME, "Cursor's row count = " + cursor.getCount());

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((textView2.getText().toString()).isEmpty()) {
                    Log.i(ACTIVITY_NAME, "User enter a empty space, ignored...");
                } else {
                    String msg = textView2.getText().toString();
                    itemsNames.add(msg);
                    itemsIcons.add("k_empty");
                    cValues.put(databaseHelper.KEY_MESSAGE, msg);
                    cValues.put(databaseHelper.KEY_MESSAGE2, "k_empty");

                    database.insert("Items", null, cValues);

                    Log.i(ACTIVITY_NAME, "Item: " + msg + " -> Added to database");
                    Toast newIt = Toast.makeText(K_Select_Items.this,
                            getString(R.string.K_Toast5), Toast.LENGTH_SHORT);
                    newIt.show();

                    itemsAdapter.notifyDataSetChanged();
                    textView2.setText("");
                }
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {    finish();   }
        });
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
//        ArrayList<String> newList = new ArrayList<>();

        public ItemsAdapter (Context ctx) {
            super();
            this.context= ctx;
        }

        @Override
        public int getCount(){  return itemsNames.size();    }

        @Override
        public String getItem(int position) {   return itemsNames.get(position); }

        public String getIcon(int position) {   return itemsIcons.get(position);    }

        @Override
        public long getItemId(int position) {   return 0;   }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = K_Select_Items.this.getLayoutInflater();

            View result = null;
            result = inflater.inflate(R.layout.k_items_line, null);

            final TextView message = (TextView) result.findViewById(R.id.K_It_Name);
            final ImageView icon = (ImageView) result.findViewById(R.id.imageView);

            final String messageText = getItem(position);
            message.setText(messageText);
            final String nameIcon = getIcon(position);
            int newIcon = getResources().getIdentifier(nameIcon, "drawable", getPackageName());
            icon.setImageResource(newIcon);

            result.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String addName = getItem(position);
                    final String addIcon = getIcon(position);
                    message.setEnabled(false);
                    icon.setEnabled(false);

                    if (!newList.contains(addName)) {   // avoiding duplicate items in new list
                        newList.add(addName);
                        cValues2.put(databaseHelper.KEY_CART1, addName);
                        cValues2.put(databaseHelper.KEY_CART2, addIcon);

                        database.insert("Cart", null, cValues2);
                        Log.i(ACTIVITY_NAME,addName + " added to shopping list");
                        Toast newIt = Toast.makeText(K_Select_Items.this,
                                getString(R.string.K_Toast2), Toast.LENGTH_SHORT);
                        newIt.show();

                    } else {    Log.i(ACTIVITY_NAME,addName + " is already on shopping list");
                        Toast newNo = Toast.makeText(K_Select_Items.this,
                                getString(R.string.K_Toast6), Toast.LENGTH_SHORT);
                        newNo.show();
                    }
                }
            });
            return result;
        }
    } // * End of inner class

} // *-* End of file *-*