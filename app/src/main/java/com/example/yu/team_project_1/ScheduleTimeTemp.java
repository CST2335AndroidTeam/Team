package com.example.yu.team_project_1;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;


import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * This class is the interface of schedule house temperature, it includes calender and clock settings
 *
 * @author  Yu Wang  2016.12.05
 * @version 2.2.2
 */
public class ScheduleTimeTemp extends AppCompatActivity implements View.OnClickListener{
    Button date;
    Button time;
    Button temp;
    Button confirm;

    EditText dateDisplay;
    EditText timeDisplay;
    EditText tempDisplay;

    EditText display;
    ListView confirmInforList;
    Switch onOff;

    /** the name of the Activity_name */
    public static final String ACTIVITY_NAME = "Query";
    public static final String COLUMN_COUNT = "Cursor\'s  column count= ";

    /** the SQL database */
    SQLiteDatabase db;
    Cursor cursor;
    final ArrayList<String> list = new ArrayList<String>();

    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_time_temp);

        date = (Button)findViewById(R.id.changeDate);
        time = (Button)findViewById(R.id.changeTime);
        temp = (Button)findViewById(R.id.changeTemp);
        confirm = (Button)findViewById(R.id.confirm);

        dateDisplay = (EditText)findViewById(R.id.displayDateText);
        timeDisplay = (EditText)findViewById(R.id.displayTimeText);
        tempDisplay = (EditText)findViewById(R.id.displayTempText);
        display = (EditText)findViewById(R.id.information) ;

        date.setOnClickListener(this);
        time.setOnClickListener(this);
        temp.setOnClickListener(this);

        confirmInforList = (ListView)findViewById(R.id.listOfSchedule);

        final ScheduleDatabaseHelper scheduleDatabaseHelper = new ScheduleDatabaseHelper(this);
        //set database to be readable
        db = scheduleDatabaseHelper.getWritableDatabase();
        String[] allColums = {ScheduleDatabaseHelper.KEY_ID,ScheduleDatabaseHelper.KEY_MESSAGE};

        //this query will return all the colums(id and message)
        cursor = db.query(scheduleDatabaseHelper.TABLE_NAME,allColums,null,null,null,null,null);

        Log.i(ACTIVITY_NAME,  COLUMN_COUNT+ cursor.getColumnCount() );
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            list.add(cursor.getString( cursor.getColumnIndex( scheduleDatabaseHelper.KEY_MESSAGE)));
            cursor.moveToNext();
        }

        // inner class: listView needs to have adapter to provide objects for each row of the list
        class ScheduleAdapter extends ArrayAdapter<String> {
            // ctx represents the current context, 0 is resource ID
            public ScheduleAdapter(Context ctx) {
                super(ctx, 0);
            }
            public int getCount(){
                return list.size();
            }
            public String getItem(int position){
                return list.get(position);
            }

            public View getView(int position, View convertView, ViewGroup parent){
                LayoutInflater inflater = ScheduleTimeTemp.this.getLayoutInflater();
                final View result = inflater.inflate(R.layout.schedulelist, null);
                final TextView scheduled = (TextView)result.findViewById(R.id.message_text);
                onOff = (Switch)result.findViewById(R.id.switchOn);
                //set listener to switch
                onOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        CharSequence text;
                        int duration;
                        if(isChecked){
                            text= "Switch is on";
                            duration = Toast.LENGTH_SHORT;
                        }else{
                            text = "Switch is Off";
                            duration = Toast.LENGTH_LONG;
                        }
                        Toast toast = Toast.makeText(ScheduleTimeTemp.this , text, duration);
                        toast.show(); //display your message box

                    }
                });

                scheduled.setText(   getItem(position)  ); // get the string at position
                return result;
            }
        }

        final ScheduleAdapter sa = new ScheduleAdapter(this);
        confirmInforList.setAdapter(sa);
        confirmInforList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        confirmInforList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
               Log.i("Position","is at "+ position);
                AlertDialog.Builder builder = new AlertDialog.Builder(ScheduleTimeTemp.this);
                        builder.setTitle("DELETE ??");
                        builder.setMessage("Are you sure you want to delete this schedule?");
                        // Add the buttons
                        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                //the schedule that user clicked is delete from database
                                db.execSQL("DELETE FROM "+scheduleDatabaseHelper.TABLE_NAME+" WHERE "+ ScheduleDatabaseHelper.KEY_MESSAGE +"= '"+String.valueOf(list.get(position))+"'");
                                Log.i("Position",String.valueOf(list.get(position)));
                                //remove the list items from listView
                                list.remove(position);
                                sa.notifyDataSetChanged();
                            }
                        });
                        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                                dialog.cancel();
                            }
                        });
                        // Create the AlertDialog
                        AlertDialog dialog = builder.create();
                        dialog.show();
            }
        });


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //add the schedule to the list
                list.add(display.getText().toString());

                //insert data to the database
                ContentValues cValues = new ContentValues();
                cValues.put(scheduleDatabaseHelper.KEY_MESSAGE,display.getText().toString());
                db.insert(scheduleDatabaseHelper.TABLE_NAME, "null",cValues);

                //this restarts the process of getCount()/ getView()
                sa.notifyDataSetChanged();
                display.setText("");

            }
        });


    }

    /**
     * Displays different dialog(calender, time, temperature)when user clicked different button
     * @param v the view of calender, time or temperature
     */
    @Override
    public void onClick(View v) {

        if (v == date) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            dateDisplay.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            display.setText(dateDisplay.getText().toString());
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == time) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            timeDisplay.setText(hourOfDay + ":" + minute);
                            display.setText(dateDisplay.getText().toString()+ " "+timeDisplay.getText().toString());
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == temp) {

            NumberPicker numberPicker = new NumberPicker(this);
            numberPicker.setMaxValue(40);
            numberPicker.setMinValue(0);
            numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    tempDisplay.setText((newVal + " \u2103"));
                    display.setText(dateDisplay.getText().toString()+" "+timeDisplay.getText().toString()+" "+ tempDisplay.getText().toString());

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
        }


    }

}
