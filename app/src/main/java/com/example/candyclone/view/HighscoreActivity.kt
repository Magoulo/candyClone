package com.example.candyclone.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.candyclone.R
import com.example.candyclone.adapters.HighscoreAdapter
import com.example.candyclone.model.Highscore
import com.google.firebase.firestore.*
import com.google.firebase.storage.FirebaseStorage

class HighscoreActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var highscoreArrayList: ArrayList<Highscore>
    lateinit var highscoreListAdapter: HighscoreAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highscore)

        highscoreArrayList = arrayListOf() /*arrayListOf(Highscore(2000, "2022-02-02", "Magoulo"),
            Highscore(200, "2022-02-02", "Rita"),
            Highscore(199, "2022-02-02", "Sabina"),
            Highscore(1, "2022-02-02", "Chunggus"))*/

        highscoreListAdapter = HighscoreAdapter(highscoreArrayList)

        //RecyclerView
        recyclerView = findViewById(R.id.RV_Highscore)
        recyclerView.adapter = highscoreListAdapter
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager


        generateList()
    }

    private fun generateList()  {
        val db = FirebaseFirestore.getInstance()
        var lengthOfChat = 0

        db.collection("highscorer").orderBy( "highscore",
            Query.Direction.ASCENDING)
            .addSnapshotListener(object : EventListener<QuerySnapshot> {

                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                ) {
                    if (error != null) {
                        Log.e("Firestore Error ", error.message.toString())
                        return
                    }
                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            highscoreArrayList.add(dc.document.toObject(Highscore::class.java))
                            lengthOfChat += 1

                        }
                    }
                   // callback(lengthOfChat)
                    highscoreListAdapter.notifyDataSetChanged()
                }

            })
    }

}