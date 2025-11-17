import model.*;
import service.*;
import exceptions.NoAvailableSpotException;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== CarParkApp demo ===");

        // create spots
        var s1 = new ParkingSpot(1, VehicleType.CAR);
        var s2 = new ParkingSpot(2, VehicleType.MOTORCYCLE);
        var s3 = new ParkingSpot(3, VehicleType.CAR);

        var lot = new ParkingLot(s1, s2, s3);

        // demonstrate LVTI (var) and polymorphism
        Vehicle v1 = new Car("ABC-123", "red");
        Vehicle v2 = new Motorcycle("BIKE-1");

        try {
            var t1 = lot.park(v1); // checked exception handled here
            System.out.println("Parked: " + t1);
            var t2 = lot.park(v2);
            System.out.println("Parked: " + t2);
        } catch (NoAvailableSpotException e) {
            System.err.println("Park failed: " + e.getMessage());
        }

        // list active
        System.out.println("\nActive tickets:");
        lot.listActive();

        // simulate time by sleeping or pretend by modifying entryTime (we'll just wait small)
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

        // pay & leave for car
        var result = lot.leaveAndPay("ABC-123");
        System.out.println("Payment result: " + result);

        // show recent older tickets (lambda predicate)
        List<Ticket> older = lot.recentTicketsOlderThanHours(0); // everything older than 0 hours
        System.out.println("History older than 0 hours:");
        older.forEach(System.out::println);

        // demonstrate varargs and LVTI
        lot.addNotes("First note", "Second note", "Paid by card");

        System.out.println("=== Demo complete ===");
    }
}
