package com.example.this_user.ourproject5778_9075_4711_02.model.backend;

import android.os.AsyncTask;
import android.util.Log;

import com.example.this_user.ourproject5778_9075_4711_02.model.entities.*;
import com.example.this_user.ourproject5778_9075_4711_02.service.ConstValue;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

/**
 * Created by This_user on 01/05/2018.
 */

public class BackEndMySQL implements BackEndInterface {

    BackEndMySQL() {
    }

    private boolean updateFlag = false;
    private String WEB_URL = "http://cfrenkel.vlab.jct.ac.il/Academy/";

    @Override
    public boolean customerExist(Customer c) throws ExecutionException, InterruptedException {
        List<Customer> customers = null;
        try {
            customers = getCustomerList();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == c.getId())
                return true;
        }
        return false;
    }

    @Override
    public boolean customerExist(String name, String paswword) {
        List<Customer> customers = null;
        try {
            customers = getCustomerList();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getEmailAddress().matches(name) && customers.get(i).getId().matches(paswword))
                return true;
        }
        return false;
    }

    @Override
    public void UpdateCar(Car c) {

        new AsyncTask<Car, Void, Void>() {
            @Override
            protected Void doInBackground(Car... params) {
                try {
                    String result = null;
                    result = PHPtools.POST(WEB_URL + "deleteCar.php", ConvertToContext.CarToContentValues(params[0]));


                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(c);

        addCar(c);

    }

    @Override
    public boolean carExist(Car c) throws ExecutionException, InterruptedException {
        /* return
                (new AsyncTask<Car, Void, Boolean>() {
                    @Override
                    protected Boolean doInBackground(Car... params) {*/
        List<Car> cars = null;
        try {
            cars = getCarList();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getCarNumber() == c.getCarNumber())
                return true;
        }
        return false;
                   /* }
                }.execute(c)).get();*/
    }

    @Override
    public boolean modelExist(Model m) throws ExecutionException, InterruptedException {
     /*   return
                (new AsyncTask<Model, Void, Boolean>() {
                    @Override
                    protected Boolean doInBackground(Model... params) {*/
        try {
            if (getModelList().contains(m))
                return true;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
             /*       }
                }.execute(m)).get();*/
    }

    @Override
    public boolean branchExist(Branch b) throws ExecutionException, InterruptedException {
       /* return
                (new AsyncTask<Branch, Void, Boolean>() {
                    @Override
                    protected Boolean doInBackground(Branch... params) {*/
        List<Branch> branchList = null;
        try {
            branchList = getBranchList();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < branchList.size(); i++) {
            if (branchList.get(i).getAddress().matches(b.getAddress())
                    && branchList.get(i).getParkingSpacesNum().matches(b.getParkingSpacesNum()))
                return true;
        }

        return false;
                 /*   }
                }.execute(b)).get();*/
    }

    @Override
    public User returnUser(String name) throws ExecutionException, InterruptedException {
        return new AsyncTask<String, Void, User>() {
            @Override
            protected User doInBackground(String... params) {
                List<User> u = null;
                try {
                    u = getUserList();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < u.size(); i++) {
                    String name1 = u.get(i).getUserName();
                    if (params[0].equals(name1))
                        return u.get(i);
                }

                return null;
            }
        }.execute(name).get();

    }

    @Override
    public boolean userExist(User u) throws ExecutionException, InterruptedException {
        return (new AsyncTask<User, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(User... params) {
                List<User> users = null;
                try {
                    users = getUserList();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < users.size(); i++) {

                    String name = users.get(i).getUserName();
                    String name1 = params[0].getUserName();
                    String password = users.get(i).getPassword();
                    if (name.matches(params[0].getUserName()) && password.matches(params[0].getPassword())) {
                        return true;
                    }
                }
                return false;
            }
        }.execute(u).get());

    }

    @Override
    public IdentificationQuestions userExist(String name) throws ExecutionException, InterruptedException {
        return new AsyncTask<String, Void, IdentificationQuestions>() {
            @Override
            protected IdentificationQuestions doInBackground(String... params) {
                if (params[0].matches("rachel")) {
                    updateFlag = true;
                }
                List<User> list = null;
                try {
                    list = getUserList();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        int b;
                        String name1 = list.get(i).getUserName();
                        if (params[0].matches(name1))
                            return list.get(i).getIdentificationQuestions();

                    }
                }
                return null;
            }
        }.execute(name).get();
    }

    public void printLog(String message) {
        Log.d(this.getClass().getName(), "\n" + message);
    }

    @Override
    public void addBrunch(Branch branch) {

        new AsyncTask<Branch, Void, Void>() {
            @Override
            protected Void doInBackground(Branch... params) {
                try {
                    String result = null;
                    result = PHPtools.POST(WEB_URL + "AddBranch.php", ConvertToContext.BranchToContentValue(params[0]));

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(branch);
    }

    @Override
    public void addModel(Model model) {


        new AsyncTask<Model, Void, Void>() {
            @Override
            protected Void doInBackground(Model... params) {
                try {
                    String result = null;
                    result = PHPtools.POST(WEB_URL + "AddModel.php", ConvertToContext.ModelToContentValue(params[0]));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(model);
    }

    @Override
    public void addCar(Car car) {


        new AsyncTask<Car, Void, Void>() {
            @Override
            protected Void doInBackground(Car... params) {
                try {
                    String result = null;
                    result = PHPtools.POST(WEB_URL + "AddCar.php", ConvertToContext.CarToContentValues(params[0]));


                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(car);
    }

    @Override
    public void addNewCustomer(Customer customer) throws ExecutionException, InterruptedException {


        new AsyncTask<Customer, Void, Void>() {
            @Override
            protected Void doInBackground(Customer... params) {
                try {
                    String result = null;
                    result = PHPtools.POST(WEB_URL + "AddClient.php", ConvertToContext.ClientToContentValue(params[0]));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(customer);
    }

    @Override
    public void addNewUser(User user) throws ExecutionException, InterruptedException {
        new AsyncTask<User, Void, Void>() {
            @Override
            protected Void doInBackground(User... params) {
                try {
                    String result = null;
                    result = PHPtools.POST(WEB_URL + "AddUser.php", ConvertToContext.UserToContentValus(params[0]));

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(user);
    }

    @Override
    public List<Car> getAvailableCar() {
        List<Car> carList = null;
        List<Car> carListBusy = new ArrayList<Car>();
        List<Order> orderList = null;

        try { //cheak double asyntask
            carList = getCarList();
            orderList = getOrder();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < carList.size(); i++) {
            for (int j = 0; j < orderList.size(); j++) {
                if (carList.get(i).getCarNumber() == orderList.get(j).getNumberCar() && orderList.get(j).isOpenOrder())
                    carListBusy.add(carList.get(i));
            }

        }

        for (int j = 0; j < carListBusy.size(); j++)
            carList.remove(carListBusy.get(j));
        return carList;

    }

    @Override
    public List<Car> getAvailableCarForBranch(Branch b) {

        List<Car> freeCar = new ArrayList<Car>();

        List<Car> cars = getAvailableCar();
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getBranchNumOfParking() == b.getBranchNum()) {
                freeCar.add(cars.get(i));
            }

        }
        return freeCar;

    }

    @Override
    public List<Car> getAvailableCarForBranch(long num) {
        List<Car> freeCar = null;
        for (int i = 0; i < getAvailableCar().size(); i++) {
            if (getAvailableCar().get(i).getBranchNumOfParking() == num) {
                freeCar.add(getAvailableCar().get(i));
            }

        }
        return freeCar;
    }

    @Override
    public List<Branch> getBranchByModel(Model m) {
        List<Branch> branch = null;
        for (int i = 0; i < getAvailableCar().size(); i++) {
            if (getAvailableCar().get(i).getModel() == m.getModalName()) {
                branch.add(returnBranchByNum(getAvailableCar().get(i).getBranchNumOfParking()));
            }

        }
        return branch;
    }

    @Override
    public List<Customer> getCustomerList() throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<Customer>>() {
            @Override
            protected List<Customer> doInBackground(Void... params) {
                List<Customer> result = new ArrayList<Customer>();
                try {
                    String s = PHPtools.GET(WEB_URL + "getClient.php");
                    JSONArray array = new JSONObject(s).getJSONArray("clients");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.optJSONObject(i);
                        Customer c = new Customer();
                        c.setFirstName(jsonObject.getString("firstName"));
                        c.setLastName(jsonObject.getString("lastName"));
                        c.setId(jsonObject.getString("Id"));
                        c.setEmailAddress(jsonObject.getString("emailAddress"));
                        c.setPhoneNumber(jsonObject.getString("PhoneNumber"));
                        c.setCreditCardNumber(jsonObject.getString("creditCardNumber"));

                        result.add(c);

                    }
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute().get();
    }

    @Override
    public List<Branch> getBranchList() throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<Branch>>() {
            @Override
            protected List<Branch> doInBackground(Void... params) {
                List<Branch> result = new ArrayList<Branch>();
                try {
                    String s = PHPtools.GET(WEB_URL + "getBranch.php");
                    JSONArray array = new JSONObject(s).getJSONArray("branchs");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.optJSONObject(i);
                        Branch b = new Branch();
                        b.setAddress(jsonObject.getString("address"));
                        b.setBranchNum(jsonObject.getInt("branchNum"));
                        b.setParkingSpacesNum(jsonObject.getString("parkingSpacesNum"));
                        result.add(b);
                    }
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute().get();
    }

    @Override
    public List<Car> getCarList() throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<Car>>() {
            @Override
            protected List<Car> doInBackground(Void... params) {
                List<Car> result = new ArrayList<Car>();
                try {
                    String s = PHPtools.GET(WEB_URL + "getCar.php");
                    JSONArray array = new JSONObject(s).getJSONArray("cars");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.optJSONObject(i);
                        Car c = new Car();
                        c.setCarNumber(jsonObject.getLong("carNumber"));
                        c.setKilometers(jsonObject.getInt("kilometers"));
                        c.setModel(jsonObject.getString("model"));
                        c.setBranchNumOfParking(jsonObject.getInt("branchNumOfParking"));
                        result.add(c);
                    }
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute().get();
    }

    @Override
    public List<Model> getModelList() throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<Model>>() {
            @Override
            protected List<Model> doInBackground(Void... params) {
                List<Model> result = new ArrayList<Model>();
                try {
                    String s = PHPtools.GET(WEB_URL + "getModel.php");
                    JSONArray array = new JSONObject(s).getJSONArray("models");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.optJSONObject(i);
                        Model m = new Model();

                        m.setModelCode(jsonObject.getString("modelCode"));
                        m.setCompanyName(jsonObject.getString("companyName"));
                        m.setModalName(jsonObject.getString("modalName"));
                        m.setEngineCapacity(jsonObject.getString("engineCapacity"));
                        m.setSeatingPlace(jsonObject.getString("seatingPlace"));
                        GearBox g = null;
                        String re = (jsonObject.getString("gearBoxStatus")).toString();
                        switch (re) {
                            case "automatic":
                                g = GearBox.automatic;
                                break;
                            case "manual":
                                g = GearBox.manual;
                                break;

                        }
                        m.setGearBoxStatus(g);
                        m.setProductionYear((jsonObject.getInt("productionYear")));
                        result.add(m);
                    }
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute().get();
    }

    @Override
    public List<Order> getOrder() throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<Order>>() {
            @Override
            protected List<Order> doInBackground(Void... params) {
                List<Order> result = new ArrayList<Order>();
                try {
                    String s = PHPtools.GET(WEB_URL + "getOrder.php");
                    JSONArray array = new JSONObject(s).getJSONArray("orders");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.optJSONObject(i);
                        Order c = new Order();
                        c.setCustomerName(jsonObject.getString("customerName"));
                        if (jsonObject.getInt("isOpenOrder") == 1)
                            c.setOpenOrder(true);
                        DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
                        Date d = format.parse(jsonObject.getString("startRental"));
                        try {
                            c.setStartRental(format.parse(jsonObject.getString("startRental")));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        try {
                            c.setEndRental(format.parse(jsonObject.getString("endRental")));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        c.setKilometerStartValue(jsonObject.getInt("kilometerStartValue"));
                        c.setKilometerEndValue(jsonObject.getInt("kilometerEndValue"));
                        if (jsonObject.getInt("isFuelFilled") == 1)
                            c.setFuelFilled(true);
                        c.setLitersWasFilled(jsonObject.getInt("litersWasFilled"));
                        c.setOrderNumber(jsonObject.getInt("orderNumber"));
                        c.setNumberCar(jsonObject.getInt("numberCar"));
                        result.add(c);
                    }
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute().get();
    }

    @Override
    public List<Order> getOpenPrder() {
        List<Order> orderList = null;
        try {
            orderList = getOrder();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Order> openOrderList = new ArrayList<Order>();
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).isOpenOrder())
                openOrderList.add(orderList.get(i));
        }

        return openOrderList;

    }

    @Override
    public void addOrder(Order o) {

        new AsyncTask<Order, Void, Void>() {
            @Override
            protected Void doInBackground(Order... params) {
                try {
                    String result = null;
                    result = PHPtools.POST(WEB_URL + "AddOrder.php", ConvertToContext.OrderToContentValue(params[0]));

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(o);
    }

    @Override
    public List<User> getUserList() throws ExecutionException, InterruptedException {
      /*  return new AsyncTask<Void, Void, List<User>>() {
            @Override
            protected List<User> doInBackground(Void... params) {*/
        List<User> result = new ArrayList<User>();
        try {
            String s = PHPtools.GET(WEB_URL + "getUser.php");
            JSONArray array = new JSONObject(s).getJSONArray("users");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.optJSONObject(i);
                User u = new User();
                u.setUserName(jsonObject.getString("userName"));
                u.setPassword(jsonObject.getString("password"));
                String re = (jsonObject.getString("identificationQuestions")).toString();
                IdentificationQuestions i1 = null;
                switch (re) {
                    case "your_favorite_food":
                        i1 = IdentificationQuestions.your_favorite_food;
                        break;
                    case "your_mothers_mother":
                        i1 = IdentificationQuestions.your_mothers_mother;
                        break;

                }
                // IdentificationQuestions i1 = IdentificationQuestions.valueOf(jsonObject.getString("identificationQuestions"));
                u.setIdentificationQuestions(i1);
                u.setAns(jsonObject.getString("ans"));
                result.add(u);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
      /* }
        }.execute().get();*/

    }

    private void SetUpdate() {
        updateFlag = true;
    }

    @Override
    public List<Car> getCarInRange() {
        return null;
    }

    @Override
    public void closeOrder(Order o) {

        o.setOpenOrder(false);
        o.setEndRental(new java.sql.Date(System.currentTimeMillis()));
        Car c = returnCarByNum(o.getNumberCar());
        c.setKilometers(o.getKilometerEndValue());
        UpdateCar(c);

        //global flag
        ConstValue.flag = true;


        new AsyncTask<Order, Void, Void>() {
            @Override
            protected Void doInBackground(Order... params) {
                try {
                    String result, result1 = null;
                    result = PHPtools.POST(WEB_URL + "deleteOrder.php", ConvertToContext.OrderToContentValue1(params[0]));


                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(o);


        addOrder(o);
    }

    @Override
    public boolean isCloseOrder() {
        return false;
    }

    //*****help function********

    /**
     * return an object of branch by branch num
     *
     * @param num
     * @return
     */
    public Branch returnBranchByNum(long num) {

        List<Branch> branchList = null;
        try {
            branchList = getBranchList();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < branchList.size(); i++) {
            if (branchList.get(i).getBranchNum() == num)
                return branchList.get(i);
        }
        return null;
    }

    /**
     * return an object of car by car num
     *
     * @param num
     * @return
     */
    public Car returnCarByNum(long num) {

        List<Car> carList = null;
        try {
            carList = getCarList();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getCarNumber() == num)
                return carList.get(i);
        }
        return null;
    }


    /**
     * return an object of model by model name
     *
     * @param name
     * @return
     */
    public Model returnModelByName(String name) {
        List<Model> modelList = null;
        try {
            modelList = getModelList();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Model m : modelList
                ) {
            if (m.getModalName().equals(name))
                return m;

        }

        return null;

    }

    @Override
    public void addPrice(Price price) {
        new AsyncTask<Price, Void, Void>() {
            @Override
            protected Void doInBackground(Price... params) {
                try {
                    String result = null;
                    result = PHPtools.POST(WEB_URL + "AddPrice.php", ConvertToContext.PriceToContentValues(params[0]));

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(price);
    }

    @Override
    public List<Price> getPriceList() throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<Price>>() {
            @Override
            protected List<Price> doInBackground(Void... params) {
                List<Price> result = new ArrayList<Price>();
                try {
                    String s = PHPtools.GET(WEB_URL + "getPrice.php");
                    JSONArray array = new JSONObject(s).getJSONArray("prices");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.optJSONObject(i);
                        Price p = new Price();
                        p.setNumberCar(jsonObject.getLong("numberCar"));
                        p.setPrice(Float.parseFloat( jsonObject.getString("price")));
                        result.add(p);
                    }
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
       }
        }.execute().get();

            }

}


