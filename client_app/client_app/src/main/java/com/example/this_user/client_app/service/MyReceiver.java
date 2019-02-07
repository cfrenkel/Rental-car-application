package com.example.this_user.ourproject5778_9075_4711_02.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {



        if (intent.getAction().matches("android.intent.action.TIME_TICK"))
            Toast.makeText(context, "TIME_TICK", Toast.LENGTH_LONG).show();
        // CharSequence intentData = intent.getCharSequenceExtra(String.valueOf("message"));
         else if (intent.getAction().matches("closedOrder"))
        Toast.makeText( context, intent.getCharSequenceExtra(String.valueOf("message")), Toast.LENGTH_LONG).show();


        /*
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        throw new UnsupportedOperationException("Not yet implemented");*/
    }
}
