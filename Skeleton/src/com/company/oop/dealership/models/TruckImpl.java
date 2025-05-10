package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Truck;
import com.company.oop.dealership.utils.FormattingHelpers;
import com.company.oop.dealership.utils.ValidationHelpers;

import static java.lang.String.format;

public class TruckImpl extends Vehicle implements Truck {
    private int weight;
    public int getWeight(){
        return this.weight;
    }
    private void setWeight(int weight){
        ValidationHelpers.validateIntRange(weight,WEIGHT_CAP_MIN,WEIGHT_CAP_MAX,WEIGHT_CAP_ERR);
        this.weight = weight;
    }
    public TruckImpl(String name, String model, double price, int weight) {
        super(name, model, price);
        setWeight(weight);
    }

    @Override
    public String info() {
        return String.format("#.Truck: %n Make: {%s} %n Model: {%s} %n Wheels: {%d} %n Price: ${%s} %n Weight: {%d}",
                getMake(),getModel(),getWheels(), FormattingHelpers.removeTrailingZerosFromDouble(getPrice()),getWeight());
    }

    @Override
    public int getWeightCapacity() {
        return this.weight;
    }


    //TODO
}