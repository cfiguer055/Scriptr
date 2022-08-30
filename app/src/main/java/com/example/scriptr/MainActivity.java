package com.example.scriptr;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.scriptr.databinding.ActivityMainBinding;
import com.example.scriptr.model.Folder;
import com.example.scriptr.model.Note;
import com.example.scriptr.viewmodel.MainActivityViewModel;
import com.example.scriptr.viewmodel.NoteAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ArrayList<Folder> foldersList;
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers handlers;

    private Folder selectedFolder;

    // RecyclerView
    private RecyclerView noteRecyclerView;
    private NoteAdapter noteAdapter;
    private ArrayList<Note> notesList;
    private static final int ADD_NOTE_REQUEST_CODE = 1;
    private static final int EDIT_NOTE_REQUEST_CODE = 2;

    public int selectedNoteId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // View Model and Handlers
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handlers = new MainActivityClickHandlers();
        activityMainBinding.setClickHandlers(handlers);


        // Get Folder and Notes to display when app opens for first time
        mainActivityViewModel.getAllFolders().observe(this, new Observer<List<Folder>>() {
            @Override
            public void onChanged(List<Folder> folders) {
                foldersList = (ArrayList<Folder>) folders;

                for(Folder f : folders) {
                    Log.i("TAG", f.getTitle());
                }

                showOnSpinner();
            }
        });

        mainActivityViewModel.getNotesOfSelectedFolder(1).observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                for(Note n : notes)
                    Log.i("TAG", n.getTitle());
            }
        });

    }


    // Create Dropdown Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }

    // Dropdown Menu Selections
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {
            case R.id.note_new:
                break;
            case R.id.note_edit:
                break;
            case R.id.note_view:
                break;
            case R.id.note_settings:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void showOnSpinner() {
        ArrayAdapter<Folder> folderArrayAdapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                foldersList
        );

        folderArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.setSpinnerAdapter(folderArrayAdapter);
    }


    public void LoadNotesArrayList(int folderID) {
        mainActivityViewModel.getNotesOfSelectedFolder(folderID).observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                notesList = (ArrayList) notes;
                LoadRecyclerView();
            }
        });
    }



    private void LoadRecyclerView() {
        noteRecyclerView = activityMainBinding.secondaryLayout.recyclerView;
        noteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteRecyclerView.setHasFixedSize(true);

        noteAdapter = new NoteAdapter();
        noteRecyclerView.setAdapter(noteAdapter);

        noteAdapter.setNotes(notesList);

        // EDIT THE COURSE
        noteAdapter.setListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                selectedNoteId = note.getNoteId();

                Intent i = new Intent(MainActivity.this, AddEditActivity.class);
                i.putExtra(AddEditActivity.NOTE_ID, selectedNoteId);
                i.putExtra(AddEditActivity.NOTE_TITLE, note.getTitle());
                i.putExtra(AddEditActivity.NOTE_CONTENT, note.getContent());

                startActivityForResult(i, EDIT_NOTE_REQUEST_CODE);

            }
        });

        // Delete A Note
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                // use to move note to different position
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Note noteToDelete = notesList.get(viewHolder.getAdapterPosition());
                mainActivityViewModel.deleteNote(noteToDelete);
            }
        }).attachToRecyclerView(noteRecyclerView);

    }




    public class MainActivityClickHandlers {

        public void onFABClicked(View view) {
            // CREATE THE COURSE

            Intent i = new Intent(MainActivity.this, AddEditActivity.class);
            startActivityForResult(i, ADD_NOTE_REQUEST_CODE);

        }


        public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {
            selectedFolder = (Folder) parent.getItemAtPosition(pos);

            String message = "id is: " + selectedFolder.getFolderId() +
                    "\nname is " + selectedFolder.getTitle();

            Toast.makeText(parent.getContext(), "message: " + message, Toast.LENGTH_SHORT).show();

            LoadNotesArrayList(selectedFolder.getFolderId());
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int selectedFolderId = selectedFolder.getFolderId();

        if(requestCode == ADD_NOTE_REQUEST_CODE && resultCode == RESULT_OK) {
            Note note = new Note();

            note.setFolderId(selectedFolderId);
            note.setTitle(data.getStringExtra(AddEditActivity.NOTE_TITLE));
            note.setContent(data.getStringExtra(AddEditActivity.NOTE_CONTENT));
            mainActivityViewModel.addNewNote(note);

        } else if(requestCode == EDIT_NOTE_REQUEST_CODE && resultCode == RESULT_OK) {
            Note note = new Note();

            note.setFolderId(selectedFolderId);
            note.setTitle(data.getStringExtra(AddEditActivity.NOTE_TITLE));
            note.setContent(data.getStringExtra(AddEditActivity.NOTE_CONTENT));

            note.setNoteId(selectedNoteId);

            mainActivityViewModel.updateNote(note);
        }

    }
}