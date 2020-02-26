package edu.etsu.glosa.glosa.ui.models.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import edu.etsu.glosa.glosa.ui.controllers.TrafficLightController;


public class TrafficLightControllerAdapter extends ArrayAdapter<TrafficLightController> {
    public TrafficLightControllerAdapter(Context context, ArrayList<TrafficLightController> trafficLightControllers) {
        super(context, 0, trafficLightControllers);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
                return getItem(position);
    }
}
