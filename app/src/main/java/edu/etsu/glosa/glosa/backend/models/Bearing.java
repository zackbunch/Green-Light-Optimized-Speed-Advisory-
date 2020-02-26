package edu.etsu.glosa.glosa.backend.models;

public class Bearing {

    public int bearing;

    private static final Bearing ourInstance = new Bearing();

    public static Bearing getInstance() {
        return ourInstance;
    }

    private Bearing() { }

    public String getOrientation(int bearing) {
        Orientation arr[] = Orientation.values();
        return arr[bearing <= 20 || bearing >= 340 ? 0 : // NORTH
                bearing > 20 && bearing <= 70 ? 1 :      // NORTH EAST
                bearing > 70 && bearing <= 110 ? 2 :     // EAST
                bearing > 110 && bearing <= 160 ? 3 :    // SOUTH EAST
                bearing > 160 && bearing <= 200 ? 4 :    // SOUTH
                bearing > 200 && bearing <= 260 ? 5 :    // SOUTH WEST
                bearing > 260 && bearing <= 290 ? 6 :    // WEST
                                                  7      // NORTH WEST
        ].toString();
    }

}
