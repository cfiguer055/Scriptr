package com.example.scriptr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ArrayList<Folder> foldersList;
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers handlers;

    private Folder selectedFolder;



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



    public class MainActivityClickHandlers {

        public void onFABClicked(View view) {
            Toast.makeText(getApplicationContext(), "Clicked!", Toast.LENGTH_SHORT).show();
        }


        public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {
            selectedFolder = (Folder) parent.getItemAtPosition(pos);

            String message = "id is: " + selectedFolder.getFolderId() +
                    "\nname is " + selectedFolder.getTitle();

            Toast.makeText(parent.getContext(), "message: " + message, Toast.LENGTH_SHORT).show();
        }
    }
}