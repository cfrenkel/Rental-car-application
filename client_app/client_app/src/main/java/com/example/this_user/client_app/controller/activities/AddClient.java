package com.example.this_user.ourproject5778_9075_4711_02.controller.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.this_user.ourproject5778_9075_4711_02.R;
import com.example.this_user.ourproject5778_9075_4711_02.model.backend.BackEndInterface;
import com.example.this_user.ourproject5778_9075_4711_02.model.backend.DataSourceFactory;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.Customer;

import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

public class AddClient extends AppCompatActivity {

    final BackEndInterface backEnd = DataSourceFactory.getBackEnd();
    /**
     * email format
     */
    private static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(

            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client2);

        final EditText firstNameText = (EditText) findViewById(R.id.firstName);
        final EditText lastNameText = (EditText) findViewById(R.id.lastName);
        final EditText idText = (EditText) findViewById(R.id.id);
        //text change event - cheak the input
        idText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(idText.getText().length()>9)
                {
                    idText.setTextColor(getResources().getColor(R.color.colorHi));
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.id_must),Toast.LENGTH_SHORT).show();
                }
                else
                {
                    idText.setTextColor(Color.BLACK);
                }
            }
        });
        final EditText phoneText = (EditText) findViewById(R.id.phone);
        final EditText emailText = (EditText) findViewById(R.id.email);
        //text change event - cheak the input
        emailText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!EMAIL_ADDRESS_PATTERN.matcher(emailText.getText()).matches())
                {
                    emailText.setTextColor(getResources().getColor(R.color.colorHi));
                }
                else
                {
                    emailText.setTextColor(Color.BLACK);

                }
            }
        });
        final EditText creditText = (EditText) findViewById(R.id.credit);
        Button add = (Button) findViewById(R.id.buttonAddClient);
        /**
         * button event - add new client
         */
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = firstNameText.getText().toString();
                String lastName = lastNameText.getText().toString();
                String id = (idText.getText().toString());
                //  Long id = Long.valueOf(idText.getText().toString());
                final String phone = (phoneText.getText().toString());
                String email = emailText.getText().toString();
                String credit = (creditText.getText().toString());

                if(firstName==null)
                    Toast.makeText(getApplicationContext(), "EEROR", Toast.LENGTH_SHORT);

                Customer c = new Customer(lastName, firstName ,id,phone,email,credit);

                if (!firstNameText.getText().toString().matches("") && !lastNameText.getText().toString().matches("")
                        && !emailText.getText().toString().matches("")
                        &&  !id.matches("") || phone.matches("") && !credit.matches("") &&
                        idText.getText().toString().length()>=9
                        && !EMAIL_ADDRESS_PATTERN.matcher(emailText.getText()).matches())
                {
                    try {
                        if (!backEnd.customerExist(c)) {
                            backEnd.addNewCustomer(c);

                            /*
                            finish adding, what to do now?
                             */
                            AlertDialog mess = new AlertDialog.Builder(AddClient.this).create();
                            mess.setIcon((R.drawable.ic_action_smile));
                            mess.setTitle(R.string.welcome);
                            mess.setMessage(getResources().getString(R.string.client_succ));
                            //return home
                            mess.setButton(AlertDialog.BUTTON_NEGATIVE, getResources().getString(R.string.home), new
                                    DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            finish();
                                        }
                                    });
                            //add more
                            mess.setButton(AlertDialog.BUTTON_POSITIVE, getResources().getString(R.string.add_more), new
                                    DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            lastNameText.setText("");
                                            firstNameText.setText("");
                                            idText.setText("");
                                            emailText.setText("");
                                            phoneText.setText("");
                                            creditText.setText("");
                                            firstNameText.requestFocus();
                                            dialog.cancel();
                                        }
                                    });
                            mess.show();
                        } else
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.client_exis), Toast.LENGTH_SHORT).show();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.inputIn), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
