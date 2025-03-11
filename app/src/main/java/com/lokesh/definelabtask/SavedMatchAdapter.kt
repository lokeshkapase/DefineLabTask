package com.lokesh.definelabtask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SavedMatchAdapter(
    private val savedMatches: List<Venue>,
    private val onStarClick: (Venue) -> Unit // Callback to remove match from database
) : RecyclerView.Adapter<SavedMatchAdapter.SavedMatchViewHolder>() {

    class SavedMatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val matchName: TextView = itemView.findViewById(R.id.matchName)
        val starIcon: ImageView = itemView.findViewById(R.id.star)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedMatchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.match_item, parent, false)
        return SavedMatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SavedMatchViewHolder, position: Int) {
        val match = savedMatches[position]
        holder.matchName.text = match.name
        holder.starIcon.setImageResource(R.drawable.ic_star_active) // Star is always active for saved matches

        holder.starIcon.setOnClickListener {
            onStarClick(match) // Remove from database when clicked
        }
    }

    override fun getItemCount(): Int {
        return savedMatches.size
    }
}
