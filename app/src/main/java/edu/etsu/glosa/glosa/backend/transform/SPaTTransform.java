
package edu.etsu.glosa.glosa.backend.transform;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import SAEJ7235Integrations.MAPStructure.Connection;
import SAEJ7235Integrations.MAPStructure.GenericLane;
import SAEJ7235Integrations.MAPStructure.IntersectionGeometry;
import SAEJ7235Integrations.SPaTStructure.IntersectionState;
import SAEJ7235Integrations.SPaTStructure.MovementEvent;
import SAEJ7235Integrations.SPaTStructure.MovementState;
import SAEJ7235Integrations.SPaTStructure.SignalGroupID;
import edu.etsu.glosa.glosa.backend.models.ActiveStatus;
import edu.etsu.glosa.glosa.backend.models.Color;
import edu.etsu.glosa.glosa.backend.models.Direction;
import edu.etsu.glosa.glosa.backend.models.Intersection;
import edu.etsu.glosa.glosa.backend.models.Lane;
import edu.etsu.glosa.glosa.backend.models.LaneDirection;
import edu.etsu.glosa.glosa.backend.models.TrafficLight;
import edu.etsu.glosa.glosa.backend.models.Vehicle;
import SAEJ7235Integrations.SPaTStructure.SPAT;
import SAEJ7235Integrations.MAPStructure.MapData;

/**
 * test class for replicating the transformation of a GLOSA response
 *
 * @author Jim Tarlton
 */
public class SPaTTransform implements iTransform {
    private Context mContext;
    private SPAT spat;
    private MapData mapData;
    private SignalGroupID signalGroupID;

    public SPaTTransform(Context context) {
        mContext = context;
    }

    @Override
    public Intersection getSpatData(Vehicle vehicle) throws Exception {
//            call data from backend here
        Intersection intersection = new Intersection();
        IntersectionState spat_intersections = spat.intersections.elements[0];
        intersection.name = spat_intersections.name.getValue();

        for (int i = 0; i < spat_intersections.states.getLength(); i++) {
            signalGroupID = spat_intersections.states.elements[i].signalGroup;
            Lane lane = new Lane();
            lane.laneId = (int) signalGroupID.getValue();
            lane.laneDirections = getLaneDirections(spat_intersections.states.elements[i]);
            intersection.lanes.add(lane);
        }
        return intersection;
    }

    private List<LaneDirection> getLaneDirections(MovementState movmentState) {
        List<LaneDirection> laneDirections = new ArrayList<>();
        for (MovementEvent movementEvent : movmentState.state_time_speed.elements) {
            TrafficLight trafficLight = new TrafficLight();
            switch (movementEvent.eventState.getValue()) {
                case 6:
                    trafficLight.color = Color.GREEN;
                    break;
                case 3:
                    trafficLight.color = Color.RED;
            }
            trafficLight.duration = (int) (movementEvent.timing.minEndTime.getValue() - System.currentTimeMillis());
            trafficLight.activeStatus = ActiveStatus.ACTIVE;
            LaneDirection laneDirection = new LaneDirection();
            laneDirection.phase = trafficLight;
            laneDirection.direction = Direction.valueOf(get_direction().toUpperCase());
            laneDirections.add(laneDirection);
        }
        return laneDirections;
    }

    private String get_direction() {
        for (IntersectionGeometry intersection : mapData.intersections.elements) {
            for (GenericLane lane : intersection.laneSet.elements) {
                for (Connection conn : lane.connectsTo.elements) {
                    if (conn.signalGroup == signalGroupID) {
                        return conn.connectingLane.maneuver.getValue();
                    }
                }
            }
        }
        return "";
    }
}
