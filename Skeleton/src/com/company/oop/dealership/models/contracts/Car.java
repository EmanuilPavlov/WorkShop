package com.company.oop.dealership.models.contracts;

import static java.lang.String.format;

public interface Car extends Vehicle,Commentable{
    int CAR_SEATS_MIN = 1;
    int CAR_SEATS_MAX = 10;
    String CAR_SEATS_ERR = format("Seats must be between %d and %d!", CAR_SEATS_MIN, CAR_SEATS_MAX);
    int getSeats();

}
