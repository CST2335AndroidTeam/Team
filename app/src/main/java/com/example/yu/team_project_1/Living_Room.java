package com.example.yu.team_project_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class Living_Room extends AppCompatActivity {

        private static String SETTING_MESSAGE = " Living Room Menu";

    String[] livingRoomList = {"Lighting 1","Lighting 2", "Lighting 3","Television","Shades"};
    protected ArrayList<String> listItemTitles = new ArrayList<>(Arrays.asList(livingRoomList));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Living Room Menu", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ListView menuLists = (ListView)findViewById(R.id.living_room_menu);
        LR_Adapter adapter = new LR_Adapter(this);
        menuLists.setAdapter(adapter);

        menuLists.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l){
                String items =(String)adapterView.getItemAtPosition(i);
                Toast.makeText(view.getContext(),"Welcome to "+ items +"!",Toast.LENGTH_LONG).show();

                if(i == 0){
                    Intent intentLighting1 = new Intent(Living_Room.this,Lighting1.class);
                    startActivityForResult(intentLighting1,2);
                }
               if(i == 1){

                    Intent intentLighting2 = new Intent(Living_Room.this,Lighting2.class);
                    startActivityForResult(intentLighting2,2);
                }
                 if(i == 2){
                    Intent intentLighting3= new Intent(Living_Room.this, Lighting3.class);
                    startActivityForResult(intentLighting3,2);
                }
               if(i== 3){
                    Intent intentTelevision= new Intent(Living_Room.this,Television.class);
                    startActivity(intentTelevision);
                }
                if(i== 4){
                    Intent intentShades= new Intent(Living_Room.this, Shades.class);
                    startActivity(intentShades);
                }

            }
        });
    }



    private class LR_Adapter extends ArrayAdapter<String> {
        LR_Adapter(Context ctx) {
            super(ctx, 0);
        }

        @Override
        public int getCount() {
            return listItemTitles.size();
        }

        @Override
        public String getItem(int position) {
            return listItemTitles.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = Living_Room.this.getLayoutInflater();
            View result = inflater.inflate(R.layout.list_item, null);

            int rowId = 0;
            switch (position) {
                case 0:
                case 1:
                case 2:
                    rowId = R.drawable.lighting;
                    break;
                case 3:
                    rowId = R.drawable.television;
                    break;
                case 4:
                    rowId = R.drawable.shades;
                    break;

            }
            ImageView imageView = (ImageView) result.findViewById(R.id.list_item_image);
            imageView.setImageResource(rowId);

            TextView textView = (TextView) result.findViewById(R.id.list_item_text);
            textView.setText(getItem(position));


            return result;
        }
    }
}
