package com.tokopedia.testproject.problems.androidView.waterJugSimulation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tokopedia.testproject.R
import com.tokopedia.testproject.loadFile
import kotlinx.android.synthetic.main.activity_problem_simulation.*

class WaterJugCaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_problem_simulation)
        webView.loadFile("water_jug_simulation.html")
        btn_simulate.setOnClickListener {
            startActivity(WaterJugSimulationActivity.getIntent(this))
        }
    }
}

infix fun WaterJugActionEnum.to(target: Int): WaterJugAction = WaterJugAction(this, target)

enum class WaterJugActionEnum {
    FILL,
    POUR,
    EMPTY
}

data class WaterJugAction(val type: WaterJugActionEnum, val target: Int)
