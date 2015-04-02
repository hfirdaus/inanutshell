package com.example.hushcoolcat.inanutshell;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Environment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.support.v7.internal.widget.AppCompatPopupWindow.*;

public class find extends ActionBarActivity {

    DatabaseTable db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        db = new DatabaseTable(this);
        handleIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_find, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.find));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(getApplicationContext(), find.class)));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            Log.d("If", "if runs");
            String query = intent.getStringExtra(SearchManager.QUERY);
            Cursor c = db.getWordMatches(query, null);
            findFile(query);
        }
    }


    public void findFile(String searchQuery) {
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() + "/InaNutshell");

        String [] queryWords = searchQuery.split(" ");
        File [] fileNamesSaved = directory.listFiles();
        Log.d("First file", fileNamesSaved[0].getName());

        List<String> resultsArray = new ArrayList<String>();
        for (int i = 0; i < queryWords.length; i++) {
            for (int j = 0; j < fileNamesSaved.length; j++) {
                if (queryWords[i].equals(fileNamesSaved[j]))
                    resultsArray.add(fileNamesSaved[j].getName());
            }
        }

        for (int i = 0; i < resultsArray.size(); i++) {
            Log.d("Results", (String) resultsArray.get(i));
        }
    }
}
