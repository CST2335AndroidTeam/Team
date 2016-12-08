package com.example.yu.team_project_1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.yu.team_project_1.R;


public class Auto_DriveFragment extends Fragment {

    private Button addKmButton;

    public Auto_DriveFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drive, container, false);
        addKmButton = (Button) view.findViewById(R.id.add_km_button);
        addKmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "kilometers added", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }


}
