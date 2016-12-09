package com.example.yu.team_project_1;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.yu.team_project_1.Auto_DatabaseHelper.RADIO_KEY;
import static com.example.yu.team_project_1.Auto_DatabaseHelper.RADIO_TABLE;

/**
 * code for shake sensor from "Morteza Soleimani" on StackOverflow:
 *  http://stackoverflow.com/questions/5271448/how-to-detect-shake-event-with-android
 */
public class Auto_RadioFragment extends Fragment {
    private ListView radioList;
    private ArrayList<String> stations =new ArrayList<>()  ;
    private Button newStationButton;
    private ProgressBar progressBar;
    private TextView message;

    private Auto_DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private MyRadioAdapter adapter;

    //for shake
    private SensorManager mSensorManager;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity

    public Auto_RadioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.auto_fragment_radio, container, false);

        dbHelper = new Auto_DatabaseHelper(getActivity());
        db = dbHelper.getWritableDatabase();

        message = (TextView)view.findViewById(R.id.auto_progress_message);
        progressBar = (ProgressBar) view.findViewById(R.id.auto_radio_progressbar);

        newStationButton = (Button) view.findViewById(R.id.auto_new_station);
        newStationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final EditText input = new EditText(getActivity());
                input.setText("FM");
                builder.setTitle("Add New Radio Station")
                        .setMessage("please enter new station")
                        .setView(input)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(addNewStation(input.getText().toString())){
                                    Toast.makeText(getActivity(),"New Radio Station added", Toast.LENGTH_SHORT).show();
                                    loadRadios();
                                }else{
                                    Toast.makeText(getActivity(),"Database Error, can't add station", Toast.LENGTH_SHORT).show();
                                };
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
            }
        });

        radioList= (ListView) view.findViewById(R.id.auto_radio_listview);
        adapter = new MyRadioAdapter(getActivity());
        radioList.setAdapter(adapter);


        new LoadDatabase().execute();
        return view;
    }


    private boolean addNewStation(String name){
        ContentValues cv = new ContentValues();
        cv.put(RADIO_KEY,name);
        long returnCode =  db.insert(RADIO_TABLE, "place holder",cv);
        return returnCode != -1;
    }

    private boolean updateStation(String oldRadio, String newRadio){
        ContentValues cv = new ContentValues();
        cv.put(RADIO_KEY, newRadio);
        return db.update(RADIO_TABLE, cv, RADIO_KEY+ " = '"+ oldRadio + "'",null) == 1;
    }

    private boolean deleteStation(String radio){
        Log.i("jamesdebug",radio);
       return  db.delete(RADIO_TABLE, RADIO_KEY + " = '" + radio + "'", null) == 1;
    }

    private void loadRadios(){

        stations= new ArrayList<>();

        Cursor cursor= db.rawQuery("SELECT * FROM " + RADIO_TABLE,null);
        int index = cursor.getColumnIndex(Auto_DatabaseHelper.RADIO_KEY);
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String station = cursor.getString(index);
                Log.i("jamesdebug", "SQL MESSAGE:" + station);
                stations.add(station);
                cursor.moveToNext();
            }
        }
        adapter.notifyDataSetChanged();

    }


    private class MyRadioAdapter extends ArrayAdapter<String> {
        MyRadioAdapter(Context ctx){
            super(ctx,0);
        }

        @Override
        public int getCount() {
            return stations.size();
        }

        @Override
        public String getItem(int position) {
            return stations.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View result = inflater.inflate(R.layout.auto_radio_list_row, null);

            TextView name = (TextView)result.findViewById(R.id.station_name);
            ImageView editImage = (ImageView) result.findViewById(R.id.auto_edit_image);
            ImageView deleteImage= (ImageView) result.findViewById(R.id.auto_delete_image);

            final int positionPass = position;
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Now playing: " + getItem(positionPass), Toast.LENGTH_LONG).show();
                }
            });

            editImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final EditText input = new EditText(getActivity());
                    input.setText(getItem(positionPass));
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Edit New Radio Station")
                            .setMessage("please enter new station name")
                            .setView(input)
                            .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    updateStation(getItem(positionPass), input.getText().toString());
                                    loadRadios();                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                }
            });

            deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("jamesdebug",deleteStation(getItem(positionPass)) + "");
                    loadRadios();
                }
            });


            name.setText(getItem(position));

            return result;
        }
    }

    private class LoadDatabase extends AsyncTask<String, Integer, Integer>{

        @Override
        protected Integer doInBackground(String... params) {
            stations= new ArrayList<>();

            Cursor cursor= db.rawQuery("SELECT * FROM " + RADIO_TABLE,null);
            int index = cursor.getColumnIndex(Auto_DatabaseHelper.RADIO_KEY);
            int count = cursor.getCount();
            int current = 0;
            if(count>0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    String station = cursor.getString(index);
                    Log.i("jamesdebug", "SQL MESSAGE:" + station);
                    stations.add(station);
                    cursor.moveToNext();
                    publishProgress((int) (++current * 100.0 / count));
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

            return count;
        }


        @Override
        protected void onProgressUpdate(Integer... progress) {
            progressBar.setProgress(progress[0]);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            message.setText("Loading favorite radios");
        }


        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            message.setText("done!");
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * Sort the list when you shake it.
     */
    private final SensorEventListener mSensorListener = new SensorEventListener() {

        public void onSensorChanged(SensorEvent se) {
            float x = se.values[0];
            float y = se.values[1];
            float z = se.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta; // perform low-cut filter

            if (mAccel > 6) {
                Collections.sort(stations);
                adapter.notifyDataSetChanged();
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

}
