package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    Scanner scanner = new Scanner(System.in);

    public UserInterface() {}

    private void init() {
        DealershipFileManager manager = new DealershipFileManager();
        this.dealership = manager.getDealership();
    }

    public void display() {
        init();

        int command = -1;
        while (command != 99) {
            System.out.println("\n--- " + dealership.getName() + " ---");
            System.out.println("1 - Search by price");
            System.out.println("2 - Search by make/model");
            System.out.println("3 - Search by year");
            System.out.println("4 - Search by color");
            System.out.println("5 - Search by mileage");
            System.out.println("6 - Search by type");
            System.out.println("7 - List all vehicles");
            System.out.println("8 - Add a vehicle");
            System.out.println("9 - Remove a vehicle");
            System.out.println("99 - Quit");
            System.out.print("Enter command: ");

            command = scanner.nextInt();
            scanner.nextLine();

            switch (command) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 99 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid command.");
            }
        }
    }

    public void processGetByPriceRequest() {
        System.out.print("Please Enter a Min Price: ");
        double minPrice = scanner.nextDouble();
        System.out.print("Please Enter a Max Price: ");
        double maxPrice = scanner.nextDouble();
        scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByPrice(minPrice, maxPrice);
        displayVehicles(vehicles);
    }

    public void processGetByMakeModelRequest() {}

    public void processGetByYearRequest() {}

    public void processGetByColorRequest() {}

    public void processGetByMileageRequest() {}

    public void processGetByVehicleTypeRequest() {}

    /**
     * Get list of all vehicles from dealership and print them
     * */
    public void processGetAllVehiclesRequest() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    public void processAddVehicleRequest() {}

    public void processRemoveVehicleRequest() {}

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles != null) {
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
        }
    }
}
