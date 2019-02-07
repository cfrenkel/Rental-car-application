package com.example.this_user.ourproject5778_9075_4711_02.model.entities;

/**
 * Created by This_user on 06/03/2018.
 */

public class Branch {
    /**
     * properties
     */
    private String address;
    private String parkingSpacesNum;
    private int branchNum;

    /*
    getters and setters
     */

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getParkingSpacesNum() {
        return parkingSpacesNum;
    }

    public void setParkingSpacesNum(String parkingSpacesNum) {
        this.parkingSpacesNum = parkingSpacesNum;
    }

    public int getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(int branchNum) {
        this.branchNum = branchNum;
    }

    /**
     * constructores...
     * @param address
     * @param parkingSpacesNum
     * @param branchNum
     */
    public Branch(String address, String parkingSpacesNum, int branchNum ) {

        this.address = address;
        this.parkingSpacesNum = parkingSpacesNum;
        this.branchNum = branchNum;

    }
    public Branch(Branch b) {

        this.address = b.getAddress();
        this.parkingSpacesNum = b.parkingSpacesNum;
        this.branchNum = b.branchNum;

    }
    public Branch() {
        this.address = "";
        this.parkingSpacesNum = "";
        this.branchNum = 0;
    }

    /**
     * toString
     */
    @Override
    public String toString() {
        return "Branch: " +
                "  \nbranch num=" + branchNum  +
                ", address='" + address + '\'' +
                ", parking spaces num=" + parkingSpacesNum +

                '.';
    }
    /**
     * equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Branch branch = (Branch) o;

        if (parkingSpacesNum != branch.parkingSpacesNum) return false;
        if (branchNum != branch.branchNum) return false;
        if (!address.equals(branch.address)) return false;
       return true;

    }



}
