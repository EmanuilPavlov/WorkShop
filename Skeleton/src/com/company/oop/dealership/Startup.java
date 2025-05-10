package com.company.oop.dealership;

import com.company.oop.dealership.core.VehicleDealershipEngineImpl;
import com.company.oop.dealership.models.CarImpl;
import com.company.oop.dealership.models.contracts.Car;

public class Startup {

    public static void main(String[] args) {
        VehicleDealershipEngineImpl engine = new VehicleDealershipEngineImpl();
        engine.start();
    }

}
