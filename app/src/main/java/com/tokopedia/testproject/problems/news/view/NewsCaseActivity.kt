package com.tokopedia.testproject.problems.news.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tokopedia.testproject.R
import com.tokopedia.testproject.loadFile
import kotlinx.android.synthetic.main.activity_problem_simulation.*

/**
 * Created by hendry on 28/01/19.
 */
class NewsCaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_problem_simulation)
        webView.loadFile("news.html")
        btn_simulate.setOnClickListener {
            startActivity(Intent(this, NewsActivity::class.java))
        }
    }

}