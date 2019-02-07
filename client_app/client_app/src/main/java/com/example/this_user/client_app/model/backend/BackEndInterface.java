package com.example.this_user.ourproject5778_9075_4711_02.model.backend;

import com.example.this_user.ourproject5778_9075_4711_02.model.entities.*;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.Customer;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by This_user on 18/03/2018.
 */

public interface BackEndInterface
{

    /**
     * cheak if client exist in the ds
     * @param c
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    boolean customerExist(Customer c) throws ExecutionException, InterruptedException;

    /**
     * cheak if client exist in the ds by is name and password
     * @param name
     * @param paswword
     * @return
     */
    boolean customerExist(String name, String paswword);

    /**
     * update the kilometrazf the car
     * @param c
     */
    void UpdateCar(Car c);

    /**
     * return client list
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    List<Customer> getCustomerList() throws ExecutionException, InterruptedException;

    /**
     * return branch list
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    List<Branch> getBranchList() throws ExecutionException, InterruptedException;

    /**
     * add new client
     * @param customer
     * @throws ExecutionException
     * @throws InterruptedException
     */
    void addNewCustomer(Customer customer) throws ExecutionException, InterruptedException;

    /**
     * add new user
     * @param user
     * @throws ExecutionException
     * @throws InterruptedException
     */
    void addNewUser(User user) throws ExecutionException, InterruptedException;

    /**
     * return the avaliable cars list
     * @return
     */
    List<Car> getAvailableCar();

    /**
     * return the Available Car list For Branch
     * @param b
     * @return
     */
    List<Car> getAvailableCarForBranch(Branch b);

    /**
     * return the Available Car list For Branch num
     * @param num
     * @return
     */
    List<Car> getAvailableCarForBranch(long num);
    List<Car> getCarInRange();

    /**
     * return model list
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    List<Model> getModelList() throws ExecutionException, InterruptedException;

    /**
     * return branch by model of car
     * @param m
     * @return
     */
    List<Branch> getBranchByModel(Model m);

    /**
     * return list of order
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    List<Order> getOrder() throws ExecutionException, InterruptedException;

    /**
     * return list of open orders
     * @return
     */
    List<Order> getOpenPrder();

    /**
     * add new order
     * @param o
     */
    void addOrder(Order o);

    /**
     * close order
     * @param o
     */
    void closeOrder(Order o);

    /**
     * cheak if order closed
     * @return
     */
    boolean isCloseOrder();

    /**
     * return car list
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    List<Car> getCarList() throws ExecutionException, InterruptedException;

    /**
     * return uder list
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    List<User> getUserList() throws ExecutionException, InterruptedException;


    /**
     * cheak if car exist
     * @param c
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    boolean carExist(Car c) throws ExecutionException, InterruptedException;

    /**
     * cheak if model exist
     * @param m
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    boolean modelExist(Model m) throws ExecutionException, InterruptedException;

    /**
     * cheak if brabch exist
     * @param b
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    boolean branchExist(Branch b) throws ExecutionException, InterruptedException;

    /**
     * return user by name
     * @param name
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    User returnUser(String name) throws ExecutionException, InterruptedException;

    /**
     * cheak if user exist
     * @param u
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    boolean userExist(User u) throws ExecutionException, InterruptedException;

    /**
     * return the Identification Questions by user name
     * @param name
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    IdentificationQuestions userExist(String name) throws ExecutionException, InterruptedException;

    /**
     * add new branch
     * @param branch
     */
    void addBrunch(Branch branch);

    /**
     * add new model
     * @param model
     */
    void addModel(Model model);

    /**
     * add new car
     * @param car
     */
    void addCar(Car car);

    /**
     * return branch by num
     * @param num
     * @return
     */
    public Branch returnBranchByNum(long num);

    /**
     * return car by num
     * @param num
     * @return
     */
    Car returnCarByNum(long num);

    /**
     * return model by name
     * @param name
     * @return
     */
    public Model returnModelByName(String name);

    /**
     * add new price to db
     * @param user
     */
    void addPrice(Price user);

    /**
     * return prices list
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    List<Price> getPriceList() throws ExecutionException, InterruptedException;

}
