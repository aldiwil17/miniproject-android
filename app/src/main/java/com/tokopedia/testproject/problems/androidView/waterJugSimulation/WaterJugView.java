package com.tokopedia.testproject.problems.androidView.waterJugSimulation;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


public class WaterJugView extends View {

    private int maxWater = 0;
    private int waterFill = 0;

    public WaterJugView(Context context) {
        super(context);
    }

    public WaterJugView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WaterJugView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public WaterJugView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setMaxWater(int maxWater) {
        this.maxWater = maxWater;
    }

    public void setWaterFill(int waterFill) {
        this.waterFill = waterFill;
    }

    //TODO
    /*
    Based on these variables: maxWater and waterFill, draw the jug with the water

    Example a:
    maxWater = 10
    waterFill = 0

    Result,
    View will draw like below
    |        |
    |        |
    |        |
    |        |
    `--------'

    Example b:
    maxWater = 10
    waterFill = 5

    Result,
    View will draw like below
    |        |
    |        |
    |--------|
    |        |
    `--------'

    Example c:
    maxWater = 10
    waterFill = 8

    Result,
    View will draw like below
    |        |
    |--------|
    |        |
    |        |
    `--------'

    Example d:
    maxWater = 10
    waterFill = 10

    Result,
    View will draw like below
     ________
    |        |
    |        |
    |        |
    |        |
    `--------'
    */

}
