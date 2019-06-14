package com.coding.notesapplication.data.local.dao


import androidx.lifecycle.LiveData
import androidx.room.*
import com.coding.notesapplication.models.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAllNote()

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    fun getAllNotes() : LiveData<List<Note>>
}