package com.example.this_user.ourproject5778_4711_9075.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import com.example.this_user.ourproject5778_4711_9075.model.entities.Branch;
import com.example.this_user.ourproject5778_4711_9075.model.entities.Car;
import com.example.this_user.ourproject5778_4711_9075.model.entities.IdentificationQuestions;
import com.example.this_user.ourproject5778_4711_9075.model.entities.Model;
import com.example.this_user.ourproject5778_4711_9075.model.entities.Price;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AddCarActivity extends AppCompatActivity {

    final BackEndInterface backEnd = DataSourceFactory.getBackEnd();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        final EditText carNumText = (EditText)findViewById(R.id.numCar);
        final Spinner modelName =  (Spinner) findViewById(R.id.nameModel);
        final EditText kiloText = (EditText)findViewById(R.id.kilometers);
        final EditText price = (EditText)findViewById(R.id.price);
        final Spinner spinnerBranch = (Spinner) findViewById(R.id.branchPark) ;

        try {
            showSpinnerBranch(spinnerBranch);
            showSpinnerModel(modelName);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * text chang event - cheak the input
         */
        carNumText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    int len = carNumText.getText().toString().length();
                    if(len!=7 ||len!=8 )
                    {
                     //   Toast.makeText(getApplicationContext(), getResources().getString(R.string.car_num_must),
                     //         Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
/**
 * text chang event - cheak the input
 */
        modelName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });
/**
 * text chang event - cheak the input
 */
        kiloText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    int kilo =  Integer.parseInt(kiloText.getText().toString());
                    if(kilo<=0)
                    {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.kilo), Toast.LENGTH_SHORT).show();
                        kiloText.setTextColor(getResources().getColor(R.color.colorHi));
                    }
                    else
                        carNumText.setTextColor(Color.BLACK);
                }

            }
        });


        Button addButton = (Button) findViewById(R.id.addCarButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(kiloText.getCurrentHintTextColor()!=getResources().getColor(R.color.colorHi) && price.getText().length()!=0 &&
                        (carNumText.getText().toString().length()==7 || carNumText.getText().toString().length()==8))
                {
                    int kilo =  Integer.parseInt(kiloText.getText().toString());
                    int branchNum = (Integer) spinnerBranch.getSelectedItem();
                    float price1 = Float.parseFloat( price.getText().toString());
                    String modelsName = (String) modelName.getSelectedItem();
                    Long numCar = Long.parseLong(carNumText.getText().toString());
                    Car c = new Car(branchNum, modelsName, kilo, numCar);
                    Price p = new Price(price1,numCar);
                    try {
                        if(backEnd.getCarList()==null || !backEnd.carExist(c))
                        {
                            backEnd.addCar(c);
                            backEnd.addPrice(p);
                            //finish working what to do now?
                            AlertDialog mess = new AlertDialog.Builder(AddCarActivity.this).create();
                            mess.setIcon((R.drawable.ic_action_smile));
                            mess.setTitle(R.string.welcome);
                            mess.setMessage(getResources().getString(R.string.car_successfully_added));
                            //return home
                            mess.setButton(AlertDialog.BUTTON_NEGATIVE,getResources().getString( R.string.home), new
                                    DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            finish();
                                        }
                                    });
                            //add more....
                            mess.setButton(AlertDialog.BUTTON_POSITIVE,getResources().getString( R.string.add_more), new
                                    DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            //clean the fildes and put the cursor in the first one
                                            carNumText.setText("");
                                            kiloText.setText("");
                                            //carNumText.requestFocus();
                                            dialog.cancel();

                                        }
                                    });
                            mess.show();
                        }
                        else
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.car_exis), Toast.LENGTH_SHORT).show();
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


    /**
     * put the valus of banch numbers in the spinner
     * @throws ExecutionException
     * @throws InterruptedException
     */
    void showSpinnerBranch(Spinner spinnerBranch) throws ExecutionException, InterruptedException
    {
        List<Branch> branchList =  backEnd.getBranchList();
        List<Integer> branchNum = new ArrayList<Integer>();

        for(int i =0; i<branchList.size(); i++)
        {
            Integer ii =branchList.get(i).getBranchNum();
            branchNum.add(ii);
        }


        ArrayAdapter<Integer> integerArrayAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item,branchNum);
        spinnerBranch.setAdapter(integerArrayAdapter);

    }

    void showSpinnerModel(Spinner spinnerBranch) throws ExecutionException, InterruptedException
    {
        List<Model> modelList =  backEnd.getModelList();
        List<String> modelNames = new ArrayList<String>();

        for(int i =0; i<modelList.size(); i++)
        {
            String ii =modelList.get(i).getModalName();
            modelNames.add(ii);
        }


        ArrayAdapter<String> integerArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,modelNames);
        spinnerBranch.setAdapter(integerArrayAdapter);

    }
}
