package edu.etsu.glosa.glosa.ui.models;

import android.content.Context;
import android.content.res.TypedArray;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import edu.etsu.glosa.glosa.backend.models.Color;
import edu.etsu.glosa.glosa.R;


/**
 * TODO: document your custom view class.
 */
public class TrafficLight extends LinearLayout {

    private Bulb _redBulb;
    private Bulb _greenBulb;
    private Bulb _yellowBulb;
    private Bulb _activeBulb;
    private int _lightSize = 40;
    private float _lightTextSize = 16f;
    private int _lightBackgroundColor = android.graphics.Color.WHITE;
    private int _lightTextColor = android.graphics.Color.BLACK;
    private int _activeLightTextColor = android.graphics.Color.BLACK;

    public TrafficLight(Context context) {
        super(context);

        inflate(context);
        init(null, 0);
    }

    public TrafficLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context);
        init(attrs, 0);
    }

    public TrafficLight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        inflate(context);
        init(attrs, defStyle);
    }

    private void inflate(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sample_traffic_controller, this, true);
    }

    private void init(AttributeSet attrs, int defStyle) {
        setOrientation(LinearLayout.VERTICAL);
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.TrafficLight, defStyle, 0);

        SetTrafficLights();



        //boolean updateActiveLightTextColor = a.hasValue(R.styleable.TrafficLight_activeLightTextColor);

        if (a.hasValue(R.styleable.TrafficLight_lightTextSize)) {
            _lightTextSize = a.getDimension(R.styleable.TrafficLight_lightTextSize,
                    _lightTextSize);
            UpdateLightsTextSizes();
        }

        if (a.hasValue(R.styleable.TrafficLight_lightSize)) {
            _lightSize = a.getDimensionPixelSize(R.styleable.TrafficLight_lightSize,
                    _lightSize);
            UpdateLightsSizes();
        }

        if (a.hasValue(R.styleable.TrafficLight_lightBackgroundColor)) {
            _lightBackgroundColor = a.getColor(R.styleable.TrafficLight_lightBackgroundColor,
                    _lightBackgroundColor);
            UpdateLightsBackgroundColors();
        }

        if (a.hasValue(R.styleable.TrafficLight_lightTextColor)) {
            _lightTextColor = a.getColor(R.styleable.TrafficLight_lightTextColor,
                    _lightTextColor);
            UpdateLightsTextColors();
        }

//        if (a.hasValue(R.styleable.TrafficLight_activeLight)) {
//            int activeIndex = a.getInteger(R.styleable.TrafficLight_activeLight, 3);
//            if(activeIndex != 3){
//               setActiveLight(activeIndex);
//            }
//
//        }

//        if (updateActiveLightTextColor) {
//            _activeLightTextColor = a.getColor(R.styleable.TrafficLight_activeLightTextColor,
//                    _activeLightTextColor );
//        }


        a.recycle();
    }

    public void setActiveTrafficLight(Color color, String text){
        setActiveLight(color);
        _activeBulb.setText(text);
    }



    private void setActiveLight(Color color) {
        // Get Active bulb
        Bulb bulb = getActiveBulb(color);
        if(_activeBulb == null){
            _activeBulb = bulb;
            _activeBulb.setLightColor(getSystemColor(color));
            _activeBulb.setTextColor(_lightTextColor);
            _activeBulb.setText("");
        }else{

            if(_activeBulb != bulb){
                _activeBulb.setLightColor(_lightBackgroundColor);
                _activeBulb.setTextColor(_lightTextColor);
                _activeBulb.setText("");
                _activeBulb = bulb;
                _activeBulb.setLightColor(getSystemColor(color));
                _activeBulb.setTextColor(_activeLightTextColor);

            }

        }
    }

    private void UpdateLightsTextColors() {
        _redBulb.setTextColor(_lightTextColor);
        _yellowBulb.setTextColor(_lightTextColor);
        _greenBulb.setTextColor(_lightTextColor);
        
    }

    private void UpdateLightsBackgroundColors() {
        _redBulb.setLightColor(_lightBackgroundColor);
        _yellowBulb.setLightColor(_lightBackgroundColor);
        _greenBulb.setLightColor(_lightBackgroundColor);
    }

    private void UpdateLightsSizes() {
        _redBulb.setLayoutParams(new LayoutParams(_lightSize, _lightSize));
        _yellowBulb.setLayoutParams(new LayoutParams(_lightSize, _lightSize));
        _greenBulb.setLayoutParams(new LayoutParams(_lightSize, _lightSize));

    }

    private void UpdateLightsTextSizes() {
        _redBulb.setTextSize(_lightTextSize);
        _yellowBulb.setTextSize(_lightTextSize);
        _greenBulb.setTextSize(_lightTextSize);
    }

    private void SetTrafficLights() {
        _redBulb = findViewById(R.id.RedBulb);
        _yellowBulb = findViewById(R.id.YellowBulb);
        _greenBulb = findViewById(R.id.GreenBulb);
    }





    private Bulb getActiveBulb(Color color){
        switch(color){
            case GREEN: return _greenBulb;
            case YELLOW:
                return _yellowBulb;
            case RED:
                return _redBulb;
        }
        return null;
    }

    private int getSystemColor(Color color){
        switch(color){
            case GREEN: return android.graphics.Color.GREEN;
            case YELLOW:
                return android.graphics.Color.YELLOW;
            case RED:
                return android.graphics.Color.RED;
        }
        return android.graphics.Color.WHITE;
    }

}
