package com.example.yu.team_project_1;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by CARLOS MENA... (c) Camesys.
 *
 * Displaying flyers and using a fragment
 */
public class K_FlyerListActivity  extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "K_FlyersListActivity";

    String flyer1 = "200x200_crop-north-force.jpg";
    String flyer2 = "347w7dy.jpg";
    String flyer3 = "Food_basics_flyer_august02.jpg";
    String flyer4 = "independent-grocer-atlantic-flyer-november-24-to-301.jpg";
    String flyer5 = "Metro.jpg";
    String flyer6 = "No-Frills-1.jpg";

    ImageView im1, im2, im3, im4, im5, im6;
    String[] pic = {"a", "b"};  // Array to pass values to to Detail Fragment file
    Button goBack;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.k_activity_flyer_list);

        /*  For now as Eric's recommendation I just check some images
            in the future I will be changing to pdf files, but as Eric said
            it will be a longer process, there is not enough time in this project.
         */
        im1 = (ImageView) findViewById(R.id.k_flyer_im1);
        im2 = (ImageView) findViewById(R.id.k_flyer_im2);
        im3 = (ImageView) findViewById(R.id.k_flyer_im3);
        im4 = (ImageView) findViewById(R.id.k_flyer_im4);
        im5 = (ImageView) findViewById(R.id.k_flyer_im5);
        im6 = (ImageView) findViewById(R.id.k_flyer_im6);
        goBack = (Button) findViewById(R.id.k_flyerbutton);

        String path = getApplication().getFilesDir().getAbsolutePath();
        Log.i(ACTIVITY_NAME, "This device path for files: " + path);
        pic[1] = path;
        try {
            InputStream p1 = new FileInputStream(path + "/" + flyer1);
            Drawable pict1 = new BitmapDrawable(p1);
            im1.setImageDrawable(pict1);
            InputStream p2 = new FileInputStream(path + "/" + flyer2);
            Drawable pict2 = new BitmapDrawable(p2);
            im2.setImageDrawable(pict2);
            InputStream p3 = new FileInputStream(path + "/" + flyer3);
            Drawable pict3 = new BitmapDrawable(p3);
            im3.setImageDrawable(pict3);
            InputStream p4 = new FileInputStream(path + "/" + flyer4);
            Drawable pict4 = new BitmapDrawable(p4);
            im4.setImageDrawable(pict4);
            InputStream p5 = new FileInputStream(path + "/" + flyer5);
            Drawable pict5 = new BitmapDrawable(p5);
            im5.setImageDrawable(pict5);
            InputStream p6 = new FileInputStream(path + "/" + flyer6);
            Drawable pict6 = new BitmapDrawable(p6);
            im6.setImageDrawable(pict6);

        } catch (Exception e) {
            Log.i(ACTIVITY_NAME, "Files not downloaded yet");
            Toast emptyList = Toast.makeText(K_FlyerListActivity.this,
                    getString(R.string.K_Toast4), Toast.LENGTH_LONG);
            emptyList.show();
            finish();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.flyer_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        im1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                pic[0] = flyer1;
                myTwoPane(view,pic);
            }
        });

        im2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                pic[0] = flyer2;
                myTwoPane(view,pic);
            }
        });
        im3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                pic[0] = flyer3;
                myTwoPane(view,pic);
            }
        });
        im4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                pic[0] = flyer4;
                myTwoPane(view,pic);
            }
        });

        im5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                pic[0] = flyer5;
                myTwoPane(view,pic);
            }
        });
        im6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                pic[0] = flyer6;
                myTwoPane(view,pic);
            }
        });
        goBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /** To call the frame, and pass an array with path and file name */
    private void myTwoPane(View v, String[] pic) {

        if (mTwoPane) {
            Log.i(ACTIVITY_NAME,"On tablet");
            Bundle arguments = new Bundle();
            arguments.putString(K_FlyerDetailFragment.ARG_ITEM_ID, pic[0]);
            arguments.putString(K_FlyerDetailFragment.ARG_FLYER_PATH, pic[1]);
            K_FlyerDetailFragment fragment = new K_FlyerDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flyer_detail_container, fragment)
                    .commit();
        } else {
            Log.i(ACTIVITY_NAME,"On phone");
            Context context = v.getContext();
            Intent intent = new Intent(context, K_FlyerDetailActivity.class);
            intent.putExtra(K_FlyerDetailFragment.ARG_ITEM_ID, pic[0]);
            intent.putExtra(K_FlyerDetailFragment.ARG_FLYER_PATH, pic[1]);

            context.startActivity(intent);
        }
    }
}   // *-* End of file *-*