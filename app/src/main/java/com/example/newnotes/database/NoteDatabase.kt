package com.example.newnotes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf( Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun Dao() : NoteDao

    companion object {

        public var roomDb: NoteDatabase?= null

        fun getDb(contex: Context): NoteDatabase {
            if (roomDb == null) {
                roomDb = Room.databaseBuilder(contex, NoteDatabase::class.java, "Database")
                    .allowMainThreadQueries().build()
            }

            return roomDb!!
        }
    }
}