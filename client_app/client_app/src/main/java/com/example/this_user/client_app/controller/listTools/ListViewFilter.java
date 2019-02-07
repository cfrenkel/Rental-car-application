package com.example.this_user.ourproject5778_9075_4711_02.controller.listTools;

import android.widget.ArrayAdapter;
import android.widget.Filter;

import com.example.this_user.ourproject5778_9075_4711_02.model.backend.BackEndInterface;
import com.example.this_user.ourproject5778_9075_4711_02.model.backend.DataSourceFactory;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.Branch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * Created by ezra on 12/1/2015.
 */
public class ListViewFilter extends Filter {
    final BackEndInterface backEnd = DataSourceFactory.getBackEnd();
    private List<Branch> list;
    public ArrayAdapter<Branch> myAdapter;

    public ListViewFilter(ArrayAdapter<Branch> myAdapter, List<Branch> objects) {
        list = objects;
        this.myAdapter=myAdapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();


        // We implement here the filter logic
        if (constraint == null || constraint.length() == 0) {
            // No filter implemented we return all the list
            List<Branch> branchList = null;
            try {
                branchList = backEnd.getBranchList();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            results.values = branchList;
            results.count = branchList.size();
        }
        else {
            // We perform filtering operation
            List<Branch> tempList = new ArrayList<Branch>();

            try {
                for (Branch p : backEnd.getBranchList()) {
                    String s;
                    s = p.getAddress().substring(p.getAddress().lastIndexOf(" ")+1);
                    if (s.toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        tempList.add(p);
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            results.values = tempList;
            results.count = tempList.size();

        }
        return results;

    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

            myAdapter.clear();
            for(Branch item : (List<Branch>) results.values)
             list.add(item);
            myAdapter.notifyDataSetChanged();

        }


    }






