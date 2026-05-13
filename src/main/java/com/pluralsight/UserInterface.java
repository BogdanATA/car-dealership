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

    public void processGetByMakeModelRequest() {
        System.out.print("Please Enter a Make: ");
        String make = scanner.nextLine();
        System.out.print("Please Enter a Model: ");
        String model = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    public void processGetByYearRequest() {
        System.out.print("Please Enter a Min Year: ");
        int minYear = scanner.nextInt();
        System.out.print("Please Enter a Max Year: ");
        int maxYear = scanner.nextInt();
        scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByYear(minYear, maxYear);
        displayVehicles(vehicles);
    }

    public void processGetByColorRequest() {
        System.out.print("Please Enter a Color: ");
        String color = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }

    public void processGetByMileageRequest() {
        System.out.print("Please Enter a Min Mileage: ");
        int minMileage = scanner.nextInt();
        System.out.print("Please Enter a Max Mileage: ");
        int maxMileage = scanner.nextInt();
        scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByMileage(minMileage, maxMileage);
        displayVehicles(vehicles);
    }

    public void processGetByVehicleTypeRequest() {
        System.out.print("Please Enter a Vehicle Type: ");
        String vehicleType = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByType(vehicleType);
        displayVehicles(vehicles);
    }

    /**
     * Get list of all vehicles from dealership and print them
     * */
    public void processGetAllVehiclesRequest() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    public void processAddVehicleRequest() {
        System.out.println("Please Fill Out All Required Fields");
        System.out.print("VIN: ");
        int vin = scanner.nextInt();
        System.out.print("Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Vehicle Type: ");
        String vehicleType = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("Mileage: ");
        int odometer = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(vehicle);
        DealershipFileManager manager = new DealershipFileManager();
        manager.saveDealership(dealership);
        System.out.println("Vehicle added successfully.");
    }

    public void processRemoveVehicleRequest() {}

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles != null) {
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
        }
    }
}
