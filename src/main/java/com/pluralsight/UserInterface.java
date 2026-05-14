package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;

    public UserInterface() {}

    private void init() {
        DealershipFileManager manager = new DealershipFileManager();
        this.dealership = manager.getDealership();
    }

    public void display() {
        init();
        Scanner scanner = new Scanner(System.in);

        String command = "";
        while (!command.equals("99")) {
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

                command = scanner.nextLine();

                switch (command) {
                    case "1" -> processGetByPriceRequest(scanner);
                    case "2" -> processGetByMakeModelRequest(scanner);
                    case "3" -> processGetByYearRequest(scanner);
                    case "4" -> processGetByColorRequest(scanner);
                    case "5" -> processGetByMileageRequest(scanner);
                    case "6" -> processGetByVehicleTypeRequest(scanner);
                    case "7" -> processGetAllVehiclesRequest();
                    case "8" -> processAddVehicleRequest(scanner);
                    case "9" -> processRemoveVehicleRequest(scanner);
                    case "99" -> System.out.println("Goodbye!");
                    default -> System.out.println("Invalid command.");
                }

        }
        scanner.close();
    }

    public void processGetByPriceRequest(Scanner scanner) {
        double minPrice = readDouble(scanner, "Please Enter a Min Price: ");
        double maxPrice = readDouble(scanner, "Please Enter a Max Price: ");

        List<Vehicle> vehicles = dealership.getVehiclesByPrice(minPrice, maxPrice);
        displayVehicles(vehicles);
    }

    public void processGetByMakeModelRequest(Scanner scanner) {
        String make = readString(scanner, "Please Enter a Make: ");
        String model = readString(scanner, "Please Enter a Model: ");

        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    public void processGetByYearRequest(Scanner scanner) {
        int minYear = readInt(scanner, "Please Enter a Min Year: ");
        int maxYear = readInt(scanner, "Please Enter a Max Year: ");

        List<Vehicle> vehicles = dealership.getVehiclesByYear(minYear, maxYear);
        displayVehicles(vehicles);
    }

    public void processGetByColorRequest(Scanner scanner) {
        String color = readString(scanner, "Please Enter a Color: ");

        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }

    public void processGetByMileageRequest(Scanner scanner) {
        int minMileage = readInt(scanner, "Please Enter a Min Mileage: ");
        int maxMileage = readInt(scanner, "Please Enter a Max Mileage: ");

        List<Vehicle> vehicles = dealership.getVehiclesByMileage(minMileage, maxMileage);
        displayVehicles(vehicles);
    }

    public void processGetByVehicleTypeRequest(Scanner scanner) {
        String vehicleType = readString(scanner, "Please Enter a Vehicle Type: ");

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

    public void processAddVehicleRequest(Scanner scanner) {
        System.out.println("\n--- Adding Vehicle ---");
        Vehicle vehicle = getVehicleInfo(scanner);
        dealership.addVehicle(vehicle);
        saveAndConfirm("Vehicle added successfully.");
    }

    public void processRemoveVehicleRequest(Scanner scanner) {
        System.out.println("\n--- Removing Vehicle ---");
        Vehicle vehicle = getVehicleInfo(scanner);
        dealership.removeVehicle(vehicle);
        saveAndConfirm("Vehicle removed successfully.");
    }

    public Vehicle getVehicleInfo(Scanner scanner) {
        int vin = readInt(scanner, "VIN: ");
        int year = readInt(scanner, "Year: ");
        String make = readString(scanner, "Make: ");
        String model = readString(scanner, "Model: ");
        String vehicleType = readString(scanner, "Vehicle Type: ");
        String color = readString(scanner, "Color: ");
        int odometer = readInt(scanner, "Mileage: ");
        double price = readDouble(scanner, "Price: ");

        return new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
    }

    private void saveAndConfirm(String message) {
        DealershipFileManager manager = new DealershipFileManager();
        manager.saveDealership(dealership);
        System.out.println(message);
    }

    private String readString(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int readInt(Scanner scanner, String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readString(scanner, prompt));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a whole number.");
            }
        }
    }

    private double readDouble(Scanner scanner, String prompt) {
        while (true) {
            try {
                return Double.parseDouble(readString(scanner, prompt));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number");
            }
        }
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles != null) {
            printHeader();
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
        }
    }

    private void printHeader() {
        System.out.println(String.format("%-10s %-6s %-12s %-12s %-12s %-10s %-12s %s",
                "VIN", "Year", "Make", "Model", "Type", "Color", "Mileage", "Price"));
        System.out.println("-".repeat(94));
    }
}
