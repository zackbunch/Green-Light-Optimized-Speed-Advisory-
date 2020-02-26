package edu.etsu.glosa.glosa;


import android.app.Activity;

import com.google.gson.Gson;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import edu.etsu.glosa.glosa.backend.models.Direction;
import edu.etsu.glosa.glosa.backend.models.Intersection;
import edu.etsu.glosa.glosa.backend.transform.TransformTest;
import edu.etsu.glosa.glosa.backend.models.Latitude;
import edu.etsu.glosa.glosa.backend.models.Location;
import edu.etsu.glosa.glosa.backend.models.Vehicle;
import edu.etsu.glosa.glosa.backend.models.VehicleType;
import edu.etsu.glosa.glosa.backend.models.Longitude;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
class ExampleUnitTest {
    Activity act = new Activity();
    @Test
    void testFrontEndDataModels() throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("fake_input_data.json").getFile());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        Intersection[] intersections = new Gson().fromJson(bufferedReader, Intersection[].class);

        assertEquals(1, intersections[0].lanes.get(0).laneId);
        assertEquals(Direction.STRAIGHT, intersections[0].lanes.get(0).laneDirections.get(0).direction);

    }

    @Test
    void testTransformGetData() throws Exception {
        Vehicle v = new Vehicle(35.0, 0.0, new Location(new Latitude(435.344), new Longitude(333.333) ), VehicleType.SEDAN);
        Intersection intersection = new TransformTest(act).getSpatData(v);
        assertTrue(intersection.distance == 15.14 || intersection.distance == 19.55);
    }
}
