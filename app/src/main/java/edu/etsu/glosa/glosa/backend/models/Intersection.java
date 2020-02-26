package edu.etsu.glosa.glosa.backend.models;

import java.util.ArrayList;
import java.util.List;

/**
 * class for the intersection collection
 *
 * @author Jim Tarlton
 */
public class Intersection {

    public double distance;
    public String name;
    public List<Alert> alerts = new ArrayList<>();
    public List<Lane> lanes = new ArrayList<>();

}
