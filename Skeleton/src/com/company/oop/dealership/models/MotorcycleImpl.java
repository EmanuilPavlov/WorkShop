package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.models.contracts.Motorcycle;
import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.utils.FormattingHelpers;
import com.company.oop.dealership.utils.ValidationHelpers;

import static java.lang.String.format;

public class MotorcycleImpl extends Vehicle implements Motorcycle {
    private String category;
    public String getCategory(){
        return this.category;
    }
    private void setCategory(String category){
        ValidationHelpers.validateStringLength(category,CATEGORY_LEN_MIN,CATEGORY_LEN_MAX,CATEGORY_LEN_ERR);
        this.category = category;
    }
    public MotorcycleImpl(String name, String model, double price, String category) {
        super(name, model, price);
        setCategory(category);
    }

    @Override
    public String info() {
        return String.format("#.Motorcycle: %n Make: {%s} %n Model: {%s} %n Wheels: {%d} %n Price: ${%s} %n Category: {%s}",
                getMake(),getModel(),getWheels(), FormattingHelpers.removeTrailingZerosFromDouble(getPrice()),getCategory());
    }



    //TODO
}