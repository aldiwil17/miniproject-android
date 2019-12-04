
package com.tokopedia.testproject.problems.algorithm.waterJug

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tokopedia.testproject.R
import com.tokopedia.testproject.loadFile
import kotlinx.android.synthetic.main.activity_problem.*

class WaterJugMinimumCaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // for example only. You may change this.
        val jug1Max = 4
        val jug2Max = 7
        val target = 5

        setContentView(R.layout.activity_problem)
        webView.loadFile("min_pour_jug.html")

        //example
        Solution.minimalPourWaterJug(jug1Max, jug2Max, target)
    }

}
