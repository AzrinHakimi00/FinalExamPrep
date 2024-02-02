package com.example.finalexamprep;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    public int noteId;

    @NonNull
    public String note;
    @NonNull
    public String date;

    public NoteEntity(@NonNull String note, @NonNull String date){
        this.note = note;
        this.date = date;
        this.noteId = 0;
    }


}
