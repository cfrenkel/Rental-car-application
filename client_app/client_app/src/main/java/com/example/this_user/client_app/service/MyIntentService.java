package com.example.this_user.ourproject5778_9075_4711_02.service;

import android.app.IntentService;
import android.content.Intent;

import com.example.this_user.ourproject5778_9075_4711_02.R;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        while (true)
        {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //global flag that changed when order closed
             if(ConstValue.flag) {

                Intent i = new Intent();
                i.putExtra("message", getResources().getString(R.string.messSer));
                i.setAction("closedOrder");
                MyIntentService.this.sendBroadcast(i);

              ConstValue.flag=false;
            }
        }
    }


}
