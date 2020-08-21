package ru.parking.logic;

import ru.parking.utils.ParkingUtils;
import ru.parking.models.AbstarctVehicle;
import ru.parking.models.Car;
import ru.parking.models.Truck;

public class QueGenerator implements Runnable{

    private Parking parking;
    private Thread t;

    public QueGenerator(){
        this.parking = Parking.getParking();
        this.t = new Thread(this);
        this.t.start();
    }

    @Override
    public synchronized void run() {
        while (true)
            generateVehicle();
    }

    public void generateVehicle(){
        long period = getPeriod();
        try {
            Thread.sleep(period);
            long start = System.currentTimeMillis();

            if(this.parking.hasPlaceInQue() && !ParkingUtils.FORCE_EXIT) {
                AbstarctVehicle vehicle = getVehicle();
                this.parking.addVehicleToQue(vehicle);
            }else{
                ParkingUtils.FORCE_EXIT = true;
            }

            Thread.sleep( ParkingUtils.TIME_PERIOD_QUE - period + System.currentTimeMillis() - start);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ~20% fro truck and ~80% for cars
    private AbstarctVehicle getVehicle(){
        return ParkingUtils.random.nextInt(10) < 9 ? new Car() : new Truck();
    }

    private int getPeriod(){
        return ParkingUtils.random.nextInt(ParkingUtils.TIME_PERIOD_QUE);
    }
}
