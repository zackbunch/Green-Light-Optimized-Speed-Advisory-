package edu.etsu.glosa.glosa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import Facade.TrafficSignaledIntersectionService;
import edu.etsu.glosa.glosa.backend.models.Color;
import edu.etsu.glosa.glosa.backend.models.Intersection;
import edu.etsu.glosa.glosa.backend.models.Lane;
import edu.etsu.glosa.glosa.backend.models.LaneDirection;
import edu.etsu.glosa.glosa.backend.transform.Transform;
import edu.etsu.glosa.glosa.backend.transform.iTransform;
import edu.etsu.glosa.glosa.backend.models.Vehicle;
import edu.etsu.glosa.glosa.backend.models.VehicleType;
import edu.etsu.glosa.glosa.backend.models.Latitude;
import edu.etsu.glosa.glosa.backend.models.Location;
import edu.etsu.glosa.glosa.backend.models.Longitude;
import edu.etsu.glosa.glosa.ui.models.Adapters.TrafficLightControllerAdapter;
import edu.etsu.glosa.glosa.ui.controllers.TrafficLightController;
import edu.etsu.glosa.glosa.ui.views.IMainActivityView;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements IMainActivityView {
    private ScheduledExecutorService mScheduler = Executors.newSingleThreadScheduledExecutor();

    private Vehicle mVehicle;
    private final int MAX_COLUMN = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Obtain vehicle speed
        double mVehicleSpeed = 140;

        //Obtain vehicle orientation
        double mOrientation = 0.0;

        //Obtain vehicle current location
        Location mLocation = new Location(new Latitude(36.114), new Longitude(-115.1640449));

        //Obtain vehicle type
        VehicleType mVehicleType = VehicleType.TRUCK;

        mVehicle = new Vehicle(mVehicleSpeed, mOrientation, mLocation, mVehicleType);

        setContentView(R.layout.activity_main);

        // Populate the UI with traffic controllers
        // populateUI();

        mScheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                populateUI();
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    private void populateUI() {
        try {
            iTransform transform = new Transform(this, new TrafficSignaledIntersectionService());

            Intersection intersection = transform.getSpatData(mVehicle);

            //Sample implementation, will fix this part with real data
            GridView gridView = findViewById(R.id.list);
            int numberOfTrafficLights = getNumberOfLights(intersection.lanes);

            ArrayList<TrafficLightController> list = new ArrayList<TrafficLightController>();
            for (int i = 0; i < intersection.lanes.size(); i++) {
                Lane lane = intersection.lanes.get(i);
                Log.i("____Lane ID____", "" + lane.laneId);
                for (LaneDirection direction : lane.laneDirections) {
                    TrafficLightController trafficLightController = new TrafficLightController(this);
                    Log.i("Speed_duration in milli", "" + direction.phase.duration);
                    Log.i("Phase", "" + direction.phase.color);
                    Log.i("Direction", "" + direction.direction);
                    trafficLightController.setSpeedRecommendation(35);
                    trafficLightController.setDirection(direction.direction);
                    trafficLightController.setTrafficLightStatus(direction.phase.color/*direction.phase.color*/, 25 /*direction.phase.duration*/);
                    trafficLightController.invalidate();
                    list.add(trafficLightController);
                }
            }

            TrafficLightControllerAdapter adapter = new TrafficLightControllerAdapter(this, list);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    gridView.setNumColumns(numberOfTrafficLights);
                    gridView.setAdapter(adapter);
                }
            });


        } catch (Exception ex) {
            Log.i("MainActivity", ex.getMessage());
            ex.printStackTrace();
        }

    }

    private int getNumberOfLights(List<Lane> lanes) {
        int count = 0;
        for (Lane lane : lanes) {
            count += lane.laneDirections.size();
        }
        return count;
    }

}
