package com.lokesh.definelabtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SavedMatchesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SavedMatchAdapter
    private lateinit var databaseHelper: DatabaseHelper
    private var savedMatches = mutableListOf<Venue>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_saved_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        databaseHelper = DatabaseHelper(requireContext())

        loadSavedMatches()
    }

    private fun loadSavedMatches() {
        savedMatches.clear()
        savedMatches.addAll(databaseHelper.getSavedMatches())
        adapter = SavedMatchAdapter(savedMatches) { match ->
            databaseHelper.removeMatch(match.id)
            loadSavedMatches()
        }
        recyclerView.adapter = adapter
    }
}
