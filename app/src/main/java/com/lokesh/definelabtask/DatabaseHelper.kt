package com.lokesh.definelabtask

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "MatchesDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE SavedMatches (id TEXT PRIMARY KEY, name TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS SavedMatches")
        onCreate(db)
    }

    fun saveMatch(id: String, name: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("id", id)
            put("name", name)
        }
        db.insertWithOnConflict("SavedMatches", null, values, SQLiteDatabase.CONFLICT_REPLACE)
        db.close()
    }

    fun getSavedMatches(): List<Venue> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM SavedMatches", null)
        val matches = mutableListOf<Venue>()

        while (cursor.moveToNext()) {
            val id = cursor.getString(0)
            val name = cursor.getString(1)
            val location = Location(null, 0.0, 0.0, null, null, null)
            val categories = emptyList<Category>()
            matches.add(Venue(id, name, location, categories))
        }
        cursor.close()
        db.close()
        return matches
    }

    fun removeMatch(id: String) {
        val db = writableDatabase
        db.delete("SavedMatches", "id=?", arrayOf(id))
        db.close()
    }

    fun isMatchSaved(id: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM SavedMatches WHERE id=?", arrayOf(id))
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }
}
