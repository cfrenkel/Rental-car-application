package com.example.this_user.ourproject5778_9075_4711_02.controller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.SearchView;

import com.example.this_user.ourproject5778_9075_4711_02.controller.listTools.NewAdapter;
import com.example.this_user.ourproject5778_9075_4711_02.R;
import com.example.this_user.ourproject5778_9075_4711_02.model.backend.BackEndInterface;
import com.example.this_user.ourproject5778_9075_4711_02.model.backend.DataSourceFactory;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.Branch;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.Car;
import com.example.this_user.ourproject5778_9075_4711_02.model.entities.Model;

import java.util.ArrayList;
import java.util.List;

public class CarsFragment extends Fragment  {

    BackEndInterface backEnd = DataSourceFactory.getBackEnd();
    ExpandableListView lv;
    SearchView search_view;
    ArrayAdapter<Car> adapter;


    public CarsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cars, container, false);

        lv = (ExpandableListView) view.findViewById(R.id.listview_car);
        lv.setDividerHeight(2);
        lv.setGroupIndicator(null);
        lv.setClickable(true);

        //group list
        List<Car> list = null;
        list = backEnd.getAvailableCar();
        final List<Car> finalCarList = list;

        //child list
        List<Object> child = initList(finalCarList);

        lv.setAdapter(new NewAdapter(finalCarList, child, getActivity()));
        return view;
    }


    /**
     * init the child list in list of strings
     * @param cars
     * @return
     */
    List<Object> initList(List<Car> cars) {
        List<Object> objects = new ArrayList<Object>();
        List<String> temp;

        for (Car c :
                cars) {
            temp = new ArrayList<String>();
            temp.add(carS(c));
            temp.add(branchS(backEnd.returnBranchByNum(c.getBranchNumOfParking())));
            temp.add(modelS(backEnd.returnModelByName(c.getModel())));

            objects.add(temp);

           // temp.removeAll(temp);

        }
        return objects;

    }

    /**
     * to string to car with string resources
     * @param car
     * @return
     */
    private String carS(Car car) {

        return getResources().getString(R.string.Car)  + ": \n" +
                getResources().getString(R.string.carNumber) + car.getCarNumber() + ",\n"+
                getResources().getString(R.string.branch_num_of_parking) + car.getBranchNumOfParking()+ ", \n"+
                getResources().getString(R.string.kilometers) + car.getKilometers() +
                '.';
    }

    /**
     * to string to branch with string resources
     * @param branch
     * @return
     */
    private String branchS(Branch branch) {

        return  getResources().getString(R.string.Branch) + "\n"+
                getResources().getString(R.string.branch_num) + branch.getBranchNum()  + ",\n"+
                getResources().getString(R.string.address) + branch.getAddress() +",\n" +
                getResources().getString(R.string.parkingSpacesNum) + branch.getParkingSpacesNum() +
                '.';
    }

    /**
     * to string to model with string resources
     * @param model
     * @return
     */
    private String modelS(Model model) {

        return getResources().getString(R.string.Model) + ": \n" +
                getResources().getString(R.string.code) + model.getModelCode() + ",\n"+
                getResources().getString(R.string.company_name) + model.getCompanyName() + ", \n" +
                getResources().getString(R.string.name) + model.getModalName() + ",\n" +
                getResources().getString(R.string.engine_capacity) + model.getEngineCapacity() + ",\n"+
                getResources().getString(R.string.seating_place) + model.getSeatingPlace() + ",\n"+
                getResources().getString(R.string.production_year)+ model.getProductionYear() +
                '.';

    }
}
