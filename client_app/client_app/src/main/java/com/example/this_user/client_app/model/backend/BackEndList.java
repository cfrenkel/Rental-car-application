package com.example.this_user.ourproject5778_9075_4711_02.model.backend;

import android.os.AsyncTask;

import com.example.this_user.ourproject5778_9075_4711_02.model.datasource.*;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by This_user on 18/03/2018.
 */


public class BackEndList implements BackEndInterface
{

    public BackEndList()
    {

    }

    @Override
    public boolean customerExist(Customer c) throws ExecutionException, InterruptedException {
        return
                (new AsyncTask<Customer, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Customer... params) {
                if( ListDataSource.customerList.contains(params[0]))
                    return true;
                return false;
            }
        }.execute(c)).get();


    }
    @Override
    public boolean customerExist(String name, String paswword) {
        return false;
    }

    @Override
    public void UpdateCar(Car kilo) {

    }
    /*
    @Override
    public boolean customerExist(String name, String paswword) {
        for( int i=0; i<ListDataSource.customerList.size(); i++)
        {
            if(ListDataSource.customerList.get(i).getEmailAddress()==name
                    && ListDataSource.customerList.get(i).getPassword()==paswword  )
                return true;
        }
        return false;
    }*/

    @Override
    public boolean carExist(Car c) throws ExecutionException, InterruptedException {
        return  new AsyncTask<Car,Void,Boolean>() {
            @Override
            protected Boolean doInBackground(Car... params) {
                if(ListDataSource.carList.contains(params[0]))

                {
                    return true;
                }
                return false;
            }
        }.execute(c).get();
    }

    @Override
    public boolean modelExist(Model m) throws ExecutionException, InterruptedException {
        return new AsyncTask<Model, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Model... params) {
                if( ListDataSource.modelList.contains(params[0]))
                    return true;
                return false;
            }
        }.execute(m).get();

    }

    @Override
    public boolean branchExist(Branch b) throws ExecutionException, InterruptedException {
        return new AsyncTask<Branch,Void,Boolean>()
        {
            @Override
            protected Boolean doInBackground(Branch... params) {
                if(ListDataSource.branchList.contains(params[0]))
                    return true;
                return false;
            }
        }.execute(b).get();
    }
    @Override
    public boolean userExist(User u) throws ExecutionException, InterruptedException {
     /*  if( ListDataSource.userList.contains(u))
            return true;
        return false;
        */

        return  (new AsyncTask<User, Void, Boolean>()
        {
            @Override
            protected Boolean doInBackground(User... params) {
                for (int i =0; i<ListDataSource.userList.size(); i++)
                {
                    String name= ListDataSource.userList.get(i).getUserName();
                    String password= ListDataSource.userList.get(i).getPassword();

                    if(name.equals(params[0].getUserName())&& password.equals(params[0].getPassword()))
                    {
                        return true;
                    }
                }
                return false;
            }
        }.execute(u).get());
    }

    @Override
    public IdentificationQuestions userExist(String name) throws ExecutionException, InterruptedException {
     /*  if( ListDataSource.userList.contains(u))
            return true;
        return false;
        */
        return new AsyncTask<String, Void, IdentificationQuestions>() {
            @Override
            protected IdentificationQuestions doInBackground(String... params) {
                for (int i =0; i<ListDataSource.userList.size(); i++)
                {
                    String name1= ListDataSource.userList.get(i).getUserName();
                    if(params[0].equals(name1))
                        return ListDataSource.userList.get(i).getIdentificationQuestions();
                }
                return null;
            }
        }.execute(name).get();

    }



    @Override
    public User returnUser(String name) throws ExecutionException, InterruptedException {
     /*  if( ListDataSource.userList.contains(u))
            return true;
        return false;
         */
        return    new AsyncTask<String, Void, User>() {
            @Override
            protected User doInBackground(String... params) {
                for(int i = 0; i<ListDataSource.userList.size();i++)
                {
                    String name1 = ListDataSource.userList.get(i).getUserName();
                    if (params[0].equals(name1))
                        return ListDataSource.userList.get(i);
                }

                return null;
            }
        }.execute(name).get();
    }

    @Override
    public void addBrunch(Branch branch) {
        new AsyncTask<Branch, Void, Void>() {
            @Override
            protected Void doInBackground(Branch... params) {
              /*  try {
                    if(!modelExist(params[0]))*/
                ListDataSource.branchList.add(params[0]);
             /*   } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                return null;
            }
        }.execute(branch);

    }
    @Override
    public void addModel(Model model)  {

        new AsyncTask<Model, Void, Void>() {
            @Override
            protected Void doInBackground(Model... params) {
              /*  try {
                    if(!modelExist(params[0]))*/
                        ListDataSource.modelList.add(params[0]);
             /*   } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                return null;
            }
        }.execute(model);
    }

    @Override
    public void addCar(Car car) {

        new AsyncTask<Car, Void, Void>() {
            @Override
            protected Void doInBackground(Car... params) {

            /*    try {
                    if(!carExist(params[0]))*/
                        ListDataSource.carList.add(params[0]);
           /*     } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                return null;
            }
        }.execute(car);

    }

    @Override
    public Branch returnBranchByNum(long num) {
        return null;
    }

    @Override
    public Car returnCarByNum(long num) {
        return null;
    }

    @Override
    public void addNewCustomer(Customer customer)  {

        new AsyncTask<Customer, Void, Void>() {
            @Override
            protected Void doInBackground(Customer... params) {
            /*    try {
                    if(!customerExist(params[0]))*/
                        ListDataSource.customerList.add(params[0]);
             /*   } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                return null;
            }

        }.execute(customer);


    }

    @Override
    public void addNewUser(User user)  {

        new AsyncTask<User, Void, Void>() {
            @Override
            protected Void doInBackground(User... params) {
            /*    try {
                    if(!userExist(params[0]))*/
                        ListDataSource.userList.add(params[0]);
            /*    } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                return null;
            }
        }.execute(user);

    }

    @Override
    public List<Car> getAvailableCar() {
        return null;
    }

    @Override
    public List<Car> getAvailableCarForBranch(Branch b) {
        return null;
    }

    @Override
    public List<Car> getAvailableCarForBranch(long num) {
        return null;
    }

    @Override
    public List<Car> getCarInRange() {
        return null;
    }

    @Override
    public List<Customer> getCustomerList() throws ExecutionException, InterruptedException {
       return new AsyncTask<Void, Void, List<Customer>>() {
            @Override
            protected List<Customer> doInBackground(Void... params) {
                return  ListDataSource.customerList;
            }
        }.execute().get();

    }

    @Override
    public List<Branch> getBranchList() throws ExecutionException, InterruptedException {
        return new AsyncTask<Void, Void, List<Branch>>() {
            @Override
            protected List<Branch> doInBackground(Void... params) {
                return ListDataSource.branchList;
            }
        }.execute().get();

    }

    @Override
    public List<Car> getCarList() throws ExecutionException, InterruptedException {
       return new AsyncTask<Void, Void, List<Car>>() {
            @Override
            protected List<Car> doInBackground(Void... params) {
                return ListDataSource.carList;
            }
        }.execute().get();

    }

    @Override
    public List<Model> getModelList() throws ExecutionException, InterruptedException {
        return new AsyncTask<Void,Void,List<Model>>()
        {
            @Override
            protected List<Model> doInBackground(Void... params) {
                return ListDataSource.modelList;
            }
        }.execute().get();
        }

    @Override
    public List<Branch> getBranchByModel(Model m) {
        return null;
    }

    @Override
    public List<Order> getOrder() throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public List<Order> getOpenPrder() {
        return null;
    }

    @Override
    public void addOrder(Order o) {

    }

    @Override
    public void closeOrder(Order kilo) {

    }

    @Override
    public boolean isCloseOrder() {
        return false;
    }

    @Override
    public List<User> getUserList() throws ExecutionException, InterruptedException {
        return new AsyncTask<Void,Void,List<User>>()
        {
            @Override
            protected List<User> doInBackground(Void... params) {
                return ListDataSource.userList;
            }
        }.execute().get();
    }

    public Model returnModelByName(String name){return null;}

    @Override
    public void addPrice(Price user) {

    }

    @Override
    public List<Price> getPriceList() {
        return null;
    }
}
