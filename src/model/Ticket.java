package model;

import java.time.Instant;

public record Ticket(String id, String regNumber, VehicleType type, Instant entryTime) { }
