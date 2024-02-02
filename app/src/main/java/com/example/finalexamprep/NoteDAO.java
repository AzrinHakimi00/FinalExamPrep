package com.example.finalexamprep;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.lifecycle.LiveData;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(NoteEntity note);

    @Query("DELETE FROM NoteEntity")
    void deleteAll();

    @Query("SELECT * FROM NoteEntity ORDER BY date ASC")
    List<NoteEntity> getAscendingNote();


}
