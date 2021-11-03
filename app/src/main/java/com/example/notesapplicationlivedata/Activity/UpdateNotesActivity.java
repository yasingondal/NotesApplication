package com.example.notesapplicationlivedata.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesapplicationlivedata.Model.Notes;
import com.example.notesapplicationlivedata.R;
import com.example.notesapplicationlivedata.ViewModel.NotesViewModel;
import com.example.notesapplicationlivedata.databinding.ActivityUpdateNotesBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {

    String priority = "1";
    NotesViewModel notesViewModel;
    String sTitle,sSubtitle,sNotes,Spriority;
    int Sid;

    ActionBar actionBar;


    ActivityUpdateNotesBinding bindings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_notes);


        actionBar=getSupportActionBar();




        //For Titles and Back Button
        actionBar.setTitle("Update Notes");
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Initializing NotesViewModel
        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);


        //Binding xml with java for accessing xml resources
        bindings = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(bindings.getRoot());

        //Receiving Data from intent...
        Intent extras = getIntent();
        Sid = extras.getIntExtra("id",0);
        sTitle = extras.getStringExtra("title");
        sSubtitle = extras.getStringExtra("subtitle");
        Spriority = extras.getStringExtra("priority");
        sNotes = extras.getStringExtra("notesdata");


        //Setting this whole collected info to the edit Texts
        bindings.upnotesTitle.setText(sTitle);
        bindings.upnotesSubTitle.setText(sSubtitle);
        bindings.upnotesData.setText(sNotes);

       // For Priority Fetching and showing in Box of priority
        if(Spriority.equals("1")){
            bindings.upGreenPriority.setImageResource(R.drawable.ic_baseline_done_24);
            bindings.upRedPriority.setImageResource(0);
            bindings.upYellowPriority.setImageResource(0);

        }else if(Spriority.equals("2")){
            bindings.upGreenPriority.setImageResource(0);
            bindings.upRedPriority.setImageResource(0);
            bindings.upYellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
        }
        else if(Spriority.equals("3")){
            bindings.upGreenPriority.setImageResource(0);
            bindings.upRedPriority.setImageResource(R.drawable.ic_baseline_done_24);
            bindings.upYellowPriority.setImageResource(0);
        }
        else {
            bindings.upRedPriority.setImageResource(R.drawable.ic_baseline_done_24);
        }



        //Setting priority
        bindings.upGreenPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here code for green
                bindings.upGreenPriority.setImageResource(R.drawable.ic_baseline_done_24);
                bindings.upRedPriority.setImageResource(0);
                bindings.upYellowPriority.setImageResource(0);
                priority = "1";
            }
        });

        //here code for yellow P
        bindings.upYellowPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bindings.upGreenPriority.setImageResource(0);
                bindings.upRedPriority.setImageResource(0);
                bindings.upYellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
                priority = "2";

            }
        });

        //here code for red P
        bindings.upRedPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindings.upGreenPriority.setImageResource(0);
                bindings.upRedPriority.setImageResource(R.drawable.ic_baseline_done_24);
                bindings.upYellowPriority.setImageResource(0);
                priority = "3";
            }
        });

        //Click event on Update Button...
        bindings.upateNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // here code for updating
              String  title = bindings.upnotesTitle.getText().toString();
              String  sub_title = bindings.upnotesSubTitle.getText().toString();
              String  notes = bindings.upnotesData.getText().toString();

                UpdateNotes(title,sub_title,notes);

            }
        });







    }

    private void UpdateNotes(String title, String sub_title, String notes) {

        //For Date Collection
        String pattern = "MMMM d yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        //Model
        Notes NotesUpdateData = new Notes();

        NotesUpdateData.id= Sid;
        NotesUpdateData.notesTitle = title;
        NotesUpdateData.notesSubtitle = sub_title;
        NotesUpdateData.notes = notes;
        NotesUpdateData.notesPriority = priority;
        NotesUpdateData.notesDate = date;

        notesViewModel.updateNote(NotesUpdateData);
        Toast.makeText(this, "Notes Updated Successfully", Toast.LENGTH_SHORT).show();

        //for moving back to main activity...
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.ic_delete)
        {

            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(UpdateNotesActivity.this,R.style.BottomSheetStyle);

            // Creating View Regarding context(From where it will be inflated/layoutname/parent of layout
            View view = LayoutInflater.from(UpdateNotesActivity.this)
                    .inflate(R.layout.delete_botom_sheet,(LinearLayout) findViewById(R.id.bottomSheet));

            //Ataching Above view with action
            bottomSheetDialog.setContentView(view);
            //showing..
            bottomSheetDialog.show();

            TextView yes,no;

            no = view.findViewById(R.id.delete_no);
            yes = view.findViewById(R.id.delete_yes);

            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // yes code
                    notesViewModel.deleteNote(Sid);
                    finish();

                }
            });

            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // no code
                    bottomSheetDialog.dismiss();
                }
            });





        }
        else if(item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }

        return true;
    }
}