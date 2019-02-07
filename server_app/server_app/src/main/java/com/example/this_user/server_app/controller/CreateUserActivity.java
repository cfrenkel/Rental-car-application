package com.example.this_user.ourproject5778_4711_9075.controller;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ForwardingListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.this_user.ourproject5778_4711_9075.R;
import com.example.this_user.ourproject5778_4711_9075.model.backend.BackEndInterface;
import com.example.this_user.ourproject5778_4711_9075.model.backend.DataSourceFactory;
import com.example.this_user.ourproject5778_4711_9075.model.entities.IdentificationQuestions;
import com.example.this_user.ourproject5778_4711_9075.model.entities.User;

import java.util.concurrent.ExecutionException;

public class CreateUserActivity extends AppCompatActivity {

    final BackEndInterface backEnd = DataSourceFactory.getBackEnd();
    private EditText editTextName;
    private EditText editTextPassword;
    private EditText editTextPassReturn;
    private TextView textView;
    private Spinner spinner;
    private EditText editTextAns;
    private Button buttonadd;
    static boolean capital=false;
    static boolean small=false;
    static boolean size = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        findViews();

        /**
         * button event - add new user
         */
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addButtonDo();
            }
        });
        spinner.setAdapter(new ArrayAdapter<IdentificationQuestions>(this, android.R.layout.simple_spinner_item, IdentificationQuestions.values()));
        spinner.setContentDescription(getResources().getString(R.string.choose_your_Identification_Questions_));
        spinner.setPrompt(getResources().getString(R.string.choose_your_Identification_Questions_));
        editTextPassReturn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    if(!editTextPassword.getText().toString().equals(editTextPassReturn.getText().toString())) {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.the_return_password_not_match___), Toast.LENGTH_SHORT).show();
                        editTextPassReturn.setTextColor(getResources().getColor(R.color.colorHi));

                    }
                    else
                        editTextPassReturn.setTextColor(Color.BLACK);

                }
            }
        });
        editTextPassReturn.setEnabled(false);

        /**
         * text change event - cheak the input
         */
        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String name = (editTextName.getText()).toString();
                try {
                    if(backEnd.userExist(name)!=null) {
                        editTextName.setTextColor(getResources().getColor(R.color.colorHi));
                        Toast.makeText(getApplicationContext(),  getResources().getString(R.string.user_name_already_exist), Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        editTextName.setTextColor(Color.BLACK);
                    }

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        /**
         * text change event - cheak the input
         */
        editTextPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if(hasFocus)
                {
                    editTextPassReturn.setText("");
                    editTextPassReturn.setEnabled(false);
                }
               else {
                    if (editTextPassword.getText().toString().length() > 1) {
                        if (capital == false) {
                            Toast.makeText(getApplicationContext(), R.string.Password_must_least_one_capital_letter, Toast.LENGTH_SHORT).show();
                            //editTextPassword.setTextColor(getResources().getColor(R.color.colorHi));
                        }
                        if (small == false) {
                            Toast.makeText(getApplicationContext(), R.string.Password_must_least_one_capital_letter, Toast.LENGTH_SHORT).show();
                            //editTextPassword.setTextColor(getResources().getColor(R.color.colorHi));

                        }
                        if (size == false) {
                            Toast.makeText(getApplicationContext(), R.string.Password_must_contain_between, Toast.LENGTH_SHORT).show();
                            // editTextPassword.setTextColor(getResources().getColor(R.color.colorHi));

                        } else if (size && capital && small)
                        {
                            editTextPassReturn.setEnabled(true);
                            editTextPassword.setTextColor(Color.BLACK);
                        }
                    }
                }
            }
        });
        /**
         * text change event - cheak the input
         */
        editTextPassword.addTextChangedListener(new TextWatcher()
        {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s) {


                    String pass = (editTextPassword.getText()).toString();
                if(s.length()>=1) {
                    if (s.charAt(s.length() - 1) >= 'A' && s.charAt(s.length() - 1) <= 'Z')
                        capital = true;

                    else if (s.charAt(s.length() - 1) >= 'a' && s.charAt(s.length() - 1) <= 'z')
                        small = true;
                }
                    if (s.length() >=6 && s.length() <= 8)
                        size = true;
                    if(s.length() <6 || s.length() > 8)
                        size = false;

                   if (editTextPassword.getText().toString().length()>1) {
                    if (capital == false) {
                       // Toast.makeText(getApplicationContext(), "Password must  least one capital letter", Toast.LENGTH_SHORT).show();
                        editTextPassword.setTextColor(getResources().getColor(R.color.colorHi));
                    }
                    if (small == false) {
                      //  Toast.makeText(getApplicationContext(), "Password must  least one small letter", Toast.LENGTH_SHORT).show();
                        editTextPassword.setTextColor(getResources().getColor(R.color.colorHi));

                    }
                    if (size==false) {
                      //  Toast.makeText(getApplicationContext(), "Password must contain between 6-8 characters", Toast.LENGTH_SHORT).show();
                        editTextPassword.setTextColor(getResources().getColor(R.color.colorHi));

                    } else if (size && small && capital)
                        editTextPassword.setTextColor(Color.BLACK);

                }
            }
        });


    }

    /**
     * add user
     */
    void addButtonDo()
    {
        IdentificationQuestions IQ = ((IdentificationQuestions) spinner.getSelectedItem());
        String ans = (String) editTextAns.getText().toString();
        String userName = (String) editTextName.getText().toString();
        String pass = (String) editTextPassword.getText().toString();

        //cheak the correct of the input

            if(editTextName.getCurrentTextColor()!=getResources().getColor(R.color.colorHi) &&
                    editTextPassReturn.getCurrentTextColor()!=getResources().getColor(R.color.colorHi)
                    && !editTextName.getText().toString().matches("") && !editTextPassReturn.getText().toString().matches("")
                    && !editTextPassReturn.getText().toString().matches("") && !editTextAns.getText().toString().matches("")
                    && editTextName.getCurrentTextColor()!=getResources().getColor(R.color.colorHi)
                    && editTextPassReturn.getCurrentTextColor()!=getResources().getColor(R.color.colorHi)){
                User u = new User(userName, pass, IQ, ans);
                try {
                    backEnd.addNewUser(u); //add....
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //mess of doing....
                AlertDialog mess = new AlertDialog.Builder(CreateUserActivity.this).create();

                mess.setIcon((R.drawable.ic_action_smile));

                mess.setTitle(R.string.welcome);
                mess.setMessage(getResources().getString(R.string.user_successfully_added));
                mess.setButton(AlertDialog.BUTTON_NEGATIVE,getResources().getString( R.string.OK), new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                               /* Intent i = new Intent(CreateUserActivity.this, MainActivity.class);
                                startActivity(i);
                                i.putExtra(editTextName.getText().toString(),true);*/
                                finish();
                            }
                        });
                mess.show();
            }

        else
        Toast.makeText(getApplicationContext(), R.string.inputIn, Toast.LENGTH_SHORT).show();
    }
    private void findViews() {
        editTextName = (EditText)findViewById( R.id.editTextName );
        editTextPassword = (EditText)findViewById( R.id.editTextPassword );
        editTextPassReturn = (EditText)findViewById( R.id.editTextPassReturn );
        textView = (TextView)findViewById( R.id.textView );
        spinner = (Spinner)findViewById( R.id.spinner );
        editTextAns = (EditText)findViewById( R.id.editTextAns );
        buttonadd = (Button)findViewById( R.id.buttonadd );
    }

    };


