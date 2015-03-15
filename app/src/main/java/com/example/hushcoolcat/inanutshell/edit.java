package com.example.hushcoolcat.inanutshell;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class edit extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {

            case R.id.action_settings:
                return true;
            case R.id.action_save:
                saveFile();
                return true;
            case R.id.action_home:
                goHome();

        }

        return super.onOptionsItemSelected(item);
    }
    public void saveFile() {
        EditText titleInput = (EditText) findViewById(R.id.title_input);
        EditText ingredientsInput = (EditText) findViewById(R.id.ingredients_input);
        EditText directionsInput = (EditText) findViewById(R.id.directions_input);
        EditText notesInput = (EditText)findViewById(R.id.notes_input);

        String title = titleInput.getText().toString();
        String ingredients = ingredientsInput.getText().toString();
        String directions = directionsInput.getText().toString();
        String notes = directionsInput.getText().toString();

        String filename = title + ".txt";
        String dataToSave = "Ingredients" + ingredients+ "\n" + "Directions" + directions + "\n"
                + "Notes" + notes;
        writeToStorage create = new writeToStorage(filename, dataToSave);

        presentSaveToast();
    }

    public void presentToast(CharSequence text)
    {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }
    public void presentSaveToast()
    {
        CharSequence text = "Recipe Saved";
        presentToast(text);
    }
    public void presentDeleteToast()
    {
        CharSequence text = "Recipe deleted";
        presentToast(text);
    }
    public void goHome()
    {
        Intent intent = new Intent(this, MainActivity.class);
        setContentView(R.layout.activity_main);
        startActivity(intent);
    }
}
