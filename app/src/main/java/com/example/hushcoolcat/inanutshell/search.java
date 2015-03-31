package com.example.hushcoolcat.inanutshell;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.app.SearchManager;
import android.support.v7.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class search extends ListActivity {

    DatabaseTable db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        db = new DatabaseTable(this);
        handleIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(getApplicationContext(), search.class)));//setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //Cursor c = db.getWordMatches(query, null);
            findFile(query);
        }
    }

    private void findFile(String searchQuery) {
        String [] queryWords = searchQuery.split(" ");
       // String [] fileNamesSaved = Context.fileList();
       // Log.d("First file", fileNamesSaved[0]);
        /*
        List<String> resultsArray = new ArrayList<String>();
        ArrayList resultsArray = new String[0];
        for (int i = 0; i < queryWords.length) {
            for (int j = 0; j < fileNamesSaved.length) {
                if (queryWords[i].equals(fileNamesSaved[j]))
                    resultsArray.add(fileNamesSaved[j]);
            }
        }

        for (int i = 0; i < resultsArray.size()) {
            Log.d("Results", (String) resultsArray.get(i));
        }
        */
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
}
