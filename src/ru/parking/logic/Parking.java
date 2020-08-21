package ru.parking.logic;

import ru.parking.utils.ParkingUtils;
import ru.parking.models.AbstarctVehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Parking implements Runnable{

    private static Parking parking;
    private int parkSlots;
    private Queue<AbstarctVehicle> inputQue;
    private int queSlots;
    private List<AbstarctVehicle> parkingVehicle;
    private Thread t;


    private Parking(){
        this.inputQue = new ArrayBlockingQueue<>(ParkingUtils.MAX_QUE);
        this.parkingVehicle = new ArrayList<>();
        this.t = new Thread(this);
        this.t.start();
    }

    public static synchronized Parking getParking(){
        if(parking == null) {
            parking = new Parking();
        }
        return parking;
    }

    @Override
    public synchronized void run() {
        while (true)
            takeVehicleFromQue();
    }

    private void takeVehicleFromQue() {
        if(!this.inputQue.isEmpty() && parkingHasPlace()){
            AbstarctVehicle vechicle = inputQue.remove();
            this.parkingVehicle.add(vechicle);
            this.parkSlots += vechicle.getVehicleClass().getCode();
            this.queSlots--;
            vechicle.getIntoParkPlace();
        }
    }

    private boolean parkingHasPlace() {
        return this.parkSlots < ParkingUtils.MAX_SLOTS;
    }

    public void addVehicleToQue(AbstarctVehicle vehicle) {
        this.inputQue.add(vehicle);
        this.queSlots++;
        vehicle.gotIntoQue();
    }
    public void getVehicleFromPark() {
        AbstarctVehicle vehicle = this.parkingVehicle.remove(ParkingUtils.random.nextInt(this.parkingVehicle.size()));
        this.parkSlots -= vehicle.getVehicleClass().getCode();
        vehicle.leftParkPlace();
    }

    public Parking copy() {
        Parking parking = new Parking();
        parking.setParkingVehicle(new ArrayList<>(this.getParkingVehicle()));
        parking.setParkSlots(this.getParkSlots());
        parking.setQueSlots(this.getQueSlots());
        return parking;
    }

    public int getFreeParkSlots() {
        return ParkingUtils.MAX_SLOTS - this.parkSlots;
    }

    public List<AbstarctVehicle> getParkingVehicle(){return this.parkingVehicle; }

    public boolean hasVehicle() {
        return this.parkingVehicle.size() > 0;
    }

    public boolean hasPlaceInQue() {
        return this.queSlots < ParkingUtils.MAX_QUE;
    }

    public Queue<AbstarctVehicle> getQue() {
        return this.inputQue;
    }

    public int getParkSlots() {
        return parkSlots;
    }

    public void setParkSlots(int parkSlots) {
        this.parkSlots = parkSlots;
    }

    public int getQueSlots() {
        return queSlots;
    }

    public void setQueSlots(int queSlots) {
        this.queSlots = queSlots;
    }

    public void setParkingVehicle(List<AbstarctVehicle> parkingVehicle) {
        this.parkingVehicle = parkingVehicle;
    }
}
