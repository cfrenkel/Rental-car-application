package com.example.this_user.ourproject5778_9075_4711_02.controller.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.this_user.ourproject5778_9075_4711_02.R;

public class DeatailesFragment extends Fragment {

    public DeatailesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_deatailes, container, false);

        //send email
        Button sendEmail = (Button) rootView.findViewById(R.id.email_da);
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"chaimtovim26@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                i.putExtra(Intent.EXTRA_TEXT, "body of email");
                startActivity(Intent.createChooser(i, "Send mail..."));

            }
        });

        //call
        Button call = (Button) rootView.findViewById(R.id.phone_da);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "0504141426", null)));

            }
        });

        //link to web page
        TextView t = (TextView) rootView.findViewById(R.id.web_de);
        t.setMovementMethod(LinkMovementMethod.getInstance());

        //link to google map
        TextView t1 = (TextView) rootView.findViewById(R.id.address_de);
        t1.setMovementMethod(LinkMovementMethod.getInstance());


        return rootView;

    }
}
