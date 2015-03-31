package com.example.hushcoolcat.inanutshell;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class view extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view, menu);
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

    public void buttonPushed(View view) {
        Intent intent = null;
        int id = view.getId();

       // getDir("/InaNutshell",MODE_WORLD_READABLE );
        viewRecipe("hello");
        }

    public void viewRecipe(String filename)
    {
        loadFromStorage file = new loadFromStorage(filename);
        String recipetext = file.loadFile();
        TextView recipeview = (TextView)findViewById(R.id.recipeContents);
        if( recipetext==null) {
            recipetext = "hello";
        }
         recipeview.setText(recipetext);
        Log.d("viewmode", recipetext);

    }

}
