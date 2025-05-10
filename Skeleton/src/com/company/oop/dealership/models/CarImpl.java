package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Car;
import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.utils.FormattingHelpers;
import com.company.oop.dealership.utils.ValidationHelpers;

import static java.lang.String.format;

public class CarImpl extends Vehicle implements Car {
    private int seats;
    public CarImpl(String name, String model, double price,int seats) {
        super(name, model, price);
        setSeats(seats);
    }

    @Override
    public int getSeats() {
        return this.seats;
    }
    private void setSeats(int seats){
        ValidationHelpers.validateIntRange(seats,CAR_SEATS_MIN,CAR_SEATS_MAX,CAR_SEATS_ERR);
        this.seats = seats;
    }

    @Override
    public String info() {
        return String.format("#.Car: %n Make: {%s} %n Model: {%s} %n Wheels: {%d} %n Price: ${%s} %n Seats: {%d}",
                getMake(),getModel(),getWheels(), FormattingHelpers.removeTrailingZerosFromDouble(getPrice()),getSeats());
    }

    //TODO
}