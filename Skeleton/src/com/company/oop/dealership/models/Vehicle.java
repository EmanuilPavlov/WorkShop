package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.models.contracts.Commentable;
import com.company.oop.dealership.models.contracts.Motorcycle;
import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle implements com.company.oop.dealership.models.contracts.Vehicle, Commentable {
    private String make;
    private String model;
    private double price;
    private int wheels;
    private VehicleType type;
    private List<Comment> comments;
    public String getMake(){
        return this.make;
    }
    private void setMake(String make){
        ValidationHelpers.validateStringLength(make,MAKE_NAME_LEN_MIN,MAKE_NAME_LEN_MAX,MAKE_NAME_LEN_ERR);
        this.make = make;
    }
    public String getModel(){
        return this.model;
    }
    private void setModel(String model){
        ValidationHelpers.validateStringLength(model,MODEL_NAME_LEN_MIN,MODEL_NAME_LEN_MAX,MODEL_NAME_LEN_ERR);
        this.model= model;
    }
    public double getPrice(){
        return this.price;
    }
    private void setPrice(double price){
        ValidationHelpers.validateDecimalRange(price,PRICE_VAL_MIN,PRICE_VAL_MAX,PRICE_VAL_ERR);
        this.price = price;
    }
    public int getWheels(){
        return this.wheels;
    }
    private void setWheels(){
        if (this instanceof CarImpl){
            this.wheels = 4;
            this.type = VehicleType.CAR;
        }
        else if (this instanceof MotorcycleImpl){
            this.wheels = 2;
            this.type = VehicleType.MOTORCYCLE;
        }
        else {
            this.wheels = 8;
            this.type = VehicleType.TRUCK;
        }
    }
    @Override
    public VehicleType getType() {
        return this.type;
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }
    @Override
    public void addComment(Comment comment) {
        if (comments.isEmpty() || !comments.contains(comment)){
            comments.add(comment);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void removeComment(Comment comment) {
        if (!comments.contains(comment)){
            throw new IllegalArgumentException();
        }
        comments.remove(comment);
    }

    public Vehicle(String name, String model, double price){
        setMake(name);
        setModel(model);
        setPrice(price);
        setWheels();
        comments = new ArrayList<Comment>();
    }

}
