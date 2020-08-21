package ru.parking.utils;

import java.util.Random;
import java.util.Scanner;

public class ParkingUtils {

    public static int MAX_SLOTS = 2;
    public static int MAX_QUE = 3;
    public static int TIME_LOGGER = 5000;
    public static int TIME_PERIOD_QUE = 1000;
    public static int TIME_PERIOD_OUT = 2000;
    public static boolean FORCE_EXIT = false;
    public static Random random = new Random();
    private static int id;
    private static final String BORED = "bored";
    private static Scanner sc = new Scanner(System.in);


    // only one thread will use it, therefore no need in safety
    public static int getID(){
        return id++;
    }

    public static boolean isNumeric(String s) {
        return s != null && s.matches("^[1-9]\\d*$");
    }

    public static boolean isNewParking(){
        while(true){
            String inputLine = sc.nextLine();
            if(inputLine.equalsIgnoreCase("Y") || inputLine.equalsIgnoreCase("N")){
                return inputLine.equalsIgnoreCase("Y") ? true : false;
            }else{
                System.out.println("The input is incorrect, please select 'Y' or 'N'");
            }
        }
    }

    public static void fillInputParams() {
        System.out.println("So, lets start. I will need from you some numeric values as input.");

        System.out.println("Please tell me, how many parking place should be?");
        MAX_SLOTS = getNumber();

        System.out.println("Okay, now, please tell me, how many vehicles could be in the que?");
        MAX_QUE = getNumber();

        System.out.println("Further, I need to know the time interval for input vehicles? In milliseconds, please.");
        MAX_SLOTS = getNumber();

        System.out.println("Fine, finally, I need to know the time interval for output vehicles? In milliseconds, please.");
        MAX_SLOTS = getNumber();

    }

    private static int getNumber() {
        while (true){
            String inputLine = sc.nextLine();
            if(isNumeric(inputLine)){
                return Integer.parseInt(inputLine);
            } else if(inputLine.equalsIgnoreCase(BORED)){
                System.out.println("You decided to quit the simulation. Chaio!");
                System.exit(0);
            } else{
                System.out.println("The input is not correct, please, give a number or write 'bored' if you want to exit the simulation.");
            }
        }
    }

    public static void startGame() {

        System.out.println("********************************");
        System.out.println("Welcome to the Parking Simulation");
        System.out.println("********************************");

        System.out.println("Would you like to start?(Y/N)");
        boolean game = isNewParking();
        if(!game){
            System.out.println("Okay, see you later, bye!");
            System.exit(0);
        }
    }
}
