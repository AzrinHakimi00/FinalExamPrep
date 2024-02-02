package com.example.finalexamprep;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities ={NoteEntity.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NoteDAO noteDao();





}
