package com.example.scriptr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.scriptr.databinding.ActivityAddEditBinding;
import com.example.scriptr.model.Note;

public class AddEditActivity extends AppCompatActivity {

    private Note note;

    public static final String NOTE_ID = "noteId";
    public static final String NOTE_TITLE = "noteTitle";
    public static final String NOTE_CONTENT = "noteContent";

    private ActivityAddEditBinding activityAddEditBinding;
    private AddEditActivityClickHandlers clickHandlers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        note = new Note();
        activityAddEditBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_add_edit);
        activityAddEditBinding.setNote(note);


        // Add clickhandler stuff here if button is added in the future:
            //clickHandlers = new AddEditActivityClickHandlers(this);
            //activityAddEditBinding.setClickHandler(clickHandlers);


        // Intent is passed from "LoadRecyclerView()" or "onFABClicked(View view)" in MainActivity.class
        Intent i = getIntent();
        if(i.hasExtra(NOTE_ID)){
            // Execcuted when RecyclerView item is Clicked
            setTitle("Edit Note");
            note.setTitle(i.getStringExtra(NOTE_TITLE));
            note.setContent(i.getStringExtra(NOTE_CONTENT));

        } else {

            // Executed When FAB Button is Clicked
            setTitle("New Note");
        }

    }


    // Create Dropdown Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_edit_menu, menu);
        return true;
    }


    // Dropdown Menu Selections
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {
            case R.id.note_redo:
                break;
            case R.id.note_undo_all:
                break;
            case R.id.note_share:
                break;
            case R.id.note_delete:
                break;
            case R.id.note_search:
                break;
            case R.id.action_export:
                break;
            case R.id.note_categorize:
                break;
            case R.id.note_read:
                break;
            case R.id.note_print:
                break;
            case R.id.note_info:
                break;
            case R.id.action_undo:
                break;
            case R.id.action_cancel:
                break;
            case R.id.action_save:
                if(note.getTitle() == null) {
                    Toast.makeText(getApplicationContext(), "Note Title is Empty!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent();
                    i.putExtra(NOTE_TITLE, note.getTitle());
                    i.putExtra(NOTE_CONTENT, note.getContent());
                    setResult(RESULT_OK, i);
                    finish();
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    // In case a button is added in the future
    public class AddEditActivityClickHandlers {

    }
}