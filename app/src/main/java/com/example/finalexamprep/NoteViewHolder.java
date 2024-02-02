package com.example.finalexamprep;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    private final TextView noteDate;
    private final TextView noteContent;

    public NoteViewHolder(@NonNull View itemView, TextView noteDate, TextView noteContent) {
        super(itemView);
        this.noteDate = noteDate;
        this.noteContent = noteContent;
    }

    public void bind(String date, String note){
        noteDate.setText(date);
        noteContent.setText(note);

    }

}
