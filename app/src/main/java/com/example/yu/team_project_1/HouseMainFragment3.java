package com.example.yu.team_project_1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class HouseMainFragment3 extends Fragment {
    /** the text view of the weather instruction*/
    TextView textWeather;
    /** empty constructor */
    public HouseMainFragment3() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Returns view of frfment_houst_main_fragment1
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_house_main_fragment3, container, false);
        // Inflate the layout for this fragment
        textWeather = (TextView)view.findViewById(R.id.TextWeather);
        textWeather.setText(Html.fromHtml(getString(R.string.weatherFragment)));

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}