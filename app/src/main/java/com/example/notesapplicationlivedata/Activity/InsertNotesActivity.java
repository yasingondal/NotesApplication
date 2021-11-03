package com.example.notesapplicationlivedata.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.notesapplicationlivedata.Model.Notes;
import com.example.notesapplicationlivedata.R;
import com.example.notesapplicationlivedata.ViewModel.NotesViewModel;
import com.example.notesapplicationlivedata.databinding.ActivityInsertNotesBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class InsertNotesActivity extends AppCompatActivity {

    ActivityInsertNotesBinding binding;
    String title,sub_title,notes;

    NotesViewModel notesViewModel;

    ActionBar actionBar;

    String priority = "1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_notes);


        actionBar=getSupportActionBar();




        //For Titles and Back Button
        actionBar.setTitle("Insert Notes");
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Linking binding with XML
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Initializing NotesViewModel
        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);


        //Setting priority
       binding.GreenPriority.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //here code for green
               binding.GreenPriority.setImageResource(R.drawable.ic_baseline_done_24);
               binding.RedPriority.setImageResource(0);
               binding.YellowPriority.setImageResource(0);
               priority = "1";
           }
       });

       //here code for yellow P
        binding.YellowPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.GreenPriority.setImageResource(0);
                binding.RedPriority.setImageResource(0);
                binding.YellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
                priority = "2";

            }
        });

        //here code for red P
        binding.RedPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.GreenPriority.setImageResource(0);
                binding.RedPriority.setImageResource(R.drawable.ic_baseline_done_24);
                binding.YellowPriority.setImageResource(0);
                priority = "3";
            }
        });





        //Click Listener or insertDone Button using bounding..
       binding.doneNotesBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //Getting Data from edit Texts
               title = binding.notesTitle.getText().toString();
               sub_title = binding.notesSubTitle.getText().toString();
               notes = binding.notesData.getText().toString();

               CreateNotes(title,sub_title,notes);

           }


       });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //For Inserting New Notes
    private void CreateNotes(String title, String sub_title, String notes) {

        //For Date Collection
        String pattern = "MMMM d yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date());


        //Model
        Notes notesInsertionModel = new Notes();

        notesInsertionModel.notesTitle = title;
        notesInsertionModel.notesSubtitle = sub_title;
        notesInsertionModel.notes = notes;
        notesInsertionModel.notesDate = date;
        notesInsertionModel.notesPriority = priority;

        notesViewModel.insertNote(notesInsertionModel);
        Toast.makeText(this, "Notes Created Successfully", Toast.LENGTH_SHORT).show();

        //for moving back to main activity....
        finish();

    }


}