package com.example.scriptr.model;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class ScriptrRepository {

    private FolderDAO folderDAO;
    private NoteDAO noteDAO;

    private LiveData<List<Folder>> folders;
    private LiveData<List<Note>> notes;



    public ScriptrRepository(Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        folderDAO = noteDatabase.folderDAO();
        noteDAO = noteDatabase.noteDAO();
    }


    public LiveData<List<Folder>> getAllFolders() {
        return folderDAO.getAllFolders();
    }


    public LiveData<List<Note>> getNotesFromFolder(int folderId) {
        return noteDAO.getNotes(folderId);
    }


    // Insert, Delete, Update in Background
    public void insertFolder(Folder folder) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                // Inserting Folders
                folderDAO.insert(folder);

                // Post Execution: after background execution
            }
        });

    }

    public void insertNote(Note note) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                // Inserting Note
                noteDAO.insert(note);

                // Post Execution: after background execution
            }
        });
    }

    public void deleteFolder(Folder folder) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                // Deleting Folders
                folderDAO.delete(folder);

                // Post Execution: after background execution
            }
        });
    }

    public void deleteNote(Note note) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                // Deleting Note
                noteDAO.delete(note);

                // Post Execution: after background execution
            }
        });
    }

    public void updateFolder(Folder folder) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                // Updating Folders
                folderDAO.update(folder);

                // Post Execution: after background execution
            }
        });
    }

    public void updateNote(Note note) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                // Updating Notes
                noteDAO.update(note);

                // Post Execution: after background execution
            }
        });
    }

}
