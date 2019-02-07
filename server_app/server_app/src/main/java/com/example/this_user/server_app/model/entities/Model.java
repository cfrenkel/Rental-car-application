package com.example.this_user.ourproject5778_4711_9075.model.entities;

import java.util.Date;

/**
 * Created by This_user on 18/03/2018.
 */

public class Model
{
    /*
    properties
     */
    private String modelCode;
    private String companyName;
    private String modalName;
    private String engineCapacity;
    private GearBox  gearBoxStatus;
    private String seatingPlace;
    private int productionYear;

    /*
    getters and setters
     */

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getModalName() {
        return modalName;
    }

    public void setModalName(String modalName) {
        this.modalName = modalName;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public GearBox getGearBoxStatus() {
        return gearBoxStatus;
    }

    public void setGearBoxStatus(GearBox gearBoxStatus) {
        this.gearBoxStatus = gearBoxStatus;
    }

    public String getSeatingPlace() {
        return seatingPlace;
    }

    public void setSeatingPlace(String seatingPlace) {
        this.seatingPlace = seatingPlace;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }
    /*
        constructors
     */
    public Model(String modelCode, String companyName, String modalName, String engineCapacity, GearBox gearBoxStatus, String seatingPlace, int productionYear) {
        this.modelCode = modelCode;
        this.companyName = companyName;
        this.modalName = modalName;
        this.engineCapacity = engineCapacity;
        this.gearBoxStatus = gearBoxStatus;
        this.seatingPlace = seatingPlace;
        this.productionYear = productionYear;
    }
    public Model() {
        this.modelCode = "";
        this.companyName = "";
        this.modalName = "";
        this.engineCapacity = "";
        this.gearBoxStatus = null;
        this.seatingPlace = "";
        this.productionYear = 1990;
    }
    public Model(Model m) {
        this.modelCode = m.modelCode;
        this.companyName = m.companyName;
        this.modalName = m.modalName;
        this.engineCapacity = m.engineCapacity;
        this.gearBoxStatus = m.gearBoxStatus;
        this.seatingPlace = m.seatingPlace;
        this.productionYear = m.productionYear;
    }

    /*
    to string
     */

    @Override
    public String toString() {
        return "Model{" +
                "modelCode=" + modelCode +
                ", companyName='" + companyName + '\'' +
                ", modalName='" + modalName + '\'' +
                ", engineCapacity=" + engineCapacity +
                ", gearBoxStatus=" + gearBoxStatus +
                ", seatingPlace=" + seatingPlace +
                ", productionYear=" + productionYear +
                '}';
    }
    /*
    equals
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Model model = (Model) o;

        if (!modelCode.equals(model.modelCode)) return false;
        if (!companyName.equals(model.companyName)) return false;
        if (!modalName.equals(model.modalName)) return false;
        if (!engineCapacity.equals(model.engineCapacity)) return false;
        if (gearBoxStatus != model.gearBoxStatus) return false;
        if (!seatingPlace.equals(model.seatingPlace)) return false;
        return productionYear!=(model.productionYear);

    }


}
