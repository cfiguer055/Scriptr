package com.example.scriptr.viewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scriptr.R;
import com.example.scriptr.databinding.NoteListItemBinding;
import com.example.scriptr.model.Note;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private OnItemClickListener listener;
    private ArrayList<Note> notes = new ArrayList<Note>();



    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NoteListItemBinding noteListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.note_list_item,
                parent,
                false);

        return new NoteViewHolder(noteListItemBinding);
    }



    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.noteListItemBinding.setNote(note);
    }



    @Override
    public int getItemCount() {
        return null != notes ? notes.size() : 0;
    }



    class NoteViewHolder extends RecyclerView.ViewHolder {
        private NoteListItemBinding noteListItemBinding;

        public NoteViewHolder(@NonNull NoteListItemBinding noteListItemBinding) {
            super(noteListItemBinding.getRoot());
            this.noteListItemBinding = noteListItemBinding;

            noteListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int clickedPosition = getAdapterPosition();

                    if(listener != null && clickedPosition != RecyclerView.NO_POSITION) {
                        listener.onItemClick(notes.get(clickedPosition));
                    }
                }
            });
        }
    }



    public interface OnItemClickListener {
        void onItemClick(Note note);
    }


    public void setListener(OnItemClickListener onItemClickListener) {
        this.listener = listener;
    }



    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }
}
