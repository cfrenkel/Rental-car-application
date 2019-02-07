package com.example.this_user.ourproject5778_4711_9075.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.this_user.ourproject5778_4711_9075.R;
import com.example.this_user.ourproject5778_4711_9075.model.backend.BackEndInterface;
import com.example.this_user.ourproject5778_4711_9075.model.backend.DataSourceFactory;
import com.example.this_user.ourproject5778_4711_9075.model.entities.Branch;

import java.util.Random;
import java.util.concurrent.ExecutionException;

public class AddBranchActivity extends AppCompatActivity {

    final BackEndInterface backEnd = DataSourceFactory.getBackEnd();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_branch);

        final EditText branchAdr = (EditText) findViewById(R.id.addressBranch);
        final EditText branchPn = (EditText) findViewById(R.id.parkingBrunch);
        Button addBranch = (Button) findViewById(R.id.bracnAddButton);
        addBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numB = new Random().nextInt();
                Branch b = new Branch(branchAdr.getText().toString(), (branchPn.getText().toString()), numB);
                if(!branchAdr.getText().toString().matches("") && !branchPn.getText().toString().matches("")) {
                    try {

                        if (!backEnd.branchExist(b)) {
                            backEnd.addBrunch(b);

                            AlertDialog mess = new AlertDialog.Builder(AddBranchActivity.this).create();
                            mess.setIcon((R.drawable.ic_action_smile));
                            mess.setTitle(getResources().getString(R.string.welcome));
                            mess.setMessage(getResources().getString(R.string.branch_successfully_added));
                            //return home
                            mess.setButton(AlertDialog.BUTTON_NEGATIVE, getResources().getString(R.string.home), new
                                    DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            finish();
                                        }
                                    });
                            //add more....
                            mess.setButton(AlertDialog.BUTTON_POSITIVE, getResources().getString(R.string.add_more), new
                                    DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            //clean the fildes and put the cursor in the first one
                                          branchAdr.setText("");
                                            branchPn.setText("");
                                            branchAdr.requestFocus();
                                            dialog.cancel();
                                        }
                                    });

                            mess.show();

                        } else {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.branch_exis), Toast.LENGTH_SHORT).show();
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.inputIn), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
