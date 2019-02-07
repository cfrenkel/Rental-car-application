package com.example.this_user.ourproject5778_4711_9075.controller;


import android.app.ProgressDialog;
import android.content.pm.PackageInstaller;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.service.textservice.SpellCheckerService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.this_user.ourproject5778_4711_9075.model.entities.*;
import com.example.this_user.ourproject5778_4711_9075.model.backend.*;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.content.Intent;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.this_user.ourproject5778_4711_9075.R;

public class MainActivity extends AppCompatActivity {


    final BackEndInterface backEnd = DataSourceFactory.getBackEnd();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /* try {
            init();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //click events
        Button sumbitButttn = (Button) findViewById(R.id.sumbitbutton);
        sumbitButttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    submitDo();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //click events
        final Button forgotButton = (Button) findViewById(R.id.forgotbutton);
        forgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    forgotPassword();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //go to new activity
        Button createButton = (Button) findViewById(R.id.createbutton);
        createButton.setPaintFlags(createButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CreateUserActivity.class);
                startActivity(i);
            }
        });

    }

    void submitDo() throws ExecutionException, InterruptedException {
        String userName = ((EditText) findViewById(R.id.usernametext)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordtext)).getText().toString();
        User user = new User(userName , password, null, "");

        if(backEnd.userExist(user))
        {
           final ProgressDialog p = new ProgressDialog(MainActivity.this);
            p.show();


           new Thread(){
                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(2000);
                        if (p.isShowing())
                            p.dismiss();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

            Intent intent = new Intent(MainActivity.this, ManagmentActivity.class);
            startActivity(intent);

        }
        else
        {
            //error....
            String mess = getResources().getString(R.string.the_user_name_or_password_are_incorrect);
            Toast.makeText(getApplicationContext(),mess,Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *
     *cheak if the user rforgot is password
     * @throws ExecutionException
     * @throws InterruptedException
     */
    void forgotPassword() throws ExecutionException, InterruptedException {

        final String userName = ((EditText) findViewById(R.id.usernametext)).getText().toString();
        if(userName.length()==0)
        {
            //error
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.Oopss_did_not_enter_the_username),Toast.LENGTH_SHORT).show();
        }

        else if(backEnd.userExist(userName)==null)
        {
            //error
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.Oopss_the_user_name_is_incorrect),Toast.LENGTH_SHORT).show();

        }
        else {
            //return the password
            AlertDialog forgotPass = new AlertDialog.Builder(MainActivity.this).create();
            forgotPass.setTitle( getResources().getString(R.string.Identification____));
            forgotPass.setIcon(R.drawable.ic_action_forget);
            String ask = backEnd.userExist(userName).name(); //find user by user name
            String mess="";

            switch (ask)
            {
                case  "your_favorite_food":
                    mess = getResources().getString(R.string.your_favorite_food);
                    break;
                case  "your_mothers_mother":
                    mess = getResources().getString(R.string.your_mothers_mother);
                    break;

            }
            forgotPass.setMessage("\n");
            forgotPass.setMessage(mess);
            final EditText input = new EditText(MainActivity.this);
            //  String ansUser = ((EditText) input.getText()).toString();
            forgotPass.setView(input);

            forgotPass.setButton(AlertDialog.BUTTON_POSITIVE, getResources().getString(R.string.cencel), new
                    DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
//...
                            dialog.cancel();
                        }
                    });
            /**
             * give the user is indendification ask and if the ans correct return is password
             */
            forgotPass.setButton(AlertDialog.BUTTON_NEGATIVE, getResources().getString(R.string.OK), new
                    DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            String s = input.getText().toString();
                            User u = null;
                            try {
                                u = backEnd.returnUser(userName); //look for the user name in data base
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(u.getAns().equals(s))
                            {
                                //return password
                                Toast.makeText(getApplicationContext(),getResources().getString(R.string.your_password)+u.getPassword(),Toast.LENGTH_LONG).show();
                                dialog.cancel();
                            }
                            else
                            {
                                //error
                                Toast.makeText(getApplicationContext(),getResources().getString(R.string.your_ask_is_incorrect),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

            forgotPass.show();
        }
    }

    /**
     * init the list
     * @throws ExecutionException
     * @throws InterruptedException
     */
    void init() throws ExecutionException, InterruptedException{
        Branch branch = new Branch(
                "beit hadfus", "20", 11);
        Branch branch1 = new Branch(
                "chazon ish", "30", 22);
        Model model = new Model("123", "subaro", "subaro station", "4550", GearBox.automatic, "7", 1920);
        Car car = new Car(11, "subaro station", 500, 123654789);
        Car car1 = new Car(11, "subaro station", 500, 24681012);
        Car car2 = new Car(11, "subaro station", 500, 74258611);
        Car car3 = new Car(11, "subaro station", 500, 987654312);
        Car car4 = new Car(11, "subaro station", 500, 19738264);
        Customer customer = new Customer("cohen", "chaim", "123452", "0533156320", "412875@gmail.com", "5486663");

        User user = new User("avicohen", "852852", IdentificationQuestions.your_favorite_food, "bamba");
        User user1 = new User("yossilevi", "741741", IdentificationQuestions.your_favorite_food, "bread");
        User user2 = new User("moishirot", "963963", IdentificationQuestions.your_mothers_mother, "chana");


            backEnd.addBrunch(branch);
            backEnd.addBrunch(branch1);
            backEnd.addCar(car);
            backEnd.addCar(car1);
            backEnd.addCar(car2);
            backEnd.addCar(car3);
            backEnd.addCar(car4);
            backEnd.addModel(model);
            backEnd.addNewCustomer(customer);
            backEnd.addNewUser(user);
            backEnd.addNewUser(user1);
            backEnd.addNewUser(user2);
            //backEnd.addNewCustomer(customer);


    }

    /**
     * loading the animation
     * @param spinner
     */
    public void loadingAnimation(ProgressBar spinner)
    {
        spinner.setVisibility(View.VISIBLE);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        spinner.setVisibility(View.INVISIBLE);
        return;
    }
}

