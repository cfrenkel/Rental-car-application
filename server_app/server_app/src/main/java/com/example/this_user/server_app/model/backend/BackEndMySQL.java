package com.example.this_user.ourproject5778_4711_9075.model.backend;

import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.Log;

import com.example.this_user.ourproject5778_4711_9075.model.datasource.ListDataSource;
import com.example.this_user.ourproject5778_4711_9075.model.entities.Branch;
import com.example.this_user.ourproject5778_4711_9075.model.entities.Car;
import com.example.this_user.ourproject5778_4711_9075.model.entities.Customer;
import com.example.this_user.ourproject5778_4711_9075.model.entities.GearBox;
import com.example.this_user.ourproject5778_4711_9075.model.entities.IdentificationQuestions;
import com.example.this_user.ourproject5778_4711_9075.model.entities.Model;
import com.example.this_user.ourproject5778_4711_9075.model.entities.Price;
import com.example.this_user.ourproject5778_4711_9075.model.entities.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

/**
 * Created by This_user on 01/05/2018.
 */

public class BackEndMySQL implements BackEndInterface {

    private boolean updateFlag = false;
    private String WEB_URL = "http://cfrenkel.vlab.jct.ac.il/Academy/";

    @Override
    public boolean customerExist(Customer c) throws ExecutionException, InterruptedException {
       /* return
                (new AsyncTask<Customer, Void, Boolean>() {
                    @Override
                    protected Boolean doInBackground(Customer... params) {*/
                        try {
                            if (getCustomerList().contains(c))
                                return true;
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return false;
                  /*  }
                }.execute(c)).get();*/
    }

    @Override
    public boolean customerExist(String name, String paswword) {
        return false;
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

                        for(int i = 0; i<cars.size(); i++)
                        {
                            if(cars.get(i).getCarNumber()==c.getCarNumber())
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
                            for(int i = 0; i<branchList.size(); i++) {
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
     return    new AsyncTask<String, Void, User>()
        {
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
            for(int i = 0; i<u.size();i++)
            {
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

                List<User> list = null;
                try {
                    list = getUserList();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(list!=null) {
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
    public List<Customer> getCustomerList() throws ExecutionException, InterruptedException {
       return new AsyncTask<Void, Void, List<Customer>>() {
            @Override
            protected List<Customer> doInBackground(Void... params) {
                List<Customer> result = new ArrayList<Customer>();
                try {
                    String s = PHPtools.GET(WEB_URL + "getClient.php");
                    JSONArray array = new JSONObject(s).getJSONArray("clients");
                    for (int i = 0; i < array.length()-1; i++) {
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
                        m.setProductionYear(jsonObject.getInt("productionYear"));

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
                IdentificationQuestions i1=null;
                switch (re)
                {
                    case "your_favorite_food":
                        i1 = IdentificationQuestions.your_favorite_food;
                        break;
                    case "your_mothers_mother":
                        i1= IdentificationQuestions.your_mothers_mother;
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

    private void SetUpdate()
    {
        updateFlag = true;
    }


    @Override
    public void addPrice(Price price)  {
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
    public List<Price> getPriceList()
    {
      /*  return new AsyncTask<Void, Void, List<User>>() {
            @Override
            protected List<User> doInBackground(Void... params) {*/
        List<Price> result = new ArrayList<Price>();
        try {
            String s = PHPtools.GET(WEB_URL + "getPrice.php");
            JSONArray array = new JSONObject(s).getJSONArray("prices");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.optJSONObject(i);
                Price p= new Price();
                p.setNumberCar(jsonObject.getLong("numberCar"));
                p.setPrice((Float) jsonObject.get("price"));
                 result.add(p);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
      /* }
        }.execute().get();*/

    }

}