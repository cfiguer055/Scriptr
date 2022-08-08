package com.example.scriptr.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FolderDAO {

    @Insert
    void insert(Folder folder);

    @Delete
    void delete(Folder folder);

    @Query("SELECT * FROM folders")
    LiveData<List<Note>> getAllFolders();

}
