package model;

public class ParkingSpot {
    private final int id;
    private final VehicleType allowed;

    public ParkingSpot(int id, VehicleType allowed) {
        this.id = id;
        this.allowed = allowed;
    }

    public int getId() { return id; }
    public VehicleType getAllowed() { return allowed; }

    @Override
    public String toString() {
        return "Spot#" + id + "(" + allowed + ")";
    }
}
