package model;

import java.time.Instant;

/**
 * record demonstrates an immutable data carrier (audit-friendly)
 */
public record Ticket(String id, String regNumber, VehicleType type, Instant entryTime) { }
