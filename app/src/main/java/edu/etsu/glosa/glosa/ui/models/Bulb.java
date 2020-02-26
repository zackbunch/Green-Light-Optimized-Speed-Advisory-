package edu.etsu.glosa.glosa.ui.models;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import edu.etsu.glosa.glosa.R;

/**
 *  Traffic Light Class
 */
public class Bulb extends View {
    private String _text = "";
    private int _textColor = Color.BLACK;
    private float _textSize = 16f;
    private int _lightColor = Color.WHITE;

    private TextPaint _textPaint;
    private Paint _lightPaint;
    private float _textWidth;
    private float _textHeight;
    private float _textX;
    private float _textY;
    private float _centerX;
    private float _centerY;
    private float _radius;

    public Bulb(Context context) {
        super(context);
        init(null, 0);
    }

    public Bulb(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public Bulb(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.Bulb, defStyle, 0);

        _text = a.getString(R.styleable.Bulb_text);
        _textColor = a.getColor(R.styleable.Bulb_textColor, _textColor);

        _textSize = a.getDimension(
                R.styleable.Bulb_textSize,
                _textSize);

        _lightColor = a.getColor(R.styleable.Bulb_lightColor, _lightColor);

        a.recycle();

        // Set up a default TextPaint object
        _textPaint = new TextPaint();
        _textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        _textPaint.setTextAlign(Paint.Align.LEFT);



        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
        invalidateLightPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
        _textPaint.setTextSize(_textSize);
        _textPaint.setColor(_textColor);
        _textWidth = _textPaint.measureText(_text == null? "" : _text.trim());

        Paint.FontMetrics fontMetrics = _textPaint.getFontMetrics();
        _textHeight = fontMetrics.bottom;
    }

    private void invalidateLightPaintAndMeasurements() {
        _lightPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        _lightPaint.setColor(_lightColor);
        _lightPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);




        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;
        _textX = paddingLeft + (contentWidth - _textWidth) / 2;
        _textY = paddingTop + (contentHeight + _textHeight) / 2;

        _centerX = (paddingLeft + paddingRight + contentWidth) / 2;
        _centerY = (paddingTop + paddingBottom + contentHeight) / 2;

        _radius = (_centerX < _centerY) ? _centerX : _centerY;

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = w - paddingLeft - paddingRight;
        int contentHeight = h - paddingTop - paddingBottom;
        _textX = paddingLeft + (contentWidth - _textWidth) / 2;
        _textY = paddingTop + (contentHeight + _textHeight) / 2;

        _centerX = (paddingLeft + paddingRight + contentWidth) / 2;
        _centerY = (paddingTop + paddingBottom + contentHeight) / 2;

        _radius = (_centerX < _centerY) ? _centerX : _centerY;
    }

    @Override
    protected void onDraw(Canvas canvas) {
       super.onDraw(canvas);

        // Draw the light
        canvas.drawCircle(_centerX , _centerY , _radius, _lightPaint);

        // Draw the text.
        if(_text != null)
            canvas.drawText(_text, _textX, _textY, _textPaint);



    }


    public String getText() {
        return _text;
    }


    public void setText(String text) {
        _text = text;
        invalidateTextPaintAndMeasurements();
        requestLayout();
        invalidate();
    }


    public int getTextColor() {
        return _textColor;
    }


    public void setTextColor(int textColor) {
        _textColor = textColor;
        invalidateTextPaintAndMeasurements();
        invalidate();
    }


    public float getTextSize() {
        return _textSize;
    }


    public void setTextSize(float textSize) {
       _textSize = textSize;
        invalidateTextPaintAndMeasurements();
        requestLayout();
        invalidate();
    }


    public int getLightColor() {
        return _lightColor;
    }


    public void setLightColor(int lightColor) {

        _lightColor = lightColor;
        invalidateLightPaintAndMeasurements();
        invalidate();
    }
}
