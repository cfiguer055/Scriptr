package com.example.scriptr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.scriptr.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private MainActivityClickHandlers handlers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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



    public class MainActivityClickHandlers {
        public void onFABClicked(View view) {
            Toast.makeText(getApplicationContext(), "Clicked!", Toast.LENGTH_SHORT).show();
        }
    }
}