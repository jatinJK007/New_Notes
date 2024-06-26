package com.example.newnotes.viewmodels

import android.app.Application
import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.newnotes.database.Note
import com.example.newnotes.database.NoteDatabase

class MainActivityModel(application: Application) : AndroidViewModel(application) {

    lateinit var noteList: LiveData<List<Note>>

    init {
        noteList = NoteDatabase.getDb(application).Dao().getAllNotes()
    }
}