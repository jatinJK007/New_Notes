package com.example.newnotes.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.newnotes.database.Note
import com.example.newnotes.database.NoteDatabase

class AddEditActivityModel(application: Application) : AndroidViewModel(application) {
    fun insert(note: Note, context: Context) {
        NoteDatabase.getDb(context).Dao().insert(note)
    }

    fun update(note: Note, context: Context) {
        NoteDatabase.getDb(context).Dao().update(note)
    }

    fun delete(note: Note, context: Context) {
        NoteDatabase.getDb(context).Dao().delete(note)
    }



}