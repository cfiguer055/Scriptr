package com.example.scriptr;

import android.os.Bundle;

import com.example.scriptr.viewmodel.MainActivityViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.scriptr.databinding.ActivityScrollingBinding;

public class ScrollingActivity extends AppCompatActivity {

    private ActivityScrollingBinding binding;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityScrollingBinding activityScrollingBinding;
    private MainActivityClickHandlers handlers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());


        // ViewModel and DataBinding
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        activityScrollingBinding = DataBindingUtil.setContentView(this, R.layout.activity_scrolling);
        handlers = new MainActivityClickHandlers();
        activityScrollingBinding.setClickHandlers(handlers);


        /*FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }



    // Create Dropdown Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
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



    public class MainActivityClickHandlers {
        public void onFABClicked(View view) {
            Toast.makeText(getApplicationContext(), "Clicked!", Toast.LENGTH_SHORT).show();
        }
    }
}
