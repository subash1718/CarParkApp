package service;

import java.time.Duration;

/**
 * interface showing default, static and private methods (Java 9+)
 */
public interface Payable {
    double calculateFee(Duration duration);

    // default method example
    default double calculateHourlyFee(long hours, double perHour) {
        return round2(hours * perHour);
    }

    // static helper
    static String formatCurrency(double amount) {
        return String.format("€%.2f", amount);
    }

    // private helper (not visible to implementors)
    private static double round2(double x) {
        return Math.round(x * 100.0) / 100.0;
    }
}
