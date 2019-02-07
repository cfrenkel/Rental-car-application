package com.example.this_user.ourproject5778_9075_4711_02.controller.fragments.order;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.this_user.ourproject5778_9075_4711_02.controller.fragments.PagesFragment;
import com.example.this_user.ourproject5778_9075_4711_02.controller.fragments.order.AvailableCarFragment;
import com.example.this_user.ourproject5778_9075_4711_02.controller.fragments.order.BranchDeatailsFragment;
import com.example.this_user.ourproject5778_9075_4711_02.controller.listTools.ListViewFilter;
import com.example.this_user.ourproject5778_9075_4711_02.R;
import com.example.this_user.ourproject5778_9075_4711_02.model.backend.BackEndInterface;
import com.example.this_user.ourproject5778_9075_4711_02.model.backend.DataSourceFactory;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.Branch;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.ModelWithCount;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class BranchListFragment extends Fragment {

    final BackEndInterface backEnd = DataSourceFactory.getBackEnd();
    ListView lv;
    SearchView search_view;
    ArrayAdapter<Branch> adapter;
    ListViewFilter listViewFilter;
    public BranchListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_branch_list, container, false);
        lv = (ListView)view.findViewById(R.id.listviewb);
        Toast.makeText(getContext(), getResources().getString(R.string.thebestcar)+ ":\t"+ statics(), Toast.LENGTH_LONG).show();


        showList();
        search_view = (SearchView) view.findViewById(R.id.search_view);


        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query)
            {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //blank frgment
                Fragment fragment = new PagesFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout2, fragment).commit();

                Fragment fragment1 = new PagesFragment();
                FragmentManager fragmentManager1 = getFragmentManager();
                fragmentManager1.beginTransaction()
                        .replace(R.id.frameLayout1, fragment1).commit();

                //search edit text is "" - put the full list
                if(newText.matches("")) {
                    adapter.getFilter().filter("");

                }
                else {
                    adapter.getFilter().filter(newText);
                }
                    adapter.notifyDataSetChanged();

                return true;
            }




        });




        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Branch b = (Branch) lv.getItemAtPosition(position);

                //branch deatailes fragment in part 2
                Fragment fragment = new BranchDeatailsFragment(b);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout2, fragment).commit();

                //availibale car fragment in part 3
                Fragment fragment1 = new AvailableCarFragment(b);
                FragmentManager fragmentManager1 = getFragmentManager();
                fragmentManager1.beginTransaction()
                        .replace(R.id.frameLayout1, fragment1).commit();


            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    /**
     * show branch list
     */
    void showList()
    {
        List<Branch> list=null;
        try {
            list = backEnd.getBranchList();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final List<Branch> finalBrunchList = list;
        adapter = new ArrayAdapter<Branch>
                (getActivity(), R.layout.brunch, finalBrunchList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = View.inflate(getActivity(),
                            R.layout.brunch, null);
                }
                //casting and put
                TextView numText = (TextView) convertView.findViewById(R.id.brunchNum);
                TextView addText = (TextView) convertView.findViewById(R.id.brunchAdd);
                numText.setText(((Integer) finalBrunchList.get(position).getBranchNum()).toString());
                addText.setText(finalBrunchList.get(position).getAddress());
                return convertView;
            }

            @Override
            public Filter getFilter() {
                if(listViewFilter==null)
                    listViewFilter=new ListViewFilter(adapter,(finalBrunchList));
                return listViewFilter;
                //return null;
            }


        };
        lv.setAdapter(adapter);

    }

    /**
     * return the best poupular car
     * @return
     */
    private String statics()
    {
        List<Order> orderList = null;
        try {
            orderList = backEnd.getOrder();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<ModelWithCount> modelWithCountList = new ArrayList<ModelWithCount>();


        List<String> models = new ArrayList<>();
        for (Order o : orderList)
        {
            models.add(backEnd.returnCarByNum(o.getNumberCar()).getModel());
        }
        boolean flag = true;
        for (String m: models) {
            for (ModelWithCount mm : modelWithCountList) {
                if(mm.getModel().matches(m)) {
                    mm.setCount(mm.getCount() + 1);
                    flag = true;
                }

            }
            if(flag) {
                modelWithCountList.add(new ModelWithCount(m, 1));
                flag = false;
            }

        }

        int max = 0;
        String model = "";
        for (ModelWithCount m : modelWithCountList) {
            if(m.getCount()>max)
            {
                max = m.getCount();
                model = m.getModel();
            }

        }

        return model;
    }


}
