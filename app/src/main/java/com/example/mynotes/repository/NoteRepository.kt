package com.example.mynotes.repository

import androidx.lifecycle.LiveData
import com.example.mynotes.roomDataBase.Note
import com.example.mynotes.roomDataBase.NoteDao

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class NoteRepository(private val noteDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()
    suspend fun insert(note: Note)
    {
        noteDao.insert(note);
    }
    suspend fun delete(note: Note)
    {
        noteDao.delete(note);
    }
}