package com.example.mynotes.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mynotes.repository.NoteRepository
import com.example.mynotes.roomDataBase.Note
import com.example.mynotes.roomDataBase.NoteDatabase
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) :AndroidViewModel(application)
{
    private lateinit var repository : NoteRepository
    val allNotes: LiveData<List<Note>>
    init{
        val dao = NoteDatabase.getDatabase(application).NoteDao()
        repository = NoteRepository(dao)
        allNotes=repository.allNotes
    }
    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }
    fun delete(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }
}