package com.example.candyclone.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView
import com.example.candyclone.R

class ScoreActivity : AppCompatActivity() {

    private var scoreResult: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        scoreResult = intent.getIntExtra("EXTRA_SCORE",0)

        var score = findViewById<TextView>(R.id.scoreActivity_score)
        score.text = scoreResult.toString()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        startActivity(
            Intent(this@ScoreActivity,
                HighscoreActivity::class.java)
        )
        finish()

        return super.onTouchEvent(event)
    }
}