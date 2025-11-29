package app;

import exceptions.NoAvailableSpotException;
import model.*;
import service.ParkingLot;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        ParkingSpot s1 = new ParkingSpot(1, VehicleType.CAR);
        ParkingSpot s2 = new ParkingSpot(2, VehicleType.MOTORCYCLE);
        ParkingSpot s3 = new ParkingSpot(3, VehicleType.CAR);

        ParkingLot lot = new ParkingLot(s1, s2, s3);
        Scanner sc = new Scanner(System.in);

        boolean running = true;

        while (running) {

            printMenu();
            System.out.print("Choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {

                case "1":
                    System.out.println("\n=== Available Parking Spots ===");
                    lot.getSpots().forEach(System.out::println);
                    break;

                case "2":
                    System.out.println("\n=== Park a Vehicle ===");
                    System.out.print("Enter vehicle type (CAR / MOTORCYCLE): ");
                    String typeInput = sc.nextLine().trim().toUpperCase();

                    Vehicle v = null;
                    if (typeInput.equals("CAR")) {
                        System.out.print("Enter registration number: ");
                        String reg = sc.nextLine().trim();
                        System.out.print("Enter color: ");
                        String color = sc.nextLine().trim();
                        v = new Car(reg, color); // your exact constructor
                    } else if (typeInput.equals("MOTORCYCLE")) {
                        System.out.print("Enter registration number: ");
                        String reg = sc.nextLine().trim();
                        v = new Motorcycle(reg); // your exact constructor
                    } else {
                        System.out.println("Invalid type.");
                        break;
                    }

                    try {
                        Ticket t = lot.park(v);
                        System.out.println("\nParked successfully!");
                        System.out.println("Ticket: " + t);
                    } catch (NoAvailableSpotException e) {
                        System.out.println("Park failed: " + e.getMessage());
                    }
                    break;

                case "3":
                    System.out.println("\n=== Active Tickets ===");
                    lot.listActive();
                    break;

                case "4":
                    System.out.println("\n=== Remove & Pay ===");
                    System.out.print("Enter registration number: ");
                    String regToRemove = sc.nextLine().trim();

                    PaymentResult result = lot.leaveAndPay(regToRemove);
                    System.out.println("\nPayment result: " + result);
                    break;

                case "5":
                    System.out.println("\n=== Parking History ===");
                    List<Ticket> hist = lot.getHistory();
                    if (hist.isEmpty()) System.out.println("(No history)");
                    else hist.forEach(System.out::println);
                    break;

                case "6":
                    System.out.println("\n=== Filter History by Hours ===");
                    System.out.print("Enter hours: ");
                    String hrsStr = sc.nextLine().trim();

                    int hours = 0;
                    try {
                        hours = Integer.parseInt(hrsStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number.");
                        break;
                    }

                    var filtered = lot.recentTicketsOlderThanHours(hours);
                    System.out.println("\n=== Results ===");
                    if (filtered.isEmpty()) System.out.println("(No results)");
                    else filtered.forEach(System.out::println);
                    break;

                case "7":
                    System.out.println("\n=== Add Notes (varargs) ===");
                    System.out.print("Enter notes separated by commas: ");
                    String notesLine = sc.nextLine();

                    if (notesLine.trim().isEmpty()) {
                        System.out.println("No notes added.");
                    } else {
                        String[] notes = notesLine.split("\\s*,\\s*");
                        lot.addNotes(notes);
                        System.out.println("Notes added.");
                    }
                    break;

                case "8":
                    System.out.println("\n=== Most Recent Ticket ===");
                    List<Ticket> history = lot.getHistory();
                    if (history.isEmpty()) System.out.println("(No tickets)");
                    else System.out.println(history.get(history.size() - 1));
                    break;

                case "9":
                    System.out.println("=== Last Payment Result ===");
                    PaymentResult last = lot.getPaymentService().getLastPayment();
                    if (last == null) {
                        System.out.println("(No payment has been made yet.)");
                    } else {
                        System.out.println(last);
                    }
                    break;


                case "10":
                    System.out.println("\n=== Clear History ===");
                    System.out.print("Are you sure? (yes/no): ");
                    if (sc.nextLine().trim().equalsIgnoreCase("yes")) {
                        lot.getHistory().clear();
                        System.out.println("History cleared.");
                    } else {
                        System.out.println("Cancelled.");
                    }
                    break;

                case "0":
                    running = false;
                    System.out.println("\nGoodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

            System.out.println();
        }

        sc.close();
    }

    private static void printMenu() {
        System.out.println("=== Car Parking System ===");
        System.out.println("1. View available parking spots");
        System.out.println("2. Park vehicle");
        System.out.println("3. View active parked vehicles");
        System.out.println("4. Remove vehicle & pay");
        System.out.println("5. View parking history");
        System.out.println("6. Filter history by hours");
        System.out.println("7. Add notes (varargs demo)");
        System.out.println("8. Show most recent ticket");
        System.out.println("9. Show last payment result");
        System.out.println("10. Clear history");
        System.out.println("0. Exit");
    }
}
