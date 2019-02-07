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
import com.example.this_user.ourproject5778_4711_9075.model.entities.Customer;
import com.example.this_user.ourproject5778_4711_9075.model.entities.Model;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelListActivity extends AppCompatActivity {

    final BackEndInterface backEnd = DataSourceFactory.getBackEnd();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_list);

        showList();
    }

    /*
   show model lisr with adapter tool
     */
    private void showList() {
        List<Model> modelList = null;

        try {
            modelList = backEnd.getModelList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(modelList!=null) {
            ListView listView = new ListView(this);

            final List<Model> finalModelList = modelList;
            ArrayAdapter<Model> adapter = new ArrayAdapter<Model>
                    (this, R.layout.model, finalModelList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = View.inflate(ModelListActivity.this,
                                R.layout.model, null);
                    }
                    //convert and put
                    TextView modelCode = (TextView) convertView.findViewById(R.id.modelCode);
                    TextView companyName = (TextView) convertView.findViewById(R.id.companyName);
                    TextView modelName = (TextView) convertView.findViewById(R.id.modelName);
                    TextView capa = (TextView) convertView.findViewById(R.id.engineCapacity);
                    //TextView year = (TextView) convertView.findViewById(R.id.year);
                    modelCode.setText((finalModelList.get(position).getModelCode()).toString());
                    companyName.setText(finalModelList.get(position).getCompanyName());
                    modelName.setText((finalModelList.get(position).getModalName()));
                    capa.setText((finalModelList.get(position).getEngineCapacity()).toString());
                   // year.setText(finalModelList.get(position).getProductionYear());
                    return convertView;
                }
            };
            listView.setAdapter(adapter);
            this.setContentView(listView);
        }

    }

}
