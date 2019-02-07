package com.example.this_user.ourproject5778_4711_9075.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.this_user.ourproject5778_4711_9075.R;
import com.example.this_user.ourproject5778_4711_9075.model.backend.BackEndInterface;
import com.example.this_user.ourproject5778_4711_9075.model.backend.DataSourceFactory;
import com.example.this_user.ourproject5778_4711_9075.model.entities.Branch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class BrunchListActivity extends AppCompatActivity {
    final BackEndInterface backEnd = DataSourceFactory.getBackEnd();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brunch_list);

        showList();
    }

    /**
     * put the values with adapter
     */
    private void showList()
    {
        List<Branch> brunchList = new ArrayList<Branch>();
        try {
            brunchList = backEnd.getBranchList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(brunchList!=null) {
            ListView listView = new ListView(this);

            final List<Branch> finalBrunchList = brunchList;
            ArrayAdapter<Branch> adapter = new ArrayAdapter<Branch>
                    (this, R.layout.brunch, finalBrunchList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = View.inflate(BrunchListActivity.this,
                                R.layout.brunch, null);
                    }
                    //casting and put
                    TextView numText = (TextView) convertView.findViewById(R.id.brunchNum);
                    TextView addText = (TextView) convertView.findViewById(R.id.brunchAdd);
                    TextView parkText = (TextView) convertView.findViewById(R.id.parkinkNum);
                    numText.setText(((Integer) finalBrunchList.get(position).getBranchNum()).toString());
                    addText.setText(finalBrunchList.get(position).getAddress());
                    parkText.setText((finalBrunchList.get(position).getParkingSpacesNum()).toString());
                    return convertView;
                }
            };
            listView.setAdapter(adapter);
            this.setContentView(listView);
        }
    }
    };


