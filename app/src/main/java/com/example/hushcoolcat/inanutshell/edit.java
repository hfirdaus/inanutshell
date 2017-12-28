package com.example.hushcoolcat.inanutshell;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class edit extends ActionBarActivity {
    String filename = null;
    String dataToSave = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        String newIng;
        String newDirect;
        String newTitle;

        Intent i = getIntent();
        newIng = i.getStringExtra("ingredients");
        newDirect=i.getStringExtra("directions");
        newTitle = i.getStringExtra("title");
        if (newIng==null) {
            newIng = ""; newDirect = ""; newTitle = "";
        }
        EditText titleInput = (EditText) findViewById(R.id.title_input);
        EditText ingredientsInput = (EditText) findViewById(R.id.ingredients_input);
        EditText directionsInput = (EditText) findViewById(R.id.directions_input);
        ingredientsInput.setText(newIng);
        titleInput.setText(newTitle);
        directionsInput.setText(newDirect);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.action_settings:
                return true;
            case R.id.action_save:
                EditText titleInput = (EditText) findViewById(R.id.title_input);
                EditText ingredientsInput = (EditText) findViewById(R.id.ingredients_input);
                EditText directionsInput = (EditText) findViewById(R.id.directions_input);
                EditText notesInput = (EditText)findViewById(R.id.notes_input);
                String title = titleInput.getText().toString();
//                String[] ingredientsList = ingredientsInput.getText().toString().split("\n");
//                for (int i = 0; i < ingredientsList.length; i++) {
//                    ingredientsList[i] = " - " + ingredientsList[i] + "\n";
//                }
                String ingredients = ingredientsInput.getText().toString();

//                String[] directionsList = directionsInput.getText().toString().split("\n");
//                for (int i = 0; i < directionsList.length; i++) {
//                    int number = i+1;
//                    directionsList[i] = number + ". " + directionsList[i] + "\n";
//                }
                String directions = directionsInput.getText().toString();
                String notes = notesInput.getText().toString();
                boolean validSave = saveFile(title, ingredients, directions, notes);

                if (validSave) {
                    writeToStorage create = new writeToStorage(filename, dataToSave);
                    presentSaveToast();
                }
                else {
                    invalidSaveToast();
                }
                return true;
            case R.id.action_home:
                goHome();

        }

        return super.onOptionsItemSelected(item);
    }
    public boolean saveFile(String title, String ingredients, String directions, String notes) {
        boolean isValid = true;

        if ((title.equals("") || ingredients.equals("") || directions.equals(""))) {
           isValid = false;
        }
        else {
            CheckBox favourite = (CheckBox)findViewById(R.id.favourite_star);
//            CheckBox toTry = (CheckBox)findViewById(R.id.to_try);
            String tag = "Tagged as: ";
            if (favourite.isChecked())
                tag = tag + "favourite";
//            else if (toTry.isChecked())
  //              tag = tag+ "To -Try";
            else
                tag = tag+"none";

            filename = title + ".txt";
            dataToSave = tag+ "\n\n"+"Ingredients\n" + ingredients+ "\n" + "Directions\n" + directions + "\n"
                    + "Notes\n" + notes;
            //writeToStorage create = new writeToStorage(filename, dataToSave);
        }
        return isValid;
    }

    public void presentToast(CharSequence text)
    {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }
    public void invalidSaveToast() {
        CharSequence text = "One or more required input areas are not filled.";
        presentToast(text);
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
