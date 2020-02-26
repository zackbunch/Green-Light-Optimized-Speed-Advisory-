package edu.etsu.glosa.glosa.backend.transform;

import edu.etsu.glosa.glosa.backend.models.Intersection;
import edu.etsu.glosa.glosa.backend.models.Vehicle;

public interface iTransform {
    Intersection getSpatData(Vehicle vehicle) throws Exception;
}
