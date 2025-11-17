package model;

import java.time.Instant;

/**
 * custom immutable type (no setters, defensive copying if had mutable fields)
 */
public final class ImmutableReceipt {
    private final String receiptId;
    private final double amount;
    private final Instant time;

    public ImmutableReceipt(String receiptId, double amount, Instant time) {
        this.receiptId = receiptId;
        this.amount = amount;
        this.time = time; // Instant is immutable; if mutable, we'd copy
    }

    public String getReceiptId() { return receiptId; }
    public double getAmount() { return amount; }
    public Instant getTime() { return time; }

    @Override
    public String toString() {
        return "Receipt{id=" + receiptId + ", amount=" + amount + ", time=" + time + "}";
    }
}
