package com.example.this_user.ourproject5778_4711_9075.model.backend;

import com.example.this_user.ourproject5778_4711_9075.model.entities.*;


import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by This_user on 18/03/2018.
 */

public interface BackEndInterface
{

    /**
     * cheak if client exist
     * @param c
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    boolean customerExist(Customer c) throws ExecutionException, InterruptedException;

    /**
     * cheak if client exist
     * @param name
     * @param paswword
     * @return
     */
    boolean customerExist(String name, String paswword);

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
     * cheak if branch exist
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
     * return Identification Questions by user name
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
     * get clients list
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    List<Customer> getCustomerList() throws ExecutionException, InterruptedException;

    /**
     * get branches list
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    List<Branch> getBranchList() throws ExecutionException, InterruptedException;

    /**
     * get cars list
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    List<Car> getCarList() throws ExecutionException, InterruptedException;

    /**
     * get models list
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    List<Model> getModelList() throws ExecutionException, InterruptedException;

    /**
     * get users list
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    List<User> getUserList() throws ExecutionException, InterruptedException;


    /**
     * add new price
     * @param user
     */
    void addPrice(Price user);

    /**
     * get prices list
     * @return
     */
    List<Price> getPriceList();


}
