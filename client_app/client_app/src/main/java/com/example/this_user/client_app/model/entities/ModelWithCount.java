package com.example.this_user.ourproject5778_9075_4711_02.model.entities;

/**
 * Created by This_user on 10/06/2018.
 */

public class ModelWithCount {

    String model;
    int count;


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ModelWithCount(String model, int count) {
        this.model = model;
        this.count = count;
    }

    public ModelWithCount(){}
}
