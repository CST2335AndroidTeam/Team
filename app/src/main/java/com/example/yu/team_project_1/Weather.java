package com.example.yu.team_project_1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.StringTokenizer;


public class Weather extends AppCompatActivity {

    TextView temp;
    TextView humi;
    TextView wind;
    ImageView weatherImage;
    public static final String LOCAL_IMAGE = "local";
    public static final String DOWNLOAD_IMAGE = "download";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        temp = (TextView)findViewById(R.id.temp_textView);
        humi = (TextView)findViewById(R.id.humidity_textView) ;
        wind = (TextView)findViewById(R.id.wind_textView);
        weatherImage = (ImageView) findViewById(R.id.weather_imageView);

        String weatherURL = "http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric";
        WeatherQuery fq = new WeatherQuery();
        fq.execute(weatherURL);
    }

    public static Bitmap getImage(URL url) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                return BitmapFactory.decodeStream(connection.getInputStream());
            } else
                return null;
        } catch (Exception e) {
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


    private class WeatherQuery extends AsyncTask<String,Integer,String>{


        String temperatureText=null;
        String iconName = null;
        String humidityText = null;
        String windText = null;
        Bitmap image;

        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);

        @Override
        protected String doInBackground(String... params) {
            String wURL = params[0];
            try {
                URL url = new URL(wURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();

                try {
                    XmlPullParser parser = Xml.newPullParser();
                    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    parser.setInput(conn.getInputStream(), null);
                    parser.nextTag();



                    while (parser.next() != XmlPullParser.END_DOCUMENT) {
                        if (parser.getEventType() != XmlPullParser.START_TAG) {
                            continue;
                        }
                        String name = parser.getName();

                        if (name.equals("temperature")) {
                            temperatureText = parser.getAttributeValue(null, "value");
                            SystemClock.sleep(1000);
                            publishProgress(25);
                        }
                        if(name.equals("humidity")){
                            humidityText = parser.getAttributeValue(null,"value");
                            SystemClock.sleep(1000);
                            publishProgress(50);
                        }
                        if(name.equals("speed")){
                            windText = parser.getAttributeValue(null,"value");
                            SystemClock.sleep(1000);
                            publishProgress(75);
                        }
                        if(name.equals("weather")){
                            iconName = parser.getAttributeValue(null,"icon");
                            String imageURL_Str = "http://openweathermap.org/img/w/" + iconName + ".png";
                            String fileName = iconName + ".png";

                            File file = getBaseContext().getFileStreamPath(fileName);
                            if(file.exists()){
                                //when this file exists
                                FileInputStream fis = null;

                                try {
                                    fis = new FileInputStream(file);
                                }catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                image = BitmapFactory.decodeStream(fis);
                                Log.i(LOCAL_IMAGE,"Found the image locally");

                            }else{
                                //download image
                                image = getImage(new URL(imageURL_Str));
                                Log.i(DOWNLOAD_IMAGE,"Can't find image locally, download it");
                                FileOutputStream outputStream = openFileOutput( iconName + ".png", Context.MODE_PRIVATE);
                                image.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                                outputStream.flush();
                                outputStream.close();

                            }

                            if(image != null){
                                publishProgress(100);
                            }


                        }
                    }


                } catch ( XmlPullParserException ex ){
                    return null;
                }
                return (temperatureText +" "+ humidityText + " "+windText);

            }catch (IOException ioe){
                Log.i("IOException"," 1231231231");
                return null;
            }

        }



        @Override
        protected void onProgressUpdate(Integer... progress) {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(progress[0]);

        }

        @Override
        protected void onPostExecute(String result) {
            String[] separated = result.split(" ");

            temp.setText( (separated[0] + " \u2103"));
            humi.setText("Humidity = "+ separated[1] + " %");
            wind.setText("Wind = "+ separated[2]+" km/h");
            weatherImage.setImageBitmap(image);
            progressBar.setVisibility(View.INVISIBLE);
        }


    }



}