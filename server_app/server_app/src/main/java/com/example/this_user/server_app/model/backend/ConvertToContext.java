package com.example.this_user.ourproject5778_4711_9075.model.backend;

import android.content.ContentValues;

import com.example.this_user.ourproject5778_4711_9075.model.entities.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by This_user on 01/05/2018.
 */

public class ConvertToContext {
    /**
     * convert car to content
     *
     * @param car
     * @return
     */
    public static ContentValues CarToContentValues(Car car) {
        ContentValues c = new ContentValues();
        c.put("carNumber", car.getCarNumber());
        c.put("model", car.getModel());
        c.put("kilometers", car.getKilometers());
        c.put("branchNumOfParking", car.getBranchNumOfParking());

        return c;
    }

    /**
     * convert content to car
     *
     * @param c
     * @return
     */
    public static Car ContentValuesToCar(ContentValues c) {
        Car car = new Car();
        car.setCarNumber(c.getAsLong("carNumber"));
        car.setModel(c.getAsString("model"));
        car.setBranchNumOfParking(c.getAsInteger("branchNumOfParking"));
        car.setKilometers(c.getAsInteger("kilometers"));

        return car;
    }

    /**
     * convert branch to content
     *
     * @param branch
     * @return
     */
    public static ContentValues BranchToContentValue(Branch branch) {
        ContentValues c = new ContentValues();
        c.put("address", branch.getAddress());
        // c.put("branchNum", branch.getBranchNum());
        c.put("parkingSpacesNum", branch.getParkingSpacesNum());
        return c;
    }

    /**
     * convert fron content to branch
     *
     * @param c
     * @return
     */
    public static Branch ContentValueToBranch(ContentValues c) {
        Branch b = new Branch();
        b.setAddress(c.getAsString("address"));
        b.setBranchNum(c.getAsInteger("branchNum"));
        b.setParkingSpacesNum(c.getAsString("parkingSpacesNum"));
        return b;
    }

    /**
     * convert user to content
     *
     * @param u
     * @return
     */
    public static ContentValues UserToContentValus(User u) {

        ContentValues c = new ContentValues();
        c.put("userName", u.getUserName());
        c.put("password", u.getPassword());
        c.put("identificationQuestions", u.getIdentificationQuestions().toString());
        c.put("ans", u.getAns());
        return c;

    }

    /**
     * convert content to user
     *
     * @param c
     * @return
     */
    public static User ContentValusToUser(ContentValues c) {

        User u = new User();
        u.setUserName(c.getAsString("userName"));
        u.setPassword(c.getAsString("password"));
        String re = c.getAsString("identificationQuestions");
        IdentificationQuestions i = null;
        switch (re) {
            case "your_favorite_food":
                i = IdentificationQuestions.your_favorite_food;
                break;
            case "your_mothers_mother":
                i = IdentificationQuestions.your_mothers_mother;
                break;

        }
        u.setIdentificationQuestions(i);
        u.setAns(c.getAsString("ans"));
        return u;
    }

    /**
     * convert model to content
     *
     * @param m
     * @return
     */
    public static ContentValues ModelToContentValue(Model m) {
        ContentValues c = new ContentValues();
        c.put("modelCode", m.getModelCode());
        c.put("companyName", m.getCompanyName());
        c.put("modalName", m.getModalName());
        c.put("engineCapacity", m.getEngineCapacity());
        c.put("gearBoxStatus", m.getGearBoxStatus().toString());
        c.put("seatingPlace", m.getSeatingPlace());
        c.put("productionYear", m.getProductionYear());
        return c;
    }

    /**
     * convert content to model
     *
     * @param c
     * @return
     */
    public static Model ContentValueToModel(ContentValues c) {
        Model m = new Model();
        m.setModelCode(c.getAsString("modelCode"));
        m.setCompanyName(c.getAsString("companyName"));
        m.setModalName(c.getAsString("modalName"));
        m.setEngineCapacity(c.getAsString("engineCapacity"));
        m.setSeatingPlace(c.getAsString("seatingPlace"));
        GearBox g = null;
        String re = c.getAsString("gearBoxStatus");
        switch (re) {
            case "automatic":
                g = GearBox.automatic;
                break;
            case "manual":
                g = GearBox.manual;
                break;

        }
        m.setGearBoxStatus(g);
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        m.setProductionYear(c.getAsInteger("productionYear"));
        return m;
    }

    /**
     * convert client to content
     *
     * @param c
     * @return
     */
    public static ContentValues ClientToContentValue(Customer c) {
        ContentValues c1 = new ContentValues();
        c1.put("lastName", c.getLastName());
        c1.put("firstName", c.getFirstName());
        c1.put("Id", c.getId());
        c1.put("PhoneNumber", c.getPhoneNumber());
        c1.put("emailAddress", c.getEmailAddress());
        c1.put("creditCardNumber", c.getCreditCardNumber());
        return c1;
    }


    public static ContentValues PriceToContentValues(Price P) {
        ContentValues c = new ContentValues();
        c.put("numberCar", P.getNumberCar());
        c.put("price", P.getPrice());

        return c;
    }
}