package com.company.oop.dealership.models.contracts;

import static java.lang.String.format;

public interface Motorcycle extends Vehicle{
    int CATEGORY_LEN_MIN = 3;
    int CATEGORY_LEN_MAX = 10;
    String CATEGORY_LEN_ERR = format("Category must be between %d and %d characters long!", CATEGORY_LEN_MIN, CATEGORY_LEN_MAX);
    String getCategory();

}
