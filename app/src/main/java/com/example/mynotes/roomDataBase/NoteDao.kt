package com.example.mynotes.roomDataBase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
   @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun insert(note: Note)

   @Delete
   suspend fun delete(note: Note)

   @Query("SELECT * FROM Word_Table ORDER BY id ASC")
   fun getAllNotes(): LiveData<List<Note>>
}