package com.example.yu.team_project_1;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.yu.team_project_1.Auto_OdometerFragment.AUTO_PREFERENCE_FILE;
import static java.lang.Integer.parseInt;

/**
 * hideKeyboard method by 'rmirabelle' from StackOverflow:
 *  http://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
 */
public class Auto_DriveFragment extends Fragment {

    private EditText kilometer;
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
        kilometer = (EditText) view.findViewById(R.id.auto_input_kilometer);


        addKmButton = (Button) view.findViewById(R.id.add_km_button);
        addKmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kmStr = kilometer.getText().toString();
                try{
                   int km =  Integer.parseInt(kmStr);

                    SharedPreferences prefs = getActivity().getSharedPreferences(AUTO_PREFERENCE_FILE, Context.MODE_PRIVATE);
                    int currentKm = prefs.getInt("distance",0);
                    int newKm = currentKm + km;
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences(AUTO_PREFERENCE_FILE,Context.MODE_PRIVATE).edit();
                    editor.putInt("distance", newKm);
                    editor.commit();

                    int tripCurrentKm = prefs.getInt("trip_distance",0);
                    int tripNewKm = tripCurrentKm + km;
                    editor.putInt("trip_distance", tripNewKm);
                    editor.commit();
                    hideKeyboard(getActivity());

                    Toast.makeText(getActivity(),km+ " kilometers added", Toast.LENGTH_LONG).show();
                }catch(NumberFormatException nfe){
                    Toast.makeText(getContext(),"Error! please enter a number", Toast.LENGTH_LONG).show();
                }

            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hideKeyboard(getActivity());
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
