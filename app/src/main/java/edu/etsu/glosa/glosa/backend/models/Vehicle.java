
package edu.etsu.glosa.glosa.backend.models;

/**
 * class which represents the users vehicle and Android data
 *
 * @author Jim Tarlton
 */
public class Vehicle {

    private double speed;
    private double orientation;
    private Location location;
    private VehicleType vehicle_type;

    public Vehicle(double speed, double orientation, Location location, VehicleType vehicle_type) {
        this.speed = speed;
        this.orientation = orientation;
        this.location = location;
        this.vehicle_type = vehicle_type;
    }

    public VehicleType getVehicle_type() {
        return vehicle_type;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getSpeed() {
        return speed;
    }

    public double getOrientation() {
        return orientation;
    }

    public Location getLocation() {
        return location;
    }

}
