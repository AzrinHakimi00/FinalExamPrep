package com.example.finalexamprep;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Page2 extends Fragment {

    EditText ETdate, ETnote;
    Button saveBtn, showBtn;
    NoteDatabase db;
    List<String> ListViewnote;
    List<NoteEntity> noteList;

    ListView listView;
    ArrayAdapter<String> adapter;

    public Page2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ETdate = view.findViewById(R.id.date);
        ETnote = view.findViewById(R.id.note);
        saveBtn = view.findViewById(R.id.saveBtn);
        showBtn = view.findViewById(R.id.showBtn);

        listView = view.findViewById(R.id.noteList);

        RoomDatabase.Callback callback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };

        db = Room.databaseBuilder(requireContext(), NoteDatabase.class, "NoteDB").addCallback(callback).build();

        saveBtn.setOnClickListener(v -> {
            String date = ETdate.getText().toString();
            String noteText = ETnote.getText().toString();
            NoteEntity myNote = new NoteEntity(noteText, date);

            addNoteInBackground(myNote);

        });

        showBtn.setOnClickListener(v -> {
            showNoteInBackground();
        });

        // Initialize the list view with an empty list initially
        ListViewnote = new ArrayList<>();
        showNoteInBackground();
    }


    public void addNoteInBackground(NoteEntity note) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(() -> {
            db.noteDao().insert(note);
            showNoteInBackground();
            // Finish task
            handler.post(() -> {
                Toast.makeText(requireContext(), "Data added successfully", Toast.LENGTH_SHORT).show();

                // Update the ListView after adding a new note

            });
        });
    }

    public void showNoteInBackground() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(() -> {
            noteList = db.noteDao().getAscendingNote();

            // Finish task
            handler.post(() -> {
                ListViewnote.clear(); // Clear the list before adding new items
                for (NoteEntity n : noteList) {
                    ListViewnote.add(n.note);
                    Log.e("List Msg",n.note);
                }
                updateNoteListView();
            });
        });

    }

    public void updateNoteListView() {
        if (listView != null) {
                adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, ListViewnote);
                listView.setAdapter(adapter);
        } else {
            Log.e("Page2", "ListView is null");
        }
    }
}

