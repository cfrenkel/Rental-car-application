package com.example.this_user.ourproject5778_4711_9075.model.entities;

import java.util.Date;

/**
 * Created by This_user on 06/03/2018.
 */

public class Order
{
    /*
    * properties
     */
    private String customerName;
    private boolean isOpenOrder;
    private long numberCar;
    private Date startRental;
    private Date endRental;
    private int kilometerStartValue;
    private int kilometerEndValue;
    private boolean isFuelFilled;
    private int litersWasFilled;
    private long orderNumber;

    /*
    * gettesr and setters
    *
     */
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public boolean isOpenOrder() {
        return isOpenOrder;
    }

    public void setOpenOrder(boolean openOrder) {
        isOpenOrder = openOrder;
    }

    public long getNumberCar() {
        return numberCar;
    }

    public void setNumberCar(long numberCar) {
        this.numberCar = numberCar;
    }

    public Date getStartRental() {
        return startRental;
    }

    public void setStartRental(Date startRental) {
        this.startRental = startRental;
    }

    public Date getEndRental() {
        return endRental;
    }

    public void setEndRental(Date endRental) {
        this.endRental = endRental;
    }

    public int getKilometerStartValue() {
        return kilometerStartValue;
    }

    public void setKilometerStartValue(int kilometerStartValue) {
        this.kilometerStartValue = kilometerStartValue;
    }

    public int getKilometerEndValue() {
        return kilometerEndValue;
    }

    public void setKilometerEndValue(int kilometerEndValue) {
        this.kilometerEndValue = kilometerEndValue;
    }

    public boolean isFuelFilled() {
        return isFuelFilled;
    }

    public void setFuelFilled(boolean fuelFilled) {
        isFuelFilled = fuelFilled;
    }

    public int getLitersWasFilled() {
        return litersWasFilled;
    }

    public void setLitersWasFilled(int litersWasFilled) {
        this.litersWasFilled = litersWasFilled;
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * constrctors
     * @param customerName
     * @param isOpenOrder
     * @param numberCar
     * @param startRental
     * @param endRental
     * @param kilometerStartValue
     * @param kilometerEndValue
     * @param isFuelFilled
     * @param litersWasFilled
     * @param orderNumber
     */
    public Order(String customerName, boolean isOpenOrder, long numberCar, Date startRental, Date endRental, int kilometerStartValue, int kilometerEndValue, boolean isFuelFilled, int litersWasFilled, long orderNumber) {
        this.customerName = customerName;
        this.isOpenOrder = isOpenOrder;
        this.numberCar = numberCar;
        this.startRental = startRental;
        this.endRental = endRental;
        this.kilometerStartValue = kilometerStartValue;
        this.kilometerEndValue = kilometerEndValue;
        this.isFuelFilled = isFuelFilled;
        this.litersWasFilled = litersWasFilled;
        this.orderNumber = orderNumber;
    }
    public Order(Order o) {      this.customerName = customerName;
        this.isOpenOrder = o.isOpenOrder;
        this.numberCar = o.numberCar;
        this.startRental = o.startRental;
        this.endRental = o.endRental;
        this.kilometerStartValue = o.kilometerStartValue;
        this.kilometerEndValue = o.kilometerEndValue;
        this.isFuelFilled = o.isFuelFilled;
        this.litersWasFilled = o.litersWasFilled;
        this.orderNumber = o.orderNumber;
    }
    public Order() {
        this.customerName = "";
        this.isOpenOrder = false;
        this.numberCar = 0;
        this.startRental = new Date();
        this.endRental =  new Date();
        this.kilometerStartValue = 0;
        this.kilometerEndValue = 0;
        this.isFuelFilled = false;
        this.litersWasFilled = 0;
        this.orderNumber = 0;
    }
/**
toString
 */
    @Override
    public String toString() {
        return "Order{" +
                "customerName='" + customerName + '\'' +
                ", isOpenOrder=" + isOpenOrder +
                ", numberCar=" + numberCar +
                ", startRental=" + startRental +
                ", endRental=" + endRental +
                ", kilometerStartValue=" + kilometerStartValue +
                ", kilometerEndValue=" + kilometerEndValue +
                ", isFuelFilled=" + isFuelFilled +
                ", litersWasFilled=" + litersWasFilled +
                ", orderNumber=" + orderNumber +
                '}';
    }
/**
equals
 */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (isOpenOrder != order.isOpenOrder) return false;
        if (numberCar != order.numberCar) return false;
        if (kilometerStartValue != order.kilometerStartValue) return false;
        if (kilometerEndValue != order.kilometerEndValue) return false;
        if (isFuelFilled != order.isFuelFilled) return false;
        if (litersWasFilled != order.litersWasFilled) return false;
        if (orderNumber != order.orderNumber) return false;
        if (!customerName.equals(order.customerName)) return false;
        if (!startRental.equals(order.startRental)) return false;
        return endRental.equals(order.endRental);

    }

}
