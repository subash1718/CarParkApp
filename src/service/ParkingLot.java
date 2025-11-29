package service;

import model.*;
import exceptions.NoAvailableSpotException;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.function.Predicate;

public class ParkingLot {
    private final List<ParkingSpot> spots = new ArrayList<>();
    private final Map<String, Ticket> active = new HashMap<>();
    private final List<Ticket> history = new ArrayList<>();
    private final PaymentService paymentService = new PaymentService();

    public ParkingLot(ParkingSpot... initialSpots) {
        // varargs usage
        Collections.addAll(spots, initialSpots);
    }

    // defensive copy when returning spots
    public List<ParkingSpot> getSpots() {
        return new ArrayList<>(spots);
    }
    // Java 23 feature: Sequenced Collections API (List.getLast)
    public Ticket getMostRecentTicket() {
        if (history.isEmpty()) return null;
        return history.getLast(); // Java 23 method
    }


    // park vehicle - checked exception if no spot
    public Ticket park(Vehicle v) throws NoAvailableSpotException {
        // find first available spot that accepts this vehicle type and is not occupied
        var spotOpt = spots.stream()
                .filter(s -> s.getAllowed() == v.getType())
                .filter(s -> active.values().stream().noneMatch(t -> t.regNumber().equals(v.getRegNumber())))
                .findFirst();

        if (spotOpt.isEmpty()) throw new NoAvailableSpotException("No available spot for " + v.getType());
        var ticketId = "T" + Instant.now().toEpochMilli();
        var ticket = new Ticket(ticketId, v.getRegNumber(), v.getType(), Instant.now());
        active.put(v.getRegNumber(), ticket);
        history.add(ticket);
        history.addLast(ticket);  // Java 23 method
        return ticket;
    }

    // leave and pay - demonstrates switch expression and pattern matching not needed but we use a switch on type
    public PaymentResult leaveAndPay(String regNumber) {
        var t = active.remove(regNumber);
        if (t == null) return new PaymentFailure("No active ticket for " + regNumber);

        var duration = Duration.between(t.entryTime(), Instant.now());
        // switch expression for fee multiplier by vehicle type
        double multiplier = switch (t.type()) {
            case CAR -> 1.0;
            case MOTORCYCLE -> 0.6;
            case VAN -> 1.4;
        };
        double base = paymentService.calculateFee(duration);
        double total = base * multiplier;
        // method reference when printing receipts elsewhere; here call pay
        return paymentService.pay(regNumber, total);
    }
    public PaymentService getPaymentService() {
        return paymentService;
    }


    // list active tickets (method reference)
    public void listActive() {
        active.values().forEach(System.out::println); // method reference
    }

    // filter history using Predicate and method reference
    public List<Ticket> recentTicketsOlderThanHours(int hours) {
        Predicate<Ticket> olderThan = t -> t.entryTime().isBefore(Instant.now().minus(Duration.ofHours(hours)));
        var result = new ArrayList<Ticket>();
        history.stream().filter(olderThan).forEach(result::add); // method reference
        return result;
    }

    // demonstrate varargs again
    public void addNotes(String... notes) {
        var sb = new StringBuilder();
        for (var n : notes) sb.append(n).append("; ");
        System.out.println("Notes: " + sb);
    }

    // defensive copy for history
    public List<Ticket> getHistory() {
        return new ArrayList<>(history);
    }
}
