package com.example.yu.team_project_1;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 *
 * Automobile Odometer Fragment, contains odometer in TextView and Trip distance,
 *  also a "reset" button.
 *  number in odometer and trip distance is from SharedPreference.
 *  @author Mochen Jin
 */
public class Auto_OdometerFragment extends Fragment {

    private TextView odometer;
    private TextView tripDistance;
    private Button resetButton;
    public static final String AUTO_PREFERENCE_FILE = "AutomobileData";
    int distanceKM;
    int tripKM;

    public Auto_OdometerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.auto_fragment__odometer, container, false);
        odometer = (TextView) view.findViewById(R.id.auto_odometer);
        tripDistance = (TextView) view.findViewById(R.id.auto_trip_distance);
        resetButton = (Button) view.findViewById(R.id.auto_reset_button);

        SharedPreferences prefs = getActivity().getSharedPreferences(AUTO_PREFERENCE_FILE, Context.MODE_PRIVATE);
        distanceKM = prefs.getInt("distance",0);
        tripKM = prefs.getInt("trip_distance",0);

        odometer.setText(Integer.toString(distanceKM));
        tripDistance.setText(Integer.toString(tripKM));


        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tripKM = 0;
                tripDistance.setText(Integer.toString(tripKM));
                SharedPreferences.Editor editor = getActivity().getSharedPreferences(AUTO_PREFERENCE_FILE,Context.MODE_PRIVATE).edit();
                editor.putInt("trip_distance", 0);
                editor.commit();
            }
        });

        return view;
    }

}
