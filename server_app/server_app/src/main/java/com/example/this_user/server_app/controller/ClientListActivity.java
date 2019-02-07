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
import com.example.this_user.ourproject5778_4711_9075.model.entities.Customer;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ClientListActivity extends AppCompatActivity {
    final BackEndInterface backEnd = DataSourceFactory.getBackEnd();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        showList();
    }

    /*
    show the full list of the clients with adapter tool
     */
    private void showList() {
        List<Customer> customerList = null;
        try {
            customerList = backEnd.getCustomerList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(customerList!=null) {
            ListView listView = new ListView(this);

            final List<Customer> finalCustomerList = customerList;
            ArrayAdapter<Customer> adapter = new ArrayAdapter<Customer>
                    (this, R.layout.client, finalCustomerList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = View.inflate(ClientListActivity.this,
                                R.layout.client, null);
                    }

                    //casting and put
                    TextView lastName = (TextView) convertView.findViewById(R.id.lastName);
                    TextView firstName = (TextView) convertView.findViewById(R.id.firstName);
                    TextView id = (TextView) convertView.findViewById(R.id.id);
                    TextView phoneNum = (TextView) convertView.findViewById(R.id.phoneNum);
                    TextView email = (TextView) convertView.findViewById(R.id.email);
                    lastName.setText(finalCustomerList.get(position).getLastName());
                    firstName.setText(finalCustomerList.get(position).getFirstName());
                    id.setText(((String) finalCustomerList.get(position).getId()).toString());
                    phoneNum.setText(((String) finalCustomerList.get(position).getPhoneNumber()).toString());
                    email.setText(finalCustomerList.get(position).getEmailAddress());
                    return convertView;
                }
            };
            listView.setAdapter(adapter);
            this.setContentView(listView);
        }
    }
}
