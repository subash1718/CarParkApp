package model;

public class Motorcycle extends Vehicle {
    public Motorcycle(String regNumber) {
        super(regNumber, 2);
    }

    @Override
    public VehicleType getType() { return VehicleType.MOTORCYCLE; }

    @Override
    public String toString() { return "Motorcycle{" + regNumber + "}"; }
}
