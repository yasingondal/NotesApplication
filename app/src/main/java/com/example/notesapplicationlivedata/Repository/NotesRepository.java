package com.example.notesapplicationlivedata.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notesapplicationlivedata.DAO.NotesDao;
import com.example.notesapplicationlivedata.Database.NotesDatabase;
import com.example.notesapplicationlivedata.Model.Notes;

import java.util.List;


//This is the repository for collecting all data from database..
public class NotesRepository {

    // all quries
    NotesDao notesDao;

    //List for this repository...
    public LiveData<List<Notes>> getallnotes;

    //Lists for Filters
    public LiveData<List<Notes>> hightolow;
    public LiveData<List<Notes>> lowtohigh;



    //This Function for Getting all the notes from db

    //For getting current context of Application State
    public NotesRepository(Application application)
    {
        //application is the context of this repository
        NotesDatabase database = NotesDatabase.getDatabaseInstance(application);

        //For connection to all queries inside ( Data Access Object )
        notesDao = database.notesDao();

        getallnotes = notesDao.getallNotes();

        //For Filters
        hightolow = notesDao.hightoLow();
        lowtohigh = notesDao.lowtoHigh();

    }


    //For Insertion of notes
    public void insertNotes(Notes notes)
    {
        notesDao.insertNotes(notes);
    }

    //For Deletion of notes
    public void deleteNotes(int id)
    {
        notesDao.deleteNotes(id);
    }

    //For updating
    public void updateNotes(Notes notes)
    {
        notesDao.updateNotes(notes);
    }


}
