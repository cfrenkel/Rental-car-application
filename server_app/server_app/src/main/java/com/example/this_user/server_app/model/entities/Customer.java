package com.example.this_user.ourproject5778_4711_9075.model.entities;

/**
 * Created by This_user on 06/03/2018.
 */

public class Customer
{
    /**
     * properties
     */
    private String lastName;
    private String firstName;
    private String Id;
    private String PhoneNumber;
    private String emailAddress;
    private String creditCardNumber;

    /**
     * getters and setters
     */
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    /**
     *constructores...
     * @param lastName
     * @param firstName
     * @param id
     * @param PhoneNumber
     * @param emailAddress
     * @param creditCardNumber
     */
    public Customer(String lastName, String firstName, String id, String PhoneNumber, String emailAddress, String creditCardNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        Id = id;
        this.PhoneNumber = PhoneNumber;
        this.emailAddress = emailAddress;
        this.creditCardNumber = creditCardNumber;
    }
    public Customer(Customer c) {
        this.lastName = c.lastName;
        this.firstName = c.firstName;
        Id = c.Id;
        this.PhoneNumber = c.PhoneNumber;
        this.emailAddress = c.emailAddress;
        this.creditCardNumber = c.creditCardNumber;
    }
    public Customer() {
        this.lastName = "";
        this.firstName = "";
        Id = "";
        this.PhoneNumber = "";
        this.emailAddress = "";
        this.creditCardNumber = "";
    }

    /**
     * toString
     */
    @Override
    public String toString() {
        return "Customer{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", Id=" + Id +
                ", PhoneNumber=" + PhoneNumber +
                ", emailAddress='" + emailAddress + '\'' +
                ", creditCardNumber=" + creditCardNumber +
                '}';
    }

    /**
     * equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer client = (Customer) o;

        if (Id != client.Id) return false;
        if (PhoneNumber != client.PhoneNumber) return false;
        if (creditCardNumber != client.creditCardNumber) return false;
        if (!lastName.equals(client.lastName)) return false;
        if (!firstName.equals(client.firstName)) return false;

        return emailAddress.equals(client.emailAddress);

    }

}
