package com.lokesh.definelabtask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MatchAdapter(
    private val matches: List<Venue>,
    private val databaseHelper: DatabaseHelper,
    private val onStarClick: (Venue, Boolean) -> Unit
) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val matchName: TextView = itemView.findViewById(R.id.matchName)
        val starIcon: ImageView = itemView.findViewById(R.id.star)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.match_item, parent, false)
        return MatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = matches[position]

        holder.matchName.text = match.name


        val isSaved = databaseHelper.isMatchSaved(match.id)
        holder.starIcon.setImageResource(
            if (isSaved) R.drawable.ic_star_active
                 else R.drawable.ic_star_inactive
        )

        holder.starIcon.setOnClickListener {
            val currentlySaved = databaseHelper.isMatchSaved(match.id)
            if (currentlySaved) {
                databaseHelper.removeMatch(match.id)
                holder.starIcon.setImageResource(R.drawable.ic_star_inactive)
            } else {
                databaseHelper.saveMatch(match.id, match.name)
                holder.starIcon.setImageResource(R.drawable.ic_star_active)
            }
            onStarClick(match, !currentlySaved)
        }
    }

    override fun getItemCount(): Int = matches.size
}
