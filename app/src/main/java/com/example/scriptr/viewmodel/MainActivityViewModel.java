package com.example.scriptr.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.scriptr.model.Folder;
import com.example.scriptr.model.Note;
import com.example.scriptr.model.ScriptrRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    // Repository
    private ScriptrRepository repository;

    // Live Data
    private LiveData<List<Folder>> allFolders;
    private LiveData<List<Note>> notesOfSelectedFolder;



    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new ScriptrRepository(application);
    }


    //
    public LiveData<List<Folder>> getAllFolders() {
        allFolders = repository.getAllFolders();
        return allFolders;
    }

    public LiveData<List<Note>> getNotesOfSelectedFolder(int folderId) {
        notesOfSelectedFolder = repository.getNotesFromFolder(folderId);
        return notesOfSelectedFolder;
    }

    public void addNewNote(Note note) {
        repository.insertNote(note);
    }

    public void updateNote(Note note) {
        repository.updateNote(note);
    }

    public void deleteNote(Note note) {
        repository.deleteNote(note);
    }

}
