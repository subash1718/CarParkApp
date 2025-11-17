package model;

public final class PaymentSuccess implements PaymentResult {
    private final String receiptId;
    public PaymentSuccess(String receiptId) { this.receiptId = receiptId; }
    public String receiptId() { return receiptId; }
    @Override public String toString() { return "PaymentSuccess{receiptId=" + receiptId + "}"; }
}
