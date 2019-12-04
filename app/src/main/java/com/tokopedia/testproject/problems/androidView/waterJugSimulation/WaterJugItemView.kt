package com.tokopedia.testproject.problems.androidView.waterJugSimulation

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.tokopedia.testproject.R
import kotlinx.android.synthetic.main.item_jug_simulation.view.*

class WaterJugItemView(context: Context) : FrameLayout(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_jug_simulation,
                this, true)
    }

    fun setData(text: String, jug1: Int, jug1Max: Int, jug2: Int, jug2Max: Int) {
        tv_desription.setText(text)
        water_jug1.setMaxWater(jug1Max)
        water_jug1.setWaterFill(jug1)
        water_jug2.setMaxWater(jug2Max)
        water_jug2.setWaterFill(jug2)
    }
}
