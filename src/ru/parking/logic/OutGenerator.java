package ru.parking.logic;

import ru.parking.utils.ParkingUtils;

public class OutGenerator implements Runnable {

    private Parking parking;
    private Thread t;

    public OutGenerator(){
        this.parking = Parking.getParking();
        this.t = new Thread(this);
        this.t.start();
    }

    @Override
    public synchronized void run() {
        while(true)
            takeVehicleOut();

    }

    private void takeVehicleOut() {
        long period = ParkingUtils.random.nextInt(ParkingUtils.TIME_PERIOD_OUT);
        try {
            Thread.sleep(period);
            long start = System.currentTimeMillis();

            if(this.parking.hasVehicle() && !ParkingUtils.FORCE_EXIT) {
                this.parking.getVehicleFromPark();
            }

            Thread.sleep( ParkingUtils.TIME_PERIOD_OUT - period + System.currentTimeMillis() - start);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
