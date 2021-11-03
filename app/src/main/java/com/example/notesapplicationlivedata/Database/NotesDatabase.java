package com.example.notesapplicationlivedata.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notesapplicationlivedata.Model.Notes;
import com.example.notesapplicationlivedata.DAO.NotesDao;


@Database(entities = {Notes.class},version = 1)
public abstract class NotesDatabase extends RoomDatabase {

    public abstract NotesDao notesDao();

    public static NotesDatabase INSTANCE;

    //Connection String or Db Connection so we can call it from anywhere in repository..
    public static NotesDatabase getDatabaseInstance(Context context)
    {
        if (INSTANCE == null)
        {
            //For Making New Db Instance
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    NotesDatabase.class,"Notes_Database").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }


}
