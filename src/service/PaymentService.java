package service;

import model.*;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

/**
 * small payment service that implements Payable
 */
public class PaymentService implements Payable {

    // method reference usage will appear where we print receipts

    @Override
    public double calculateFee(Duration duration) {
        long minutes = duration.toMinutes();
        long hours = Math.max(1, (int) Math.ceil(minutes / 60.0));
        // switch expression for vehicle-based rates could be used, but this service is generic
        return calculateHourlyFee(hours, 2.5); // €2.5 per hour base
    }

    public PaymentResult pay(String regNumber, double amount) {
        // In real app we'd interact with gateway. Here: accept if amount > 0
        if (amount <= 0) return new PaymentFailure("Invalid amount");
        String receiptId = "R-" + UUID.randomUUID().toString().substring(0, 8);
        ImmutableReceipt r = new ImmutableReceipt(receiptId, amount, Instant.now());
        // return success with receipt id
        return new PaymentSuccess(r.getReceiptId());
    }
}
