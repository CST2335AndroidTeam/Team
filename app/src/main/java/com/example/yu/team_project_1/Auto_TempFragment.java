package com.example.yu.team_project_1;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.yu.team_project_1.Auto_OdometerFragment.AUTO_PREFERENCE_FILE;

/**
 * A simple {@link Fragment} subclass.
 *
 * Automobile Temperature Fragment, allowing user to control in-car temperature.
 * Control temperature in 2 part (front and back) of the car, through NumberPicker
 * Button color will change (warm = red, cold = blue) when temperature adjusted.
 * Temperature value is stored in SharedPreference
 *
 * @author Mochen Jin
 */

public class Auto_TempFragment extends Fragment {

    private NumberPicker front;
    private NumberPicker back;
    private Button frontButton;
    private Button backButton;

    public static final int MIN_TEMP = 10;
    public static final int MAX_TEMP = 40;

    public Auto_TempFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.auto_fragment_temp, container, false);

        front = (NumberPicker) view.findViewById(R.id.auto_front_temp);
        back = (NumberPicker) view.findViewById(R.id.auto_back_temp);
        frontButton = (Button)view.findViewById(R.id.auto_front_button);
        backButton = (Button)view.findViewById(R.id.auto_back_button);

        SharedPreferences prefs = getActivity().getSharedPreferences(AUTO_PREFERENCE_FILE, Context.MODE_PRIVATE);
        int frontValue = prefs.getInt("front_temp",MIN_TEMP);
        int backValue = prefs.getInt("back_temp",MAX_TEMP);

        front.setMaxValue(MAX_TEMP);
        front.setMinValue(MIN_TEMP);
        front.setValue(frontValue);
        front.setWrapSelectorWheel(false);
        adjustColor(frontButton,frontValue);

        front.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                SharedPreferences.Editor editor = getActivity().getSharedPreferences(AUTO_PREFERENCE_FILE,Context.MODE_PRIVATE).edit();
                editor.putInt("front_temp", newVal);
                editor.commit();

                adjustColor(frontButton,newVal);
            }
        });

        back.setMaxValue(MAX_TEMP);
        back.setMinValue(MIN_TEMP);
        back.setValue(backValue);
        back.setWrapSelectorWheel(false);

        adjustColor(backButton,backValue);

        back.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                SharedPreferences.Editor editor = getActivity().getSharedPreferences(AUTO_PREFERENCE_FILE,Context.MODE_PRIVATE).edit();
                editor.putInt("back_temp", newVal);
                editor.commit();

                adjustColor(backButton,newVal);
            }
        });

        frontButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Front Temperature Set to " + front.getValue() + "°C", Toast.LENGTH_SHORT).show();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Back Temperature Set to " + back.getValue() + "°C", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void adjustColor(Button button, int value){
        int Rhigh = 229;
        int Ghigh = 0;
        int Bhigh = 11;
        int Rlow = 0;
        int Glow = 191;
        int Blow = 182;

        float percent = ((float)value-MIN_TEMP)/(MAX_TEMP-MIN_TEMP);

        int R = (int)((Rhigh-Rlow) * percent + Rlow);
        int G = (int)((Ghigh-Glow) * percent + Glow);
        int B = (int)((Bhigh-Blow) * percent + Blow);

        button.setBackgroundColor(Color.rgb(R,G,B));

    }

}
