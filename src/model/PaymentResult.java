package model;

/**
 * sealed interface and permitted implementations (Java 17+ / 21)
 */
public sealed interface PaymentResult permits PaymentSuccess, PaymentFailure { }
