package com.example.notesapplicationlivedata.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notesapplicationlivedata.Model.Notes;
import com.example.notesapplicationlivedata.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository repository;
    public LiveData<List<Notes>> GetAllNotes;


    //Lists For Filters
    public LiveData<List<Notes>> HIGHTOLOW;
    public LiveData<List<Notes>> LOWTOHIGH;


    public NotesViewModel(Application application) {
        super(application);

        // Made Repository of Notes for accessing all defined functions
       repository = new NotesRepository(application);

       //For getting all notes inside a LiveData List
        GetAllNotes = repository.getallnotes;

        //For Filters
        HIGHTOLOW = repository.hightolow;
        LOWTOHIGH = repository.lowtohigh;

    }


    // NotesInsertionViewModel
    public void insertNote(Notes notes)
    {
        repository.insertNotes(notes);
    }

    // NotesDeletionViewModel
    public void deleteNote(int id)
    {
        repository.deleteNotes(id);
    }

    // NotesUpdatingViewModel
    public void updateNote(Notes notes)
    {
        repository.updateNotes(notes);
    }


}
