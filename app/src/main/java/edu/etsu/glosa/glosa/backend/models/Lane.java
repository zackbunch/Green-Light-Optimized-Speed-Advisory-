package edu.etsu.glosa.glosa.backend.models;

import java.util.ArrayList;
import java.util.List;

/**
 * class for the range of recommendation objects
 *
 * @author Jim Tarlton
 */
public class Lane {

    public  int laneId;
    public List<LaneDirection> laneDirections = new ArrayList<>();

}
