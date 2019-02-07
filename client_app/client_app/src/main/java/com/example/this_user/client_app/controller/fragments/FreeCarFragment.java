package com.example.this_user.ourproject5778_9075_4711_02.controller.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.this_user.ourproject5778_9075_4711_02.R;
import com.example.this_user.ourproject5778_9075_4711_02.controller.activities.MainActivity;
import com.example.this_user.ourproject5778_9075_4711_02.model.backend.BackEndInterface;
import com.example.this_user.ourproject5778_9075_4711_02.model.backend.DataSourceFactory;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.Branch;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.Car;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.Customer;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.ModelWithCount;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.Order;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.Price;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class FreeCarFragment extends Fragment {

    BackEndInterface backEnd = DataSourceFactory.getBackEnd();
    ListView listView;
    EditText kilo;
    Button close;
    TextView t;
    List<Order> orders;
    Car c;
    Spinner spinnerBranch;
    public FreeCarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.fragment_free_car, container, false);

         spinnerBranch = (Spinner) rootView.findViewById(R.id.parkb) ;
        t = (TextView) rootView.findViewById(R.id.textView2);
        try {
            showSpinnerBranch(spinnerBranch);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        orders = backEnd.getOpenPrder();
        kilo = (EditText) rootView.findViewById(R.id.kiloup);
        close = (Button) rootView.findViewById(R.id.close);
        listView = (ListView) rootView.findViewById(R.id.listView);

        showlist();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                c = (Car) listView.getItemAtPosition(position);
                kilo.setVisibility(View.VISIBLE);
                close.setVisibility(View.VISIBLE);
                t.setVisibility(View.VISIBLE);
                spinnerBranch.setVisibility(View.VISIBLE);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             if(kilo.getText().toString()=="")
                 Toast.makeText(getContext(), getResources().getString(R.string.errorKilo), Toast.LENGTH_LONG).show();
                else{
             int kiloS = Integer.valueOf(kilo.getText().toString());
                if(kiloS<c.getKilometers())
                    Toast.makeText(getContext(),  getResources().getString(R.string.errorKilo)  , Toast.LENGTH_SHORT).show();

                else {
                    //look for order to close
                    for (Order o : orders) {
                        if (o.getNumberCar() == c.getCarNumber()) {
                            if (spinnerBranch.getSelectedItem() != null) {
                                //update address of branch num o fparking
                                String add = (String) spinnerBranch.getSelectedItem();
                                try {
                                    //"replace" the address of branch (from the spinner) with num
                                    for (Branch b : backEnd.getBranchList()) {
                                        if (b.getAddress().matches(add)) {
                                            c.setBranchNumOfParking(b.getBranchNum());
                                            backEnd.UpdateCar(c);
                                        }

                                    }
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                            o.setKilometerEndValue(kiloS);
                            prices(o);

                            break;

                        }

                    }

                }
                       }

            }
        }



    );
        return rootView;
    }


    private void prices(Order o) {



        AlertDialog a = new AlertDialog.Builder(getActivity()).create();
        a.setTitle( getResources().getString(R.string.forPayment));

        List<Order> orders = null;
        try {
            orders = backEnd.getOrder();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<ModelWithCount> m = new ArrayList<ModelWithCount>();
        for (Order oo:orders) {
            //String s = backEnd.ret
        }


        a.setIcon(R.drawable.creditcard);

        a.setMessage(getResources().getString(R.string.thePrice) +" : ");
         calcPrice(o, a);
        a.setButton(AlertDialog.BUTTON_POSITIVE, getResources().getString(R.string.cencel), new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
//...
                        dialog.cancel();
                    }
                });
        final Order oo = o;
        a.setButton(AlertDialog.BUTTON_NEGATIVE, getResources().getString(R.string.OK), new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                //confirm the payment
                                Toast.makeText(getContext(),getResources().getString(R.string.call_cre)   ,  Toast.LENGTH_LONG).show();
                                loadAnimate();

                                backEnd.closeOrder(oo);
                                Toast.makeText(getContext(),getResources().getString(R.string.order_close)   ,  Toast.LENGTH_LONG).show();
                                showlist();
                                kilo.setText("");
                                kilo.setVisibility(View.INVISIBLE);
                                close.setVisibility(View.INVISIBLE);
                                t.setVisibility(View.INVISIBLE);
                                spinnerBranch.setVisibility(View.INVISIBLE);

                                dialog.cancel();
                            }

                        });

                a.show();
    }

    /**
     * calculate the total price and show in alert
     * @param o
     * @param a
     */
    private void calcPrice(Order o, AlertDialog a) {


        List<Price> getPrice = null;
        try {
            getPrice = backEnd.getPriceList();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        float pp = 1;
        for (Price p : getPrice) {
            if (p.getNumberCar() == o.getNumberCar())
            pp=p.getPrice();
        }

        float t= (float) 0.05;
        Date d1 = o.getStartRental();
        Date d2 = new java.sql.Date(System.currentTimeMillis());


        long diff = d2.getTime() - d1.getTime();
        diff =TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)/24;

        float sum = o.getKilometerEndValue()-o.getKilometerStartValue()-200*diff;
        if(sum>0)
            a.setMessage(getResources().getString(R.string.priceday) + pp +"\n" +
                    getResources().getString(R.string.numDays) + diff+"\n"+
                            getResources().getString(R.string.mufraz) + " " + String.valueOf((sum/diff)*t) + "\n"+
                    getResources().getString(R.string.thePrice) + String.valueOf(diff*pp+(sum*t)));
        else
            a.setMessage(getResources().getString(R.string.priceday) + pp +"\n" +
                    getResources().getString(R.string.numDays) + diff+"\n"+
                    getResources().getString(R.string.thePrice) + String.valueOf(diff*pp));

    }

    /**
     * show free car list
     */
    private void showlist() {

        //take email of client from shared Preferences
        String email = "";
        SharedPreferences sharedPreferences;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (sharedPreferences.contains("name"))
            email = sharedPreferences.getString("name", null);

        //"replace" the email with name
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




        //look for in the orders, orders of the client and put the cars of them in list
        List<Car> carList= new ArrayList<Car>();
        for (Order o: orders ) {

            if(o.getCustomerName().matches(name) && o.isOpenOrder())
            {
                carList.add(backEnd.returnCarByNum(o.getNumberCar()));

            }

        }

        //adapter
        if (carList != null) {
          //  listView = (ListView) rootView.findViewById(R.id.listView);

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
                    nameText.setText(((Long) finalCarList.get(position).getCarNumber()).toString());
                    modelText.setText(finalCarList.get(position).getModel());
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

    /**
     * put the valus of branch address in the spinner
     * @throws ExecutionException
     * @throws InterruptedException
     */
    void showSpinnerBranch(Spinner spinnerBranch) throws ExecutionException, InterruptedException
    {
        List<Branch> branchList =  backEnd.getBranchList();
        List<String> branchNum = new ArrayList<String>();

        for(int i =0; i<branchList.size(); i++)
        {
            String ii =branchList.get(i).getAddress();
            branchNum.add(ii);
        }

        ArrayAdapter<String> integerArrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,branchNum);
        spinnerBranch.setAdapter(integerArrayAdapter);

    }

    /**
     * visible the calling animation
     */
    private void loadAnimate() {
        final ProgressDialog p = new ProgressDialog(getActivity());
        p.show();
        p.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                    if (p.isShowing())
                        p.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
