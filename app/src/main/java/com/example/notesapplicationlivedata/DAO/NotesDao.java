package com.example.notesapplicationlivedata.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notesapplicationlivedata.Model.Notes;

import java.util.List;

// It will be used for CRUD Operations ( DATA ACCESS OBJECT )
@Dao
public interface NotesDao {

    //Function for Getting whole notes
    @Query("SELECT * from Notes_Database")
    LiveData<List<Notes>> getallNotes();


    //For high to low filter
    @Query("SELECT * from Notes_Database ORDER BY notes_priority DESC")
    LiveData<List<Notes>> hightoLow();

    //For hightolow Filter
    @Query("SELECT * from Notes_Database ORDER BY notes_priority ASC")
    LiveData<List<Notes>> lowtoHigh();


    @Insert
    void insertNotes(Notes... notes);

    @Query("DELETE from Notes_Database WHERE id=:id")
    void deleteNotes(int id);

    @Update
    void updateNotes(Notes notes);




}
