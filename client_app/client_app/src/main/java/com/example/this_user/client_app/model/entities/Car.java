package com.example.this_user.ourproject5778_9075_4711_02.model.entities;

/**
 * Created by This_user on 06/03/2018.
 */

public class Car
{
    /**
     * properties
     */
    private int  branchNumOfParking;
    private String model;
    private int kilometers;
    private long carNumber;

    /**
    * getters and setters
     */
    public int getBranchNumOfParking() {
        return branchNumOfParking;
    }

    public void setBranchNumOfParking(int branchNumOfParking) {
        this.branchNumOfParking = branchNumOfParking;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public long getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(long carNumber) {
        this.carNumber = carNumber;
    }

    /***
     * constructors
     * @param branchNumOfParking
     * @param model
     * @param kilometers
     * @param carNumber
     */
    public Car(int branchNumOfParking, String model, int kilometers, long carNumber) {
        this.branchNumOfParking = branchNumOfParking;
        this.model = model;
        this.kilometers = kilometers;
        this.carNumber = carNumber;
    }
    public Car(Car c) {
        this.branchNumOfParking = c.branchNumOfParking;
        this.model = c.model;
        this.kilometers = c.kilometers;
        this.carNumber = c.carNumber;
    }
    public Car( ) {
        this.branchNumOfParking = 0;
        this.model = "";
        this.kilometers = 0;
        this.carNumber = 0;
    }
    /**
        toString
         */
    @Override
    public String toString() {
        return "Car: " +
                " \ncar number=" + carNumber +
                ", branch num of parking=" + branchNumOfParking +
                ", kilometers=" + kilometers +
                '.';
    }
    /**
        equals
         */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (branchNumOfParking != car.branchNumOfParking) return false;
        if (kilometers != car.kilometers) return false;
        if (carNumber != car.carNumber) return false;
        return model.equals(car.model);

    }


}
