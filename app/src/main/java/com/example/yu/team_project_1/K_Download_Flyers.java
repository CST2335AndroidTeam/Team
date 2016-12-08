package com.example.yu.team_project_1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by CARLOS MENA... (c) Camesys.
 *
 * Class to download the pictures of the flyers from the intenet.
 */
public class K_Download_Flyers extends Activity {

    protected static final String ACTIVITY_NAME = "K_Download_Flyers";

    ProgressDialog barProgressDialog;
    Handler updateBarHandler;
    String flyer1, flyer2, flyer3, flyer4, flyer5, flyer6;
    boolean exist;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.k_flyers_download);

        updateBarHandler = new Handler();

        new Flyers().execute();
        launchRingDialog();

        if (exist) {
            launchBarDialog(getString(R.string.K_LaunchBar1),1,2000);
         //   launchBarDialog("Flyers already on device\n no download required...",1,2000);
        } else {
            launchBarDialog(getString(R.string.K_LaunchBar2),6,5000);
         //   launchBarDialog("Download in progress ...",6,5000);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

    /* Display a progress dialog with a ring */
    public void launchRingDialog() {
        final ProgressDialog ringProgressDialog = ProgressDialog.show(K_Download_Flyers.this,
                getString(R.string.K_Wait),	getString(R.string.K_LaunchBar4), true);
        ringProgressDialog.setCancelable(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) { Log.i(ACTIVITY_NAME, "No sleep"); }
                ringProgressDialog.dismiss();
            }
        }).start();
    }

    /* Displays a dialog with a progress bar */
    public void launchBarDialog(String msg, int max, int time){
        barProgressDialog = new ProgressDialog(K_Download_Flyers.this);
        final int timer = time;
        barProgressDialog.setTitle(getString(R.string.K_LaunchBar3));
        barProgressDialog.setMessage(msg);
        barProgressDialog.setProgressStyle(barProgressDialog.STYLE_HORIZONTAL);
        barProgressDialog.setProgress(0);
        barProgressDialog.setMax(max);
        barProgressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (barProgressDialog.getProgress() <= barProgressDialog.getMax()) {

                        Thread.sleep(timer);
                        updateBarHandler.post(new Runnable() {

                            public void run() {
                                barProgressDialog.incrementProgressBy(1);
                            }
                        });

                        if (barProgressDialog.getProgress() == barProgressDialog.getMax()) {
                            barProgressDialog.dismiss();
                            finish();
                        }
                    }
                } catch (Exception e) { Log.i(ACTIVITY_NAME, "Dialog Successful");  }
            }
        }).start();
    }

    /* INNER CLASS TO DOWNLOAD THE IMAGE FLYERS */
    private class Flyers extends AsyncTask<String, String, Void> {

        protected Void doInBackground(String... urls) {

            flyer1 = "200x200_crop-north-force.jpg";
            flyer2 = "347w7dy.jpg";
            flyer3 = "Food_basics_flyer_august02.jpg";
            flyer4 = "independent-grocer-atlantic-flyer-november-24-to-301.jpg";
            flyer5 = "Metro.jpg";
            flyer6 = "No-Frills-1.jpg";
            String url1 = "http://dam-img.rfdcontent.com/cms/002/323/408/";
            String url2 = "http://i44.tinypic.com/";
            String url3 = "http://www.yflyers.com/wp-content/uploads/2013/08/";
            String url4 = "http://flyerify.com/uploads/pages/58249/";
            String url5 = "http://www.yflyers.com/wp-content/uploads/2015/12/";
            String url6 = "http://www.yflyers.com/wp-content/uploads/2015/12/";

            toDownload(url1, flyer1);       // Superstore
            toDownload(url2, flyer2);       // WalMart
            toDownload(url3, flyer3);       // Food Basics
            toDownload(url4, flyer4);       // Independent
            toDownload(url5, flyer5);       // Metro
            toDownload(url6, flyer6);       // No Frills

            return null;
        }   // End of doInBackground

        /* Downloads an image file */
        public void toDownload (String url, String file) {

            try {
                boolean file1 = fileExistance(file);
                if (file1)
                {
                 Log.i(ACTIVITY_NAME, "File " + file + " already exist...");
                    exist = true;
                } else {
                    exist = false;
                    Log.i(ACTIVITY_NAME, "Loading file " + file + " from external source...");
                    Log.i(ACTIVITY_NAME,"Downloading from..." + url + file );
                    String ImageURL = url + file;

                    URL urlD = new URL(ImageURL);
                    HttpURLConnection connection = null;
                    connection = (HttpURLConnection) urlD.openConnection();
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                        FileOutputStream outputStream = openFileOutput(file, Context.MODE_PRIVATE);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                    }
                    if (connection != null) { connection.disconnect(); }
                }
            } catch (Exception e) { Log.i(ACTIVITY_NAME,e.toString());  }
        } // End of toDownload

        /* Checks if file already exists */
        public boolean fileExistance (String name) {
            File file = getBaseContext().getFileStreamPath(name);
            return file.exists();
        }
    }   // End of Inner Class

} // *-* End of file *-*