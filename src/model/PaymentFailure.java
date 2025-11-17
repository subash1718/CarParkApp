package model;

public final class PaymentFailure implements PaymentResult {
    private final String reason;
    public PaymentFailure(String reason) { this.reason = reason; }
    public String reason() { return reason; }
    @Override public String toString() { return "PaymentFailure{reason=" + reason + "}"; }
}
