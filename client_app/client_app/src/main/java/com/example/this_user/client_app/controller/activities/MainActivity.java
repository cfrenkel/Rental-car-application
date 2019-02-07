package com.example.this_user.ourproject5778_9075_4711_02.controller.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.this_user.ourproject5778_9075_4711_02.R;
import com.example.this_user.ourproject5778_9075_4711_02.model.backend.BackEndInterface;
import com.example.this_user.ourproject5778_9075_4711_02.model.backend.DataSourceFactory;
import com.example.this_user.ourproject5778_9075_4711_02.service.MyIntentService;

import java.util.Date;

public class MainActivity extends Activity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static String nameS;
    public static String passS;


    BackEndInterface backEnd = DataSourceFactory.getBackEnd();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(MainActivity.this, OpenViewActivity.class);
        startActivity(i);
        //start service
        startService(new Intent(getBaseContext(), MyIntentService.class));

        final EditText name = (EditText) findViewById(R.id.usernametext);
        final EditText pass = (EditText) findViewById(R.id.passwordtext);
        nameS = name.getText().toString();
        passS = pass.getText().toString();

        //read the user and password from the share preferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(sharedPreferences.contains("name"))
            name.setText(sharedPreferences.getString("name",null));
        if(sharedPreferences.contains("pass"))
            pass.setText(sharedPreferences.getString("pass",null));
        Button submit = (Button) findViewById(R.id.sumbitbutton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString();
                String pass1 = pass.getText().toString();
                if(backEnd.customerExist(name1, pass1))
                {
                    //save the user and password from the share preferences
                    sharedPreferences = PreferenceManager .getDefaultSharedPreferences(MainActivity.this);
                    editor = sharedPreferences.edit();
                    editor.putString("name", name1);
                    editor.putString("pass", pass1);
                    editor.commit();

                    loadAnimate();

                    Intent i = new Intent(MainActivity.this,  NavigationActivity.class);
                    startActivity(i);

                }
                else //user not exist
                {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.the_user_name_or_password_are_incorrect), Toast.LENGTH_SHORT).show();

                }
            }
        });

        Button create = (Button) findViewById(R.id.createbutton);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddClient.class);
                startActivity(i);
            }
        });


        Button play = (Button) findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(i);
                //put a video
            }
        });
    }

    /**
     * visible the loading animation
     */
    private void loadAnimate() {
        final ProgressDialog p = new ProgressDialog(MainActivity.this);
        p.show();
        p.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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

    }
}
