package ru.parking;

import ru.parking.logic.OutGenerator;
import ru.parking.logic.QueGenerator;
import ru.parking.utils.Logger;
import ru.parking.utils.ParkingUtils;

public class Main {

    public static void main(String[] args) {

        ParkingUtils.startGame();
        ParkingUtils.fillInputParams();

        System.out.println("I got all info, let's start!");
        System.out.println("********************************");
        QueGenerator queGenerator = new QueGenerator();
        OutGenerator outGenerator = new OutGenerator();
        Logger logger = new Logger();
        Logger logger1 = new Logger();
        Logger logger2 = new Logger();
        Logger logger3 = new Logger();





    }


}
