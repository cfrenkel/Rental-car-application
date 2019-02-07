package com.example.this_user.ourproject5778_9075_4711_02.model.entities;

/**
 * Created by This_user on 10/06/2018.
 */

public class Price {
    long numberCar;
    float price;
    public long getNumberCar() {
        return numberCar;
    }

    public void setNumberCar(long numberCar) {
        this.numberCar = numberCar;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Price(float price, long numberCar) {
        this.price = price;
        this.numberCar = numberCar;
    }
    public Price(){}
}
