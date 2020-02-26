package edu.etsu.glosa.glosa.backend.models;

/**
 * class for definition a specified location (i.e., current user
 * location or expected, upcoming intersection for advisory
 *
 * @author Jim Tarlton
 */
public class Location {

    public Latitude latitude;
    public Longitude longitude;

    public Location(Latitude latitude, Longitude longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
