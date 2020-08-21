package ru.parking.utils;

import ru.parking.logic.Parking;
import ru.parking.models.AbstarctVehicle;

import java.util.List;

public class Logger implements Runnable {

    private Parking parking;
    private Thread t;
    private int cars;
    private int trucks;


    public Logger(){
        this.t = new Thread(this);
        this.t.start();
    }

    @Override
    public synchronized void run() {
        while (true)
            logParkSpace();
    }

    private void logParkSpace() {
        try {
            Thread.sleep(ParkingUtils.TIME_LOGGER);
            countVehicles();
            printLog();

            if(ParkingUtils.FORCE_EXIT){
                System.out.println("The que is full. Exit.");
                System.exit(0);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void countVehicles(){
        this.parking = Parking.getParking().copy();
        List<AbstarctVehicle> list = this.parking.getParkingVehicle();
        for(AbstarctVehicle vehicle : list){
            switch (vehicle.getVehicleClass()) {
                case CAR : this.cars++; break;
                case TRUCK: this.trucks++; break;
                default: break;
            }
        }
    }

    private void printLog(){
        System.out.println( "****************************************** \n" +
                "Free slots: " + this.parking.getFreeParkSlots() + ".\n" +
                "Taken slots: " + this.cars + " car(s), " + this.trucks + " truck(s).\n" +
                "Cars in que: " + this.parking.getQueSlots() + ".\n" +
                "******************************************");
        this.cars = 0;
        this.trucks = 0;

    }
}
