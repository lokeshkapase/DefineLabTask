package com.lokesh.definelabtask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.appdistribution.gradle.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AllMatchesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MatchAdapter
    private lateinit var databaseHelper: DatabaseHelper
    private var matches = mutableListOf<Venue>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        databaseHelper = DatabaseHelper(requireContext())

        fetchMatches()
    }

    private fun fetchMatches() {
        RetrofitClient.instance.getMatches().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    matches.clear()
                    matches.addAll(response.body()?.response?.venues ?: emptyList())
                    setupRecyclerView()
                } else {
                    Toast.makeText(requireContext(), "Failed to load matches", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = MatchAdapter(matches, databaseHelper) { match, isSaved ->

        }
        recyclerView.adapter = adapter
    }
}
