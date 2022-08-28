package com.example.candyclone.firebase

import android.content.ContentValues.TAG
import android.util.Log
import com.example.candyclone.model.Highscore
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import java.lang.Thread.sleep
import java.util.*
import kotlin.concurrent.thread


object HighscoreData {

    var highscore: Int? = null
    var date: String? = null
    var name: String? = null

    fun addNewHighscore(highscore: Int, date: String, name: String){
        val db = FirebaseFirestore.getInstance()

        db.collection("highscorer")
            .get()

            .addOnSuccessListener { documents ->

                val userDocumentPath = documents.size()
                var Highscore = highscore
                var date = "2022.."
                var name = name
                val newHighscore = hashMapOf(
                    "userID" to userDocumentPath,
                    "highscore" to Highscore,
                    "name" to name,
                    "date" to date
                )
                val highscoreListSize = getHighscoreList().size
                if(highscoreListSize < 10){
                    popLastHighscore()
                    db.collection("highscorer").document(userDocumentPath.toString())
                        .set(newHighscore)
                        .addOnFailureListener { exception ->
                            Log.w(TAG, "Error writing document", exception)
                        }
                }

            }

            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
    }

    fun popLastHighscore (){
        val db = FirebaseFirestore.getInstance()
        val highscoreList = getHighscoreList()
        for (score in highscoreList) {
            Log.d("Highscore: ", score.toString())
        }
        /*
       val removeHighscore = highscoreList[highscoreList.size]

       db.collection("highscorer")
            .get()*/
    }

    fun getHighscoreList() :ArrayList<Highscore>  {
        val db = FirebaseFirestore.getInstance()
        Log.e("inne i getHighScore: ", "inne i getHighScore")
        var  highscoreArrayList: ArrayList<Highscore> = arrayListOf()

        db.collection("highscorer").orderBy( "highscore",
            Query.Direction.DESCENDING)
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
                            for (score in highscoreArrayList) {
                                Log.e("Highscore: ", score.toString())
                            }
                        }
                    }

                }

            })

        for (score in highscoreArrayList) {
            Log.e("inreturnHighscoreList: ", score.toString())
        }
        Log.e("Highscore: ", "numba 1")
         return highscoreArrayList
    }
}