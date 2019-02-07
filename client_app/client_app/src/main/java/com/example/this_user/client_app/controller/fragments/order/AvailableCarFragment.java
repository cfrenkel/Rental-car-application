package com.example.this_user.ourproject5778_9075_4711_02.controller.fragments.order;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.this_user.ourproject5778_9075_4711_02.R;
import com.example.this_user.ourproject5778_9075_4711_02.model.backend.BackEndInterface;
import com.example.this_user.ourproject5778_9075_4711_02.model.backend.DataSourceFactory;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.Branch;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.Car;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.Customer;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.Model;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.ModelWithCount;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.Order;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.Price;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class AvailableCarFragment extends Fragment {

    final BackEndInterface backEnd = DataSourceFactory.getBackEnd();
    Branch branch = null;
    ListView listView;
    View view;
    int posi;

    public AvailableCarFragment(Branch b) {
        branch = b;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


         view = inflater.inflate(R.layout.fragment_available_car, container, false);
         showlist();

        //event of click on item in the list - order car
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            public void onItemClick(AdapterView<?> parent, View view,
                                                                    int position, long id) {

                                                posi = position;
                                                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                                final DatePicker picker = new DatePicker(getActivity());
                                                picker.setCalendarViewShown(false);

                                                builder.setTitle(getResources().getString(R.string.Choose_a_date_to_return));
                                                builder.setView(picker);
                                                builder.setNegativeButton("Cancel", new
                                                        DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int id) {
                                                                dialog.cancel();
                                                            }
                                                        });
                                                builder.setPositiveButton("Set",new
                                                        DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int id) {

                                                                Car c = (Car) listView.getItemAtPosition(posi);
                                                                String email = "";

                                                                //take the user detailes from the sharedPreferences
                                                                SharedPreferences sharedPreferences;
                                                                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                                                                if (sharedPreferences.contains("name"))
                                                                    email = sharedPreferences.getString("name", null);

                                                                String name = returnNameByEmail(email);

                                                                //cheak if correct date
                                                                DatePicker d = picker;
                                                                SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");

                                                                Date d1 = new java.sql.Date(System.currentTimeMillis());


                                                                int dd = d.getDayOfMonth();
                                                                int mm = d.getMonth();

                                                                Date today = new Date();
                                                                Calendar cal = Calendar.getInstance();
                                                                cal.setTime(today); // don't forget this if date is arbitrary
                                                                int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1 being Sunday
                                                                int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
                                                                int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
                                                                int month = cal.get(Calendar.MONTH); // 0 being January
                                                                int year = cal.get(Calendar.YEAR);

                                                                if((dd<dayOfMonth && mm==month) || mm <month)
                                                                {
                                                                    Toast.makeText(getContext(), getResources().getString(R.string.errorday), Toast.LENGTH_LONG).show();
                                                                }
                                                                else
                                                                {
                                                                    Order o = new Order(name, true, c.getCarNumber(), new java.sql.Date(System.currentTimeMillis()), new  Date(d.getYear(), d.getMonth(), d.getDayOfMonth()), c.getKilometers(), 0, false, 0, 0);
                                                                    backEnd.addOrder(o);
                                                                    Toast.makeText(getActivity(), getResources().getString(R.string.addOrder), Toast.LENGTH_LONG).show();
                                                                    //show the list after add order
                                                                    showlist();
                                                                }

                                                            }
                                                        });
                                                builder.show();

                                            }
                                        });

                // Inflate the layout for this fragment
        return view;
    }

    /**
     * return the client name by his email
     * @param email
     * @return
     */
    private String returnNameByEmail(String email) {
        List<Customer> customerList = null;
        try {
            customerList = backEnd.getCustomerList();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String name = "";
        for (Customer c1 : customerList) {
            if (c1.getEmailAddress().matches(email))
                name = c1.getFirstName();
        }
        return name;

    }

    /**
     * show the list by adapter
     */
    private void showlist() {

        List<Car> carList = backEnd.getAvailableCarForBranch(branch);

        if (carList != null) {
            listView = (ListView) view.findViewById(R.id.lvcar);

            final List<Car> finalCarList = carList;
            ArrayAdapter<Car> adapter = new ArrayAdapter<Car>
                    (getActivity(), R.layout.car, finalCarList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = View.inflate(getActivity(),
                                R.layout.car, null);
                    }
                    //casting and put
                    TextView nameText = (TextView) convertView.findViewById(R.id.carNum);
                    TextView modelText = (TextView) convertView.findViewById(R.id.carModel);
                    TextView priecText = (TextView) convertView.findViewById(R.id.price) ;
                    nameText.setText(((Long) finalCarList.get(position).getCarNumber()).toString());
                    modelText.setText(finalCarList.get(position).getModel());

                    float price = 0;
                    List<Price> prices=null;
                    try {
                         prices = backEnd.getPriceList();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (Price pp : prices) {
                        if(pp.getNumberCar() == finalCarList.get(position).getCarNumber()){
                            price = pp.getPrice();
                            break;
                        
                    }}
                    priecText.setText(String.valueOf( price));
                    return convertView;
                }
            };
            listView.setAdapter(adapter);
        }
    }


}
