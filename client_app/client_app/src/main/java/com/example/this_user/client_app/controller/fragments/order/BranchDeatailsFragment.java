package com.example.this_user.ourproject5778_9075_4711_02.controller.fragments.order;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.example.this_user.ourproject5778_9075_4711_02.R;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.Branch;

public class BranchDeatailsFragment extends Fragment {

    Branch branch = null;
    public BranchDeatailsFragment(){}


    public BranchDeatailsFragment(Branch b) {
        branch = b;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_branch_deatails, container, false);

        TextView textView_ = (TextView)view.findViewById(R.id.textView_);
        Button buttonw = (Button) view.findViewById(R.id.button_web);

        //put the full detailes of the wanted branch
        textView_.setText("    "+getResources().getString(R.string.the_branchNum) +" "+ String.valueOf(branch.getBranchNum())+ "\n" +
                "    "+ getResources().getString(R.string.the_address) +" "+ branch.getAddress() + "\n" +
                "    "+  getResources().getString(R.string.parkingggg) +" "+ String.valueOf(branch.getParkingSpacesNum())
               );

        buttonw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //init the web view in mathches google map
         AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        WebView wv = new WebView(getActivity());
         wv.getSettings().setJavaScriptEnabled(true);
         wv.loadUrl("http://www.google.com/maps/place/"+branch.getAddress()+" israel");

        alert.setView(wv);
            }
        });

        return view;
    }


}
