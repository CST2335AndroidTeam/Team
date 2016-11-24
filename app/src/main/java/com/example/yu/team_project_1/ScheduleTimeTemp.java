package com.example.yu.team_project_1;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;


import android.content.DialogInterface;
import android.content.Intent;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import java.util.Calendar;


public class ScheduleTimeTemp extends AppCompatActivity implements View.OnClickListener{
    Button date;
    Button time;
    Button temp;

    EditText dateDisplay;
    EditText timeDisplay;
    EditText TempDisplay;

    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_time_temp);

        date = (Button)findViewById(R.id.changeDate);
        time = (Button)findViewById(R.id.changeTime);
        temp = (Button)findViewById(R.id.changeTemp);
        dateDisplay = (EditText)findViewById(R.id.displayDateText);
        timeDisplay = (EditText)findViewById(R.id.displayTimeText);
        TempDisplay = (EditText)findViewById(R.id.displayTempText);

        date.setOnClickListener(this);
        time.setOnClickListener(this);
    }

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
                    TempDisplay.setText((newVal + " \u2103"));

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
