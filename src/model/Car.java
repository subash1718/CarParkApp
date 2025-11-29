package model;

public class Car extends Vehicle {
    private String color;

    // primary constructor that sets color
    public Car(String regNumber, String color) {
        super(regNumber, 4);
        this.color = color;
    }

    // convenience constructor delegates to primary (this()) — single assignment
    public Car(String regNumber) {
        this(regNumber, "unknown");
    }

    @Override
    public VehicleType getType() { return VehicleType.CAR; }

    @Override
    public String toString() {
        return "Car{" + regNumber + ", color=" + color + "}";
    }
}
