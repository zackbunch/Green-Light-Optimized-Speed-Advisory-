package edu.etsu.glosa.glosa.ui.controllers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.etsu.glosa.glosa.R;
import edu.etsu.glosa.glosa.backend.models.Color;
import edu.etsu.glosa.glosa.backend.models.Direction;
import edu.etsu.glosa.glosa.ui.models.TrafficLight;

/**
 * TODO: document your custom view class.
 */
public class TrafficLightController extends LinearLayout {
    private TextView mSpeedRecommendation;
    private TrafficLight mTrafficLight;
    private ImageView mManeuver;


    public TrafficLightController(Context context) {
        super(context);
        init(context,null, 0);
    }

    public TrafficLightController(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public TrafficLightController(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void inflate(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sample_traffic_light_controller, this, true);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        inflate(context);
        CreateChildComponents();
        setOrientation(LinearLayout.VERTICAL);
    }
    private void CreateChildComponents(){
        mSpeedRecommendation = findViewById(R.id.SpeedRecommendation);
        mTrafficLight = findViewById(R.id.TrafficLight);
        mManeuver = findViewById(R.id.Maneuver);
    }

    public void setSpeedRecommendation(int averageSpeed){
        mSpeedRecommendation.setText(String.valueOf( averageSpeed + "\n" + "MPH"));
    }

    public void setDirection(Direction direction){
        int resourceId = R.drawable.left;
        switch(direction){
            case LEFT:
                resourceId = R.drawable.left;
                break;
            case LEFTSTRAIGHT:
                break;
            case STRAIGHT:
                break;
            case STRAIGHTRIGHT:
                break;
            case RIGHT:
                resourceId = R.drawable.right;
                break;
            case LEFTSTRAIGHTRIGHT:
                break;
        }
        mManeuver.setImageResource(resourceId);
    }

    public void setTrafficLightStatus(Color color, int duration){
        mTrafficLight.setActiveTrafficLight(color, duration + "s");
    }

}
