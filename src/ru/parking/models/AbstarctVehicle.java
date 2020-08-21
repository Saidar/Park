package ru.parking.models;

import ru.parking.enums.VehicleEnum;

public abstract class AbstarctVehicle {

    protected int id;
    protected VehicleEnum vehicleClass;

    public void getIntoParkPlace() {
        System.out.println(vehicleClass.name() + " with id = " + this.id + " got into the parking.");
    }

    public void leftParkPlace() {
        System.out.println(vehicleClass.name() + " with id = " + this.id + " left the parking.");
    }

    public void gotIntoQue() {
        System.out.println(vehicleClass.name() + " with id = " + this.id + " got into the que.");
    }

    public VehicleEnum getVehicleClass(){
        return this.vehicleClass;
    }
}
