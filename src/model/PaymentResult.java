package model;

public sealed interface PaymentResult permits PaymentSuccess, PaymentFailure { }
