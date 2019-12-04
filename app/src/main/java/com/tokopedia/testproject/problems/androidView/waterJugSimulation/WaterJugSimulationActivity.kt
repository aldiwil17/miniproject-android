package com.tokopedia.testproject.problems.androidView.waterJugSimulation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.tokopedia.testproject.R
import com.tokopedia.testproject.problems.algorithm.waterJug.Solution
import com.tokopedia.testproject.problems.androidView.waterJugSimulation.Solution.simulateWaterJug
import com.tokopedia.testproject.problems.androidView.waterJugSimulation.WaterJugActionEnum.*
import kotlinx.android.synthetic.main.activity_problem_water_jug_simulate.*

class WaterJugSimulationActivity : AppCompatActivity() {

    private val waterJugSimulationAdapter: WaterJugSimulationAdapter by lazy {
        WaterJugSimulationAdapter(this)
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, WaterJugSimulationActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_problem_water_jug_simulate)
        vg_case.requestFocus()

        recycler_view_simulation.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_view_simulation.setHasFixedSize(true)
        recycler_view_simulation.adapter = waterJugSimulationAdapter
        button_simulate.setOnClickListener {
            val jug1Max = et_jug_1.text.toString().toInt()
            val jug2Max = et_jug_2.text.toString().toInt()
            val target = et_target.text.toString().toInt()
            val list: List<WaterJugAction> = simulateWaterJug(jug1Max, jug2Max, target)

            waterJugSimulationAdapter.setData(generateState(list, jug1Max, jug2Max), jug1Max, jug2Max, target)
            waterJugSimulationAdapter.notifyDataSetChanged()
        }
        button_simulate.performClick()
    }

    fun generateState(list: List<WaterJugAction>, jug1Max: Int, jug2Max: Int): List<WaterJugState> {
        var jug1 = 0
        var jug2 = 0
        val waterJugStateList = mutableListOf<WaterJugState>()
        if (list.isNotEmpty()) {
            for (action in list) {
                when (action.type) {
                    FILL -> {
                        if (action.target == 1) {
                            jug1 = jug1Max
                        } else {
                            jug2 = jug2Max
                        }
                    }
                    POUR -> {
                        if (action.target == 1) {
                            val diff = jug1Max - jug1
                            jug1 += jug2
                            if (jug1 > jug1Max) {
                                jug2 -= diff
                                jug1 = jug1Max
                            } else {
                                jug2 = 0
                            }
                        } else {
                            val diff = jug2Max - jug2
                            jug2 += jug1
                            if (jug2 > jug2Max) {
                                jug1 -= diff
                                jug2 = jug2Max
                            } else {
                                jug1 = 0
                            }
                        }
                    }
                    EMPTY -> {
                        if (action.target == 1) {
                            jug1 = 0
                        } else {
                            jug2 = 0
                        }

                    }
                }
                waterJugStateList.add(WaterJugState(jug1, jug2, action))
            }
        }
        return waterJugStateList
    }
}

class WaterJugSimulationAdapter(private val context: Context) : RecyclerView.Adapter<WaterJugSimulationAdapter.WaterJugSimulationViewHolder>() {
    var waterJugStateList: List<WaterJugState>? = null
    var jug1Max: Int = 0
    var jug2Max: Int = 0
    var target: Int = 0

    fun setData(waterJugStateList: List<WaterJugState>, jug1Max: Int, jug2Max: Int, target: Int) {
        this.waterJugStateList = waterJugStateList
        this.jug1Max = jug1Max
        this.jug2Max = jug2Max
        this.target = target
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): WaterJugSimulationViewHolder {
        val view = WaterJugItemView(context)
        return WaterJugSimulationViewHolder(view)
    }

    override fun getItemCount(): Int = waterJugStateList?.size ?: 0

    override fun onBindViewHolder(holder: WaterJugSimulationViewHolder, position: Int) {
        waterJugStateList?.get(position)?.let {
            holder.bind(it, jug1Max, jug2Max)
        }
    }

    class WaterJugSimulationViewHolder(val waterJugView: WaterJugItemView) : RecyclerView.ViewHolder(waterJugView) {
        fun bind(waterJugState: WaterJugState, jug1Max: Int, jug2Max: Int) {
            when (waterJugState.waterJugAction.type) {
                FILL -> waterJugView.setData(waterJugView.context.getString(R.string.fill_jug_x,
                        waterJugState.waterJugAction.target), waterJugState.jug1, jug1Max, waterJugState.jug2, jug2Max)
                POUR -> waterJugView.setData(waterJugView.context.getString(R.string.pour_jug_x,
                        if (waterJugState.waterJugAction.target == 1) 2 else 1, waterJugState.waterJugAction.target),
                        waterJugState.jug1, jug1Max, waterJugState.jug2, jug2Max)
                EMPTY -> waterJugView.setData(waterJugView.context.getString(R.string.empty_jug_x,
                        waterJugState.waterJugAction.target),
                        waterJugState.jug1, jug1Max, waterJugState.jug2, jug2Max)
            }
        }
    }

}

data class WaterJugState(val jug1: Int, val jug2: Int, val waterJugAction: WaterJugAction)
