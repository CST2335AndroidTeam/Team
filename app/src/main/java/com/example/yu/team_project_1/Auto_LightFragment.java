package com.example.yu.team_project_1;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;



public class Auto_LightFragment extends Fragment {

    private ToggleButton beamButton;
    private ToggleButton lightButton;
    private Switch beamSwitch;
    private ImageView beamImage;
    private ImageView lightImage;
    private SeekBar seekBar;
    private View.OnClickListener onBeamUndoClickListener;
    private View.OnClickListener offBeamUndoClickListener;
    private View.OnClickListener onLightUndoClickListener;
    private View.OnClickListener offLightUndoClickListener;

    private boolean highBeamOn;
    private boolean lightOn;
    private boolean beamOn;

    public Auto_LightFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onBeamUndoClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnOffBeam();
            }
        };
        offBeamUndoClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnOnBeam();
            }
        };

        onLightUndoClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnOffLight();
            }
        };
        offLightUndoClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnOnLight();
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_auto__light, container, false);
        beamButton = (ToggleButton)view.findViewById(R.id.auto_beam_onoff_toggle);
        lightButton = (ToggleButton)view.findViewById(R.id.auto_light_onoff_toggle);
        beamSwitch = (Switch) view.findViewById(R.id.auto_beam_switch);
        beamImage = (ImageView) view.findViewById(R.id.auto_beam_image);
        lightImage = (ImageView) view.findViewById(R.id.auto_light_image);
        seekBar = (SeekBar)view.findViewById(R.id.auto_light_seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                if(lightOn){
                    lightImage.setBackgroundColor(Color.argb((int)(progress/100.0*255), 255,255,0));
                }
            }
        });


        beamButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    turnOnBeam();
                }else{
                    turnOffBeam();
                }
            }
        });

        lightButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    turnOnLight();
                }else{
                    turnOffLight();
                }
            }
        });

        beamSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                highBeamOn = isChecked;
                if(beamOn) {
                    if (isChecked)
                        beamImage.setBackgroundResource(R.drawable.auto_high_beam);
                    else
                        beamImage.setBackgroundResource(R.drawable.auto_low_beam);
                }

            }
        });


        return view;
    }

    private void turnOnBeam(){
        beamOn = true;
        String highLow = "";
        if(highBeamOn){
            beamImage.setBackgroundResource(R.drawable.auto_high_beam);
            highLow = "High";
        }else{
            beamImage.setBackgroundResource(R.drawable.auto_low_beam);
            highLow = "Normal";
        }
        Snackbar snackbar = Snackbar
                .make(getView().findViewById(R.id.auto_light_fragment_layout), "Beam light on :" + highLow + " mode", Snackbar.LENGTH_LONG)
                .setAction("Undo", onBeamUndoClickListener);
        snackbar.setActionTextColor(Color.RED);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.DKGRAY);
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();

    }

    private void turnOffBeam(){
        beamOn = false;
        beamImage.setBackgroundResource(R.drawable.auto_no_beam);
        Snackbar snackbar = Snackbar
                .make(getView().findViewById(R.id.auto_light_fragment_layout), "Beam light off", Snackbar.LENGTH_SHORT)
                .setAction("Undo", offBeamUndoClickListener);
        snackbar.setActionTextColor(Color.RED);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.DKGRAY);
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.BLACK);
        snackbar.show();

    }

    private void turnOnLight(){
        lightOn = true;
        int progress = seekBar.getProgress();
        lightImage.setBackgroundColor(Color.argb((int)(progress/100.0*255), 255,255,0));
        lightImage.setImageResource(R.drawable.auto_yes_light);

        Snackbar snackbar = Snackbar
                .make(getView().findViewById(R.id.auto_light_fragment_layout), "in car light on " , Snackbar.LENGTH_LONG)
                .setAction("Undo", onLightUndoClickListener);
        snackbar.setActionTextColor(Color.RED);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.DKGRAY);
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }

    private void turnOffLight(){
        lightOn = false;
        lightImage.setImageResource(R.drawable.auto_no_light);
        lightImage.setBackgroundColor(Color.argb(0,0,0,0));

        Snackbar snackbar = Snackbar
                .make(getView().findViewById(R.id.auto_light_fragment_layout), "in car light off " , Snackbar.LENGTH_LONG)
                .setAction("Undo", offLightUndoClickListener);
        snackbar.setActionTextColor(Color.RED);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.DKGRAY);
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.BLACK);
        snackbar.show();
    }
}
