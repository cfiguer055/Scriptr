package com.example.scriptr.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Folder.class,Note.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class NoteDatabase extends RoomDatabase {

    public abstract FolderDAO folderDAO();
    public abstract NoteDAO noteDAO();



    // Singleton Pattern
    private static NoteDatabase instance;

    public static synchronized NoteDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }



    // Callback
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // Insert data when database is created...
            InitializeData();
            
        }
    };

    private static void InitializeData() {
        NoteDAO noteDAO = instance.noteDAO();
        FolderDAO folderDAO = instance.folderDAO();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {

                // Folders
                Folder folder1 = new Folder();
                folder1.setTitle("Front End");
                folder1.setDescription("Web Development Interface");
                folder1.setDate(4);

                Folder folder2 = new Folder();
                folder2.setTitle("Back End");
                folder2.setDescription("Web Development Database");
                folder2.setDate(4);

                folderDAO.insert(folder1);
                folderDAO.insert(folder2);


                // Notes
                Note note1 = new Note();
                note1.setTitle("CSS");
                note1.setContent("blah blah blah blah blah blah blah blah blah blah blah blah ");
                note1.setDate(null);
                note1.setFolderId(1);

                Note note2 = new Note();
                note2.setTitle("HTML");
                note2.setContent("blah blah blah blah blah blah blah blah blah blah blah blah ");
                note2.setDate(null);
                note2.setFolderId(1);

                Note note3 = new Note();
                note3.setTitle("AJAX");
                note3.setContent("blah blah blah blah blah blah blah blah blah blah blah blah ");
                note3.setDate(null);
                note3.setFolderId(2);

                Note note4 = new Note();
                note4.setTitle("PHP");
                note4.setContent("blah blah blah blah blah blah blah blah blah blah blah blah ");
                note4.setDate(null);
                note4.setFolderId(2);

                noteDAO.insert(note1);
                noteDAO.insert(note2);
                noteDAO.insert(note3);
                noteDAO.insert(note4);

            }
        });
    }
}
