package com.example.scriptr.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.scriptr.BR;

import java.util.Date;



@Entity(tableName = "notes")
public class Note extends BaseObservable {

    // Columns:
    @ColumnInfo(name = "note_id")
    @PrimaryKey(autoGenerate = true)
    private int noteId;

    @ColumnInfo(name = "note_title")
    private String title;

    @ColumnInfo(name = "note_content")
    private String content;

    @ColumnInfo(name = "note_date")
    private Date date;

    @ColumnInfo(name = "note_folder_id")
    private int folderId;
    // no folder functionality for now. use -1 for now.




    // Constructors:
    @Ignore
    public Note() {
    }


    public Note(int noteId, String title, String content, Date date, int folderId) {
        this.noteId = noteId;
        this.title = title;
        this.content = content;
        this.date = date;
        this.folderId = folderId;
    }




    // Getters and Setters:
    @Bindable
    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
        notifyPropertyChanged(BR.noteId);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        notifyPropertyChanged(BR.content);
    }

    @Bindable
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        notifyPropertyChanged(BR.date);
    }

    @Bindable
    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
        notifyPropertyChanged(BR.folderId);
    }
}
