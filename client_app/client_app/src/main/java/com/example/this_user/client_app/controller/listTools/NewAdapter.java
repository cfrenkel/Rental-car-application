package com.example.this_user.ourproject5778_9075_4711_02.controller.listTools;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.this_user.ourproject5778_9075_4711_02.R;
import com.example.this_user.ourproject5778_9075_4711_02.model.backend.BackEndInterface;
import com.example.this_user.ourproject5778_9075_4711_02.model.backend.DataSourceFactory;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.Car;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.Price;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by This_user on 28/05/2018.
 */

public class NewAdapter extends BaseExpandableListAdapter {

    public List<Car> groupItem;
    List<Object> Childtem;
    List<String> tempChild;
    public LayoutInflater minflater;
    public Activity activity;
    BackEndInterface backEndInterface = DataSourceFactory.getBackEnd();

        public NewAdapter(List<Car> grList, List<Object> childItem, Activity minflater) {
        groupItem = grList;
        this.Childtem = childItem;
        this.minflater = LayoutInflater.from(minflater);
    }

    public void setInflater(LayoutInflater mInflater, Activity act) {
        this.minflater = mInflater;
        activity = act;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }


    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        tempChild = (ArrayList<String>) Childtem.get(groupPosition);
        if (convertView == null) {
            convertView = minflater.inflate(R.layout.child_row, null);
        }

        TextView text = (TextView) convertView.findViewById(R.id.carde);
        text.setText(tempChild.get(childPosition));

        return convertView;

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return ((ArrayList<String>) Childtem.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public int getGroupCount() {
        return groupItem.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
       if (convertView == null) {
           convertView = minflater.inflate(R.layout.car, null);

       }
        TextView nameText = (TextView) convertView.findViewById(R.id.carNum);
        TextView modelText = (TextView) convertView.findViewById(R.id.carModel);
        nameText.setText(((Long) groupItem.get(groupPosition).getCarNumber()).toString());
        modelText.setText(groupItem.get(groupPosition).getModel());
        TextView priecText = (TextView) convertView.findViewById(R.id.price) ;
        nameText.setText(((Long) groupItem.get(groupPosition).getCarNumber()).toString());
        modelText.setText(groupItem.get(groupPosition).getModel());

        float price = 0;
        List<Price> prices=null;
        try {
            prices = backEndInterface.getPriceList();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Price pp : prices) {
            if(pp.getNumberCar() == groupItem.get(groupPosition).getCarNumber()){
                price = pp.getPrice();
                break;

            }}
        priecText.setText(String.valueOf( price));

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
