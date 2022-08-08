package com.example.scriptr.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "folders")
public class Folder extends BaseObservable {

    // Columns:
    @ColumnInfo(name = "folder_id")
    @PrimaryKey(autoGenerate = true)
    private int folderId;

    @ColumnInfo(name = "folder_title")
    private String title;

    @ColumnInfo(name = "folder_description")
    private String description;

    @ColumnInfo(name = "folder_date")
    private int date;



    // Constructors:
    @Ignore
    public Folder() {
    }


    public Folder(int folderId, String title, String description, int date) {
        this.folderId = folderId;
        this.title = title;
        this.description = description;
        this.date = date;
    }



    // Getters and Setters
    @Bindable
    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Bindable
    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
