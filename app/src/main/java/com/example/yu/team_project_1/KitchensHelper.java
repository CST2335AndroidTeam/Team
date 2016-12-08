package com.example.yu.team_project_1;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by CARLOS MENA... (c) Camesys.
 *
 *  Main Class for the Shopping list project.
 */
public class KitchensHelper extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "KitchensHelper";
    final Context context = this;
    Button newList;
    Button editList;
    Button doShopping;
    Button exit;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.k_main_menu);
        setTitle(R.string.carlos_app_name);

        newList = (Button) findViewById(R.id.K_Main_B1);
        editList = (Button) findViewById(R.id.K_Main_B2);
        doShopping = (Button) findViewById(R.id.K_Main_B3);
        exit = (Button) findViewById(R.id.K_Main_B4);

        newList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(KitchensHelper.this);
                builder.setMessage(getString(R.string.K_Misc2));
                builder.setTitle(getString(R.string.K_Misc3));
                builder.setPositiveButton(getString(R.string.K_Yes), new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int id) {
                      Intent intent = new Intent(KitchensHelper.this, K_New_List.class);
                      startActivityForResult(intent, 5);
                  }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        editList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KitchensHelper.this, K_Edit_List.class);
                startActivityForResult(intent, 5);
            }
        });

        doShopping.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KitchensHelper.this, K_Shopping.class);
                startActivityForResult(intent, 5);
            }
        });

        exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 5){
            Log.i(ACTIVITY_NAME, "Returned to ShoppingMenu.onActivityResult");
        }

        if(resultCode == KitchensHelper.RESULT_OK){

            String messagePassed = data.getStringExtra("Response");

            if (messagePassed.length() > 0) {

                CharSequence text = "New List passed: My information to share";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(this, text, duration);
                toast.show();
            }
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

    /* This creates the Help menu item*/
    public boolean onCreateOptionsMenu (Menu m) {

        getMenuInflater().inflate(R.menu.k_toolbar_menu, m);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem mi) {

        switch (mi.getItemId()) {
            case R.id.k_action_help:
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.k_dialog);
                dialog.setTitle(getString(R.string.K_D_Help1));
                TextView text = (TextView) dialog.findViewById(R.id.k_dialog_text);
                text.setText(getString(R.string.K_D_Help2)+"\n"+getString(R.string.K_D_Help3)
                    +"\n"+getString(R.string.K_D_Help4)+"\n"+getString(R.string.K_D_Help5)
                    +"\n"+getString(R.string.K_D_Help6)+"\n"+getString(R.string.K_D_Help7)
                    +"\n"+getString(R.string.K_D_Help8));
                Button dialogButton = (Button) dialog.findViewById(R.id.k_dialog_b1);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
        }
        return true;
    }

} // *-* End of file *-*