package com.example.this_user.ourproject5778_4711_9075.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.this_user.ourproject5778_4711_9075.R;
import com.example.this_user.ourproject5778_4711_9075.model.backend.BackEndInterface;
import com.example.this_user.ourproject5778_4711_9075.model.backend.DataSourceFactory;
import com.example.this_user.ourproject5778_4711_9075.model.entities.GearBox;
import com.example.this_user.ourproject5778_4711_9075.model.entities.IdentificationQuestions;
import com.example.this_user.ourproject5778_4711_9075.model.entities.Model;

import java.sql.Date;
import java.util.concurrent.ExecutionException;

public class AddModelActivity extends AppCompatActivity {



    final BackEndInterface backEnd = DataSourceFactory.getBackEnd();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_model);

        final EditText codeModel = (EditText) findViewById(R.id.codeModel);
        final EditText modelName = (EditText) findViewById(R.id.modelName);
        final EditText companyName = (EditText) findViewById(R.id.companyModel);
        final EditText engee = (EditText) findViewById(R.id.engineCapacity);
        final EditText seating = (EditText) findViewById(R.id.seatingPlace);
        final EditText year = (EditText) findViewById(R.id.productionYear);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        spinner.setAdapter(new ArrayAdapter<GearBox>(this, android.R.layout.simple_spinner_item, GearBox.values()));
        Button addModelButton = (Button) findViewById(R.id.addModelButton);
        /**
         * button event - add new model
         */
        addModelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codeModelLong = (codeModel.getText().toString());
                String modelNameString = modelName.getText().toString();
                String companyNameString = companyName.getText().toString();
                String engeeDouble =(engee.getText().toString());
                String seatInt = (seating.getText().toString());
                GearBox gearBox = (GearBox) spinner.getSelectedItem();
                int date = Integer.parseInt(year.getText().toString());

                Model m = new Model(codeModelLong, companyNameString, modelNameString, engeeDouble, gearBox ,seatInt,date );
                if(codeModelLong.matches("") || modelNameString.matches("") || companyNameString.matches("")
                       || engeeDouble.matches("") || seatInt.matches("") )
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.inputIn), Toast.LENGTH_SHORT).show();
                else
                {
                    try {
                        if(backEnd.getModelList()==null || !backEnd.modelExist(m)) {
                            backEnd.addModel(m);

                            //finish adding, what to do now?
                            AlertDialog mess = new AlertDialog.Builder(AddModelActivity.this).create();
                            mess.setIcon((R.drawable.ic_action_smile));
                            mess.setTitle(R.string.welcome);
                            mess.setMessage(getResources().getString(R.string.model_successfully_added));
                            //return home
                            mess.setButton(AlertDialog.BUTTON_NEGATIVE,getResources().getString( R.string.home), new
                                    DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            finish();
                                        }
                                    });
                            //add more
                            mess.setButton(AlertDialog.BUTTON_POSITIVE,getResources().getString( R.string.add_more), new
                                    DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            //clean the fildes....
                                            codeModel.setText("");
                                            modelName.setText("");
                                            year.setText("");
                                            companyName.setText("");
                                            engee.setText("");
                                            seating.setText("");
                                            codeModel.requestFocus();
                                            dialog.cancel();
                                        }
                                    });
                            mess.show();

                        }
                        else
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.model_exis), Toast.LENGTH_SHORT).show();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }
}
