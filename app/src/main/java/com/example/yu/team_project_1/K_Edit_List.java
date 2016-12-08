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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 *
 * Created by CARLOS MENA... (c) Camesys.
 *
 * Class for editing the shopping list.
 */
public class K_Edit_List extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "K_Edit_List";

    ListView listView1;
    TextView textView2;
    Button addButton, doneButton;
    CheckBox checkBox;
    ArrayList<String> itemsNames = new ArrayList<>();
    ArrayList<String> itemsIcons = new ArrayList<>();
    ArrayList<String> itemsSelected = new ArrayList<>();
    ArrayList<String> itemsSelIcons = new ArrayList<>();
    K_Edit_List.ItemsAdapter itemsAdapter;

    public SQLiteDatabase database;
    K_DatabaseCreator databaseHelper;
    Cursor cursor, cursor2;
    ContentValues cValues, cValues2;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.k_list_edit);
        setTitle(R.string.K_EditItems);

        doneButton = (Button) findViewById(R.id.K_doneButt);
        addButton = (Button) findViewById(R.id.K_AddButt);
        textView2 = (TextView) findViewById(R.id.K_It_Sel_TextView);
        listView1 = (ListView) findViewById(R.id.K_It_Sel_ListView);
        checkBox = (CheckBox) findViewById(R.id.K_Checkbox);

        itemsAdapter = new K_Edit_List.ItemsAdapter(this);
        listView1.setAdapter(itemsAdapter);

        databaseHelper = new K_DatabaseCreator(K_Edit_List.this);

        database = databaseHelper.getWritableDatabase();

        cursor = database.rawQuery("SELECT * FROM Items", null);
        cursor2 = database.rawQuery("SELECT * FROM Cart", null);
        cValues = new ContentValues();
        cValues2 = new ContentValues();

        cursor.isFirst();
        while (cursor.moveToNext()) {
            String myItems = cursor.getString(cursor.getColumnIndex(databaseHelper.KEY_MESSAGE));
            String myItems2 = cursor.getString(cursor.getColumnIndex(databaseHelper.KEY_MESSAGE2));
            itemsNames.add(myItems);
            itemsIcons.add(myItems2);
        }

        if (cursor2.getCount() == 0) {
            Log.i(ACTIVITY_NAME, "List is empty");
            itemsSelected.add("Placeholder");       // To prevent null array
            itemsSelIcons.add("Placeholder");
        } else {
            cursor2.isFirst();
            while (cursor2.moveToNext()) {
                String myItems = cursor2.getString(cursor2.getColumnIndex(databaseHelper.KEY_CART1));
                String myItems2 = cursor2.getString(cursor2.getColumnIndex(databaseHelper.KEY_CART2));
                itemsSelected.add(myItems);
                itemsSelIcons.add(myItems2);
            }
        }

        Log.i(ACTIVITY_NAME, "Items in database = " + cursor.getCount());
        Log.i(ACTIVITY_NAME, "Items in shopping list = " + cursor2.getCount());

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
                    Toast newIt = Toast.makeText(K_Edit_List.this,
                            getString(R.string.K_Toast1), Toast.LENGTH_LONG);
                    newIt.show();

                    itemsAdapter.notifyDataSetChanged();
                    textView2.setText("");
                }
            }
        });

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
        public int getCount(){  return itemsNames.size();    }

        @Override
        public String getItem(int position) {   return itemsNames.get(position); }


        public String getIcon(int position) {   return itemsIcons.get(position);    }

        @Override
        public long getItemId(int position) {   return 0;   }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = K_Edit_List.this.getLayoutInflater();

            View result = null;
            result = inflater.inflate(R.layout.k_list_edit_line, null);

            final CheckBox select = (CheckBox) result.findViewById(R.id.K_Checkbox);
            final TextView message = (TextView) result.findViewById(R.id.K_It_Name);
            final ImageView icon = (ImageView) result.findViewById(R.id.imageView);

            final String messageText = getItem(position);
            message.setText(messageText);
            final String nameIcon = getIcon(position);
            int newIcon = getResources().getIdentifier(nameIcon, "drawable", getPackageName());
            icon.setImageResource(newIcon);

            if (itemsSelected.contains(messageText)) { select.setChecked(true); }

            select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if ( ((CheckBox) v).isChecked()) {
                        final String addName = getItem(position);
                        final String addIcon = getIcon(position);
                        message.setEnabled(false);
                        icon.setEnabled(false);

                        if (!itemsSelected.contains(addName)) {   // avoiding duplicate items in new list
                            itemsSelected.add(addName);
                            cValues2.put(databaseHelper.KEY_CART1, addName);
                            cValues2.put(databaseHelper.KEY_CART2, addIcon);

                            database.insert("Cart", null, cValues2);
                            Log.i(ACTIVITY_NAME, addName + " added to shopping list");
                            Toast newIt = Toast.makeText(K_Edit_List.this,
                                    getString(R.string.K_Toast2), Toast.LENGTH_SHORT);
                            newIt.show();
                        }

                    } else {
                            final String delName = getItem(position);
                            final String delIcon = getIcon(position);
                            message.setEnabled(true);
                            icon.setEnabled(true);

                            if (itemsSelected.contains(delName)) {
                                itemsSelected.remove(delName);
                                cValues2.put(databaseHelper.KEY_CART1, delName);
                                cValues2.put(databaseHelper.KEY_CART2, delIcon);

                                database.delete("Cart", databaseHelper.KEY_CART1
                                        + "= ?", new String[]{delName});
                                Log.i(ACTIVITY_NAME, delName + " removed from shopping list");
                                Toast remList = Toast.makeText(K_Edit_List.this,
                                        getString(R.string.K_Toast3), Toast.LENGTH_SHORT);
                                remList.show();
                            }
                    }
                }});
            return result;
        }
    } // * End of inner class

} // *-* End of file *-*
