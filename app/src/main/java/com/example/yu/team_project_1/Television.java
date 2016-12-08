package com.example.yu.team_project_1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class Television extends AppCompatActivity {

    private GridView channelGrid;
    private Button powerButton;
    private boolean tvPower = false;
    private String wholeChannel;
    private Button buttonright;
    private Button buttonleft;
    private Button buttondown;
    private Button buttonup;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_television);

        Button button = (Button)findViewById(R.id.powerbutton);
        button.setOnClickListener(new OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          /*if(tvPower == false) {
                                              tvPower = true;
                                              Toast.makeText(Television.this, "Television on", Toast.LENGTH_SHORT).show();
                                          }else {
                                              tvPower = false;
                                              Toast.makeText(Television.this, "Television off", Toast.LENGTH_SHORT).show();
                                          }*/

                                          tvPower = !tvPower;
                                          String stringToDisplay = tvPower ? "TV on" : "TV off";
                                          Toast.makeText(Television.this, stringToDisplay, Toast.LENGTH_SHORT).show();
                                      }
                                  });


        GridView gridview = (GridView) findViewById(R.id.channelgrid);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if(position >=0 && position <=8){// parse position +1 to string
                    position++;
                    String channel = Integer.toString(position);
                    // string add to variable
                    wholeChannel = wholeChannel + channel;
                }else if(position == 11){
                    wholeChannel = wholeChannel + "0";
                }


            }
        });
    }
    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return channelButtons.length + 1;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = null;
            if (position >= 0 && position <= 8) {
                if (convertView == null) {
                    // if it's not recycled, initialize some attributes
                    imageView = getImageView();
                } else {
                    imageView = (ImageView) convertView;
                }

                imageView.setImageResource(channelButtons[position]);
            } else if(position==9){
                View newView = new View(mContext);
                newView.setLayoutParams(new GridView.LayoutParams(115, 115));
                return newView;
            } else if(position == 10){
                if (convertView == null) {
                    // if it's not recycled, initialize some attributes
                    imageView = getImageView();
                } else {
                    imageView = (ImageView) convertView;
                }

                imageView.setImageResource(channelButtons[9]);
            }

            return imageView;
        }

        private ImageView getImageView() {
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(115,115));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
            return imageView;
        }


        private Integer[] channelButtons = {
                R.drawable.tv_button1, R.drawable.tv_button2, R.drawable.tv_button3,
                R.drawable.tv_button4, R.drawable.tv_button5, R.drawable.tv_button6,
                R.drawable.tv_button7, R.drawable.tv_button8, R.drawable.tv_button9,
                R.drawable.tv_button0,

        };
    }
 /*  Button buttonUp = (Button)findViewById(R.id.upbutton);

    Button buttonRight = (Button)findViewById(R.id.rightbutton);
    Button buttonLeft = (Button)findViewById(R.id.leftbutton);

    Button buttonDown = (Button)findViewById(R.id.downbutton);
    */
}
