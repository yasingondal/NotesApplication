package com.example.notesapplicationlivedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesapplicationlivedata.Activity.InsertNotesActivity;
import com.example.notesapplicationlivedata.Adapters.NotesAdapter;
import com.example.notesapplicationlivedata.Model.Notes;
import com.example.notesapplicationlivedata.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView nofilter,hightolow,lowtohigh;
    List<Notes> FilterNotesAllList;

    FloatingActionButton newNotesBtn;
    NotesViewModel notesViewModel;
    RecyclerView recyclerView;
    NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("All Notes");

        nofilter = findViewById(R.id.nofilter);
        hightolow = findViewById(R.id.hightolow);
        lowtohigh = findViewById(R.id.lowtohigh);
        recyclerView = findViewById(R.id.NotesRecyclerView);
        newNotesBtn = findViewById(R.id.newNotesBtn);
        //Initializing NotesViewModel
        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);


        nofilter.setBackgroundResource(R.drawable.filter_sel_shape);

        //Filters
        nofilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // no filter code
                loadData(0);

                hightolow.setBackgroundResource(R.drawable.filter_un_shape);
                lowtohigh.setBackgroundResource(R.drawable.filter_un_shape);
                nofilter.setBackgroundResource(R.drawable.filter_sel_shape);
            }
        });

        hightolow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code for high to low..
                loadData(1);
                hightolow.setBackgroundResource(R.drawable.filter_sel_shape);
                lowtohigh.setBackgroundResource(R.drawable.filter_un_shape);
                nofilter.setBackgroundResource(R.drawable.filter_un_shape);
            }
        });

        lowtohigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code for lowtohigh
                loadData(2);
                hightolow.setBackgroundResource(R.drawable.filter_un_shape);
                nofilter.setBackgroundResource(R.drawable.filter_un_shape);
                lowtohigh.setBackgroundResource(R.drawable.filter_sel_shape);
            }
        });




        newNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Moving to InsertNotesActivity
                startActivity(new Intent(MainActivity.this, InsertNotesActivity.class));
            }
        });


        notesViewModel.GetAllNotes.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                setAdapter(notes);
                FilterNotesAllList = notes;
            }
        });

    }

    private void loadData(int i) {

        if(i==0)
        {
            notesViewModel.GetAllNotes.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    FilterNotesAllList = notes;
                }
            });
        }
        else if(i==1){

            notesViewModel.HIGHTOLOW.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    FilterNotesAllList = notes;
                }
            });
        }
        else if(i==2) {
            notesViewModel.LOWTOHIGH.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    FilterNotesAllList = notes;
                }
            });
        }
        else {
            Toast.makeText(this, "No Filter Selected", Toast.LENGTH_SHORT).show();
        }

    }

    //For one time setting adapter for every Filter...
    public void setAdapter(List<Notes> notes){
        //setting layout..
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter = new NotesAdapter(MainActivity.this,notes);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_notes,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setQueryHint("Search Note here...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                NotesFilter(newText);
                return false;
            }
        });



        return super.onCreateOptionsMenu(menu);
    }

    //Notes Filter Method for filtering Notes from all notes..
    private void NotesFilter(String newText) {

        //Making a new list to add all the notes that contain "newText" or searched Text...
        ArrayList<Notes> FilterName = new ArrayList<>();


        //For each loop on FilterNotesAllList for checking From whole Notes
        for(Notes notes:this.FilterNotesAllList){
            // if whole notes contain SearchedText
            if(notes.notesTitle.contains(newText) || notes.notesSubtitle.contains(newText)){
                //add in a new list...
                FilterName.add(notes);
            }
        }
        //Calling Function that we made inn Notesadapter for implementing Search Results
        this.adapter.SearchNotes(FilterName);

    }
}