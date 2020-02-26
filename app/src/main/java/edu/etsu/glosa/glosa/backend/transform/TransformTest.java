
package edu.etsu.glosa.glosa.backend.transform;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import edu.etsu.glosa.glosa.backend.models.Intersection;
import edu.etsu.glosa.glosa.backend.models.Vehicle;


/**
 * test class for replicating the transformation of a GLOSA response
 *
 * @author Jim Tarlton
 */
public class TransformTest implements iTransform {
    private Context mContext;

    public TransformTest(Context context) {
        mContext = context;
    }

    @Override
    public Intersection getSpatData(Vehicle vehicle) throws Exception {

       // ClassLoader classLoader = getClass().getClassLoader();
        InputStream in = mContext.getAssets().open("fake_input_data.json");
        //Log.i("TransformTest", classLoader.toString());
        //File file = new File(classLoader.getResource("fake_input_data.json").getFile());
       // BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        Intersection[] intersections = new Gson().fromJson(bufferedReader, Intersection[].class);
        int random_int = new Random().nextInt(intersections.length);
        return intersections[random_int];
    }
}
