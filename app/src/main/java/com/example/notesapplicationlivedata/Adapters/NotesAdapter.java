package com.example.notesapplicationlivedata.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapplicationlivedata.Activity.UpdateNotesActivity;
import com.example.notesapplicationlivedata.MainActivity;
import com.example.notesapplicationlivedata.Model.Notes;
import com.example.notesapplicationlivedata.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {

    MainActivity mainActivity;
    List<Notes> notes;

    List<Notes> allnotesItem;


    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {

        this.mainActivity = mainActivity;
        this.notes = notes;
        // In all notes item list it will be whole list without any filter
        allnotesItem = new ArrayList<>(notes);

    }

    //using this function we are filtering from whole notes to filtered notes...
    public void SearchNotes(List<Notes> filteredName){

        this.notes = filteredName;
        notifyDataSetChanged();

    }

    @Override
    public notesViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return new notesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes,parent,false));
    }

    @Override
    public void onBindViewHolder(NotesAdapter.notesViewHolder holder, int position) {

        Notes eachnote = notes.get(position);

        holder.title.setText(eachnote.notesTitle);
        holder.subtitle.setText(eachnote.notesSubtitle);
        holder.notesdate.setText(eachnote.notesDate);

        if(eachnote.notesPriority.equals("1"))
        {
            holder.notespriority.setBackgroundResource(R.drawable.green_priority);
        }
        else if(eachnote.notesPriority.equals("2"))
        {
            holder.notespriority.setBackgroundResource(R.drawable.yellow_priority);
        }
        else if(eachnote.notesPriority.equals("3"))
        {
            holder.notespriority.setBackgroundResource(R.drawable.red_priority);
        }
        else
        {
            holder.notespriority.setBackgroundResource(R.drawable.red_priority);
        }






        // On item view Click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //here i am using an intent to send details on itemView List to Update activity
                Intent intent = new Intent(mainActivity,UpdateNotesActivity.class);
                //Values that need to send to other update activity for updating data..
                intent.putExtra("id",eachnote.id);
                intent.putExtra("title",eachnote.notesTitle);
                intent.putExtra("subtitle",eachnote.notesSubtitle);
                intent.putExtra("priority",eachnote.notesPriority);
                intent.putExtra("notesdata",eachnote.notes);
                mainActivity.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class notesViewHolder extends RecyclerView.ViewHolder {

        TextView title,subtitle,notesdate;
        View notespriority;

        public notesViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.notessTitle);
            subtitle = itemView.findViewById(R.id.notesssubtitle);
            notesdate = itemView.findViewById(R.id.notessDate);
            notespriority = itemView.findViewById(R.id.notespriority);


        }
    }
}
