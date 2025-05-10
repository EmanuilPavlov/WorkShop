package com.company.oop.dealership.models.contracts;

import com.company.oop.dealership.models.enums.VehicleType;

import static java.lang.String.format;

public interface Vehicle extends Commentable,Priceable{


    int MAKE_NAME_LEN_MIN = 2;
    int MAKE_NAME_LEN_MAX = 15;
    int MODEL_NAME_LEN_MIN = 1;
    int MODEL_NAME_LEN_MAX = 15;
    double PRICE_VAL_MIN = 0;
    double PRICE_VAL_MAX = 1000000;
    String MAKE_NAME_LEN_ERR = format("Make must be between %s and %s characters long!",MAKE_NAME_LEN_MIN,MAKE_NAME_LEN_MAX);
    String MODEL_NAME_LEN_ERR = format("Model must be between %s and %s characters long!",MODEL_NAME_LEN_MIN,MODEL_NAME_LEN_MAX);
    String PRICE_VAL_ERR = format("Price must be between %.1f and %.1f!",PRICE_VAL_MIN,PRICE_VAL_MAX);
    int getWheels();

    VehicleType getType();

    String getMake();

    String getModel();

    void addComment(Comment comment);

    void removeComment(Comment comment);
    String info();
}
