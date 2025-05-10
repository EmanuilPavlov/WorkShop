package com.company.oop.dealership.models.contracts;

import static java.lang.String.format;

public interface Truck extends Vehicle{

    int WEIGHT_CAP_MIN = 1;
    int WEIGHT_CAP_MAX = 100;
    String WEIGHT_CAP_ERR = format("Weight capacity must be between %d and %d!", WEIGHT_CAP_MIN, WEIGHT_CAP_MAX);
    int getWeightCapacity();

}
