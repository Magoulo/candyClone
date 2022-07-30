package com.example.candyclone.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.candyclone.R
import com.example.candyclone.adapters.HighscoreAdapter
import com.example.candyclone.model.Highscore

class HighscoreActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var highscoreArrayList: ArrayList<Highscore>
    lateinit var highscoreListAdapter: HighscoreAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highscore)

        recyclerView = findViewById(R.id.RV_Highscore)
        highscoreArrayList = arrayListOf(Highscore(2000, "2022-02-02", "Magoulo"),
            Highscore(200, "2022-02-02", "Rita"),
            Highscore(199, "2022-02-02", "Sabina"),
            Highscore(1, "2022-02-02", "Chunggus"))

        highscoreListAdapter = HighscoreAdapter(highscoreArrayList)
        recyclerView.adapter = highscoreListAdapter

        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
    }
}