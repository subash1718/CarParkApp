package model;


public abstract class Vehicle {
    protected final String regNumber;
    protected final int wheels;

    public Vehicle(String regNumber) {
        this(regNumber, 4); // this() calls another constructor
    }

    public Vehicle(String regNumber, int wheels) {
        this.regNumber = regNumber; // this. refers to field
        this.wheels = wheels;
    }

    public abstract VehicleType getType();

    // method overloading
    public String info() { return "Reg: " + regNumber; }
    public String info(boolean verbose) {
        if (verbose) return "Vehicle[reg=" + regNumber + ", wheels=" + wheels + "]";
        return info();
    }

    public String getRegNumber() { return regNumber; }
    public int getWheels() { return wheels; }
}
