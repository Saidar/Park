package ru.parking.models;

import ru.parking.utils.ParkingUtils;
import ru.parking.enums.VehicleEnum;

public class Truck extends AbstarctVehicle {

    public Truck(){
        this.id = ParkingUtils.getID();
        this.vehicleClass = VehicleEnum.TRUCK;
    }
}
