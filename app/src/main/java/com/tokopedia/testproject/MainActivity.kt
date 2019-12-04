package com.tokopedia.testproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.tokopedia.testproject.problems.algorithm.continousarea.MaxContinousAreaActivity
import com.tokopedia.testproject.problems.algorithm.maxrectangle.MaxRectActivity
import com.tokopedia.testproject.problems.algorithm.waterJug.WaterJugMinimumCaseActivity
import com.tokopedia.testproject.problems.androidView.graphTraversal.GraphCaseActivity
import com.tokopedia.testproject.problems.androidView.slidingImagePuzzle.SlidingImagePuzzleCaseActivity
import com.tokopedia.testproject.problems.androidView.waterJugSimulation.WaterJugCaseActivity
import com.tokopedia.testproject.problems.news.view.NewsCaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1 goTo MaxRectActivity::class.java
        button2 goTo MaxContinousAreaActivity::class.java
        button3 goTo WaterJugMinimumCaseActivity::class.java
        button4 goTo WaterJugCaseActivity::class.java
        button5 goTo SlidingImagePuzzleCaseActivity::class.java
        button6 goTo GraphCaseActivity::class.java
        button7 goTo NewsCaseActivity::class.java
    }

    private fun startActivity(cls: Class<*>){
        startActivity(Intent(this, cls))
    }

    private infix fun View.goTo(cls: Class<*>) {
        setOnClickListener { startActivity(cls) }
    }

}
