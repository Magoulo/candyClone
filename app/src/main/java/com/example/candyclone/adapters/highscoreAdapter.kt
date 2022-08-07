package com.example.candyclone.adapters

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.candyclone.R
import com.example.candyclone.model.Highscore
import java.nio.file.Files.size

class HighscoreAdapter( private val highscore: MutableList<Highscore>): RecyclerView.Adapter<HighscoreAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var highscore : TextView = itemView.findViewById(R.id.Highscore_Score)
        val date : TextView = itemView.findViewById(R.id.Highscore_Date)
        val name : TextView = itemView.findViewById(R.id.Highscore_Name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.highscore_item,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curItem = highscore[position]

        holder.highscore.text = curItem.highscore.toString() + " P"
        holder.date.text = curItem.date
        holder.name.text = curItem.name
    }

    override fun getItemCount(): Int {
        return highscore.size
    }
}