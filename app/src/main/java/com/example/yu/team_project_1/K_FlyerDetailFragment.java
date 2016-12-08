package com.example.yu.team_project_1;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

//import com.example.yu.team_project_1.dummy.DummyContent;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by CARLOS MENA... (c) Camesys.
 *
 * Class to create a fragment to show the flyers.
 */
public class K_FlyerDetailFragment extends Fragment {

    protected static final String ACTIVITY_NAME = "K_FlyerDetailFragment";

    public static final String ARG_ITEM_ID = "name";
    public static final String ARG_FLYER_PATH = "path";

    private String myFlyer, myPath;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public K_FlyerDetailFragment() {    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {

            myFlyer = getArguments().getString(ARG_ITEM_ID);
            myPath = getArguments().getString(ARG_FLYER_PATH);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(getString(R.string.K_Misc1));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.k_flyer_detail, container, false);

        if (myFlyer != null) {
            try {
                InputStream p1 = new FileInputStream(myPath + "/" + myFlyer);
                Drawable pict = new BitmapDrawable(p1);
                ((ImageView) rootView.findViewById(R.id.flyer_detail)).setImageDrawable(pict);
            } catch (Exception e) { Log.i(ACTIVITY_NAME,e.toString());}
        }
        return rootView;
    }
}   // *- End of file -*