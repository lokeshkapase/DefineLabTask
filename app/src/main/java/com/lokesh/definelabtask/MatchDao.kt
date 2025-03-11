package com.lokesh.definelabtask

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MatchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMatch(match: MatchEntity)

    @Delete
    fun removeMatch(match: MatchEntity)

    @Query("SELECT * FROM saved_matches")
    fun getSavedMatches(): List<MatchEntity>

    @Query("SELECT id FROM saved_matches")
    fun getSavedMatchIds(): List<String>
}
