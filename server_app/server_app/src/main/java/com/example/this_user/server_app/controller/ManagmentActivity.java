package com.example.this_user.ourproject5778_4711_9075.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.this_user.ourproject5778_4711_9075.R;

public class ManagmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managment);


        Button carButton = (Button) findViewById(R.id.car_);
        /**
         * car function button
         */
        carButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /**
                 * choose your opotion...
                 */
                AlertDialog mess = new AlertDialog.Builder(ManagmentActivity.this).create();
                mess.setTitle( getResources().getString(R.string.what_to));
                mess.setIcon(R.drawable.ic_action_ask);
                //list
                mess.setButton(AlertDialog.BUTTON_POSITIVE, getResources().getString(R.string.list), new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
//...
                                Intent i = new Intent(ManagmentActivity.this, CarListActivity.class);
                                startActivity(i);

                            }
                        });
                //add
                mess.setButton(AlertDialog.BUTTON_NEGATIVE, getResources().getString(R.string.add), new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(ManagmentActivity.this, AddCarActivity.class);
                                startActivity(i);
                            }
                        }
                );

                //cecel
                mess.setButton(AlertDialog.BUTTON_NEUTRAL, getResources().getString(R.string.cencel),new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }
                        );
                mess.show();
            }
        });

        /**
         * branch function
         */
        Button brunchButton = (Button) findViewById(R.id.branch_);
        brunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //choose option
                AlertDialog mess = new AlertDialog.Builder(ManagmentActivity.this).create();
                mess.setTitle( getResources().getString(R.string.what_to));
                mess.setIcon(R.drawable.ic_action_ask);
                //list
                mess.setButton(AlertDialog.BUTTON_POSITIVE,getResources().getString(R.string.list), new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
//...
                                Intent i = new Intent(ManagmentActivity.this, BrunchListActivity.class);
                                startActivity(i);
                            }
                        });
                //add
                mess.setButton(AlertDialog.BUTTON_NEGATIVE, getResources().getString(R.string.add), new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(ManagmentActivity.this, AddBranchActivity.class);
                                startActivity(i);
                            }
                        }
                );

                //cencel
                mess.setButton(AlertDialog.BUTTON_NEUTRAL, getResources().getString(R.string.cencel),new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }
                );
                mess.show();
            }
        });

        /*
        client option
         */
        Button clientButton = (Button) findViewById(R.id.client_);
        clientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //choose option
                AlertDialog mess = new AlertDialog.Builder(ManagmentActivity.this).create();
                mess.setTitle( getResources().getString(R.string.what_to));
                mess.setIcon(R.drawable.ic_action_ask);
                //list
                mess.setButton(AlertDialog.BUTTON_POSITIVE, getResources().getString(R.string.list), new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
//...
                                Intent i = new Intent(ManagmentActivity.this, ClientListActivity.class);
                                startActivity(i);
                            }
                        });
                //add
                mess.setButton(AlertDialog.BUTTON_NEGATIVE, getResources().getString(R.string.add), new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(ManagmentActivity.this, AddClientActivity.class);
                                startActivity(i);
                            }
                        }
                );
                //cencel
                mess.setButton(AlertDialog.BUTTON_NEUTRAL,getResources().getString(R.string.cencel),new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }
                );
                mess.show();
            }
        });

        /*
        model function
         */
        Button modelButton = (Button) findViewById(R.id.model_);
        modelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //choose option
                AlertDialog mess = new AlertDialog.Builder(ManagmentActivity.this).create();
                mess.setTitle( getResources().getString(R.string.what_to));
                mess.setIcon(R.drawable.ic_action_ask);
                //list
                mess.setButton(AlertDialog.BUTTON_POSITIVE,getResources().getString(R.string.list), new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
//...
                                Intent i = new Intent(ManagmentActivity.this, ModelListActivity.class);
                                startActivity(i);
                            }
                        });
                //add
                mess.setButton(AlertDialog.BUTTON_NEGATIVE, getResources().getString(R.string.add), new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(ManagmentActivity.this, AddModelActivity.class);
                                startActivity(i);
                            }
                        }
                );

                //cence
                mess.setButton(AlertDialog.BUTTON_NEUTRAL, getResources().getString(R.string.cencel),new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }
                );
                mess.show();
            }
        });



    }
}
