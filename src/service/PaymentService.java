package service;

import model.*;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;


public class PaymentService implements Payable {

    // store last payment made
    private PaymentResult lastPayment;

    @Override
    public double calculateFee(Duration duration) {
        long minutes = duration.toMinutes();
        long hours = Math.max(1, (long) Math.ceil(minutes / 60.0));
        return calculateHourlyFee(hours, 2.5); // €2.5 per hour base
    }

    public PaymentResult pay(String regNumber, double amount) {
        if (amount <= 0) {
            lastPayment = new PaymentFailure("Invalid amount");
            return lastPayment;
        }

        String receiptId = "R-" + UUID.randomUUID().toString().substring(0, 8);
        ImmutableReceipt receipt = new ImmutableReceipt(receiptId, amount, Instant.now());

        // create success object
        PaymentSuccess result = new PaymentSuccess(receipt.getReceiptId());

        // store last payment
        lastPayment = result;

        return result;
    }

    // new method: return last payment result
    public PaymentResult getLastPayment() {
        return lastPayment;
    }
}
