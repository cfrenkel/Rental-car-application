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
import com.example.this_user.ourproject5778_4711_9075.model.entities.Car;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CarListActivity extends AppCompatActivity {

    final BackEndInterface backEnd = DataSourceFactory.getBackEnd();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        showList();
    }

    /**
     *   show list wih adapter
     */
    private void showList() {
        List<Car> carList = null;
        try {
            carList = backEnd.getCarList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (carList != null) {
            ListView listView = new ListView(this);

            final List<Car> finalCarList = carList;
            ArrayAdapter<Car> adapter = new ArrayAdapter<Car>
                    (this, R.layout.car, finalCarList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = View.inflate(CarListActivity.this,
                                R.layout.car, null);
                    }
                    //casting and put
                    TextView nameText = (TextView) convertView.findViewById(R.id.carNum);
                    TextView modelText = (TextView) convertView.findViewById(R.id.carModel);
                    nameText.setText(((Long) finalCarList.get(position).getCarNumber()).toString());
                    modelText.setText(finalCarList.get(position).getModel());
                    return convertView;
                }
            };
            listView.setAdapter(adapter);
            this.setContentView(listView);
        }
    }
};

