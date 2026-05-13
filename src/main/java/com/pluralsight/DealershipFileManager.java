package com.pluralsight;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DealershipFileManager {
    private static final String FILE_NAME = "dealership.csv";

    /**
     * Reads inventory.csv and populates the inventory with a list of Vehicles
     *
     * @return A dealership object populated with vehicles
     * */
    public Dealership getDealership() {
        Dealership dealership = null;

        try {
            File file = new File(FILE_NAME);

            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            String line;

            String firstLine = br.readLine();
            if (firstLine != null) {
                String[] dealerName = firstLine.split("\\|");
                String name = dealerName[0];
                String address = dealerName[1];
                String phone = dealerName[2];
                dealership = new Dealership(name, address, phone);
            }


            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\|");

                if (tokens.length != 8) continue; // if line inside file doesnt have exactly 8 tokens skip it

                try {
                    int vin = Integer.parseInt(tokens[0]);
                    int year = Integer.parseInt(tokens[1]);
                    String make = tokens[2];
                    String model = tokens[3];
                    String vehicleType = tokens[4];
                    String color = tokens[5];
                    int odometer = Integer.parseInt(tokens[6]);
                    double price = Double.parseDouble(tokens[7]);

                    Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

                    dealership.addVehicle(vehicle);

                } catch (NumberFormatException e) { // catches error if file has bad lines (ex. price has a letter in it)
                    System.err.println("Bad line; " + line);
                }
            }
            br.close();
        } catch (IOException e) { // catches errors if we are not able to access the file
            System.err.println("Error reading file");
        }
        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));

            bw.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            bw.newLine();

            for (Vehicle vehicle : dealership.getAllVehicles()) {
                bw.write(vehicle.getVin() + "|" + vehicle.getYear() +
                        "|" + vehicle.getMake() + "|" + vehicle.getModel() +
                        "|" + vehicle.getVehicleType() + "|" + vehicle.getColor() +
                        "|" + vehicle.getOdometer() + "|" + vehicle.getPrice());
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            System.err.println("Error writing file");
        }
    }
}
