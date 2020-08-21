package ru.parking.models;

import ru.parking.utils.ParkingUtils;
import ru.parking.enums.VehicleEnum;

public class Car extends AbstarctVehicle {

    public Car(){
        this.id = ParkingUtils.getID();
        this.vehicleClass = VehicleEnum.CAR;
    }
}
