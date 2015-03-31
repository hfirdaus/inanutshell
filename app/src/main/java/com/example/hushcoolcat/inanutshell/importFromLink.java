package com.example.hushcoolcat.inanutshell;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jsoup.Jsoup;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class importFromLink extends ActionBarActivity {
    EditText ingredientsInput;
    EditText directionsInput;
    EditText edtUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent intent = new Intent(this, edit.class);
        ingredientsInput = (EditText) findViewById(R.id.ingredients_input);
        //directionsInput = (EditText) findViewById(R.id.directions_input);
       // ingredientsInput.setText("hi");
       // ingredientsInput.setText("");
        //directionsInput.setText("");
        edtUrl = (EditText) findViewById(R.id.link_input);

        setContentView(R.layout.activity_import_from_link);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_import_from_link, menu);
        return super.onCreateOptionsMenu(menu);
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

    public void goToEdit(importFromLink view)
    {

        //goToEdit(this);
        Intent intent = new Intent(this,edit.class);
        //setContentView(R.layout.activity_edit);
        startActivity(intent);


    }
    public void buttonPushed(View view) {
        int id = view.getId();
        Button importButton = (Button)findViewById(R.id.import_button);
        if (id == R.id.import_button) {
            EditText edtUrl = (EditText) findViewById(R.id.link_input);
            String siteUrl = edtUrl.getText().toString();
            ParseURL parse = new ParseURL();
            (parse).execute(new String[]{siteUrl});
            String ingredients = parse.ingredientsB;
            String directions = parse.directions;

            Log.d("hello", ingredients);
            Log.d("hello", directions);

            if(parse.ingredientsB!= null && parse.directions!=null) {
                Intent intent = new Intent(this, edit.class);
                intent.putExtra("ingredients" ,ingredients);
                intent.putExtra("directions", directions);
               // intent.putExtra(directions, directionsInput.getText().toString());
                startActivity(intent);
            }
            else
                Log.d("hello","null" );

        }
    }

    public class ParseURL extends AsyncTask<String, Void, String> {
//ADD http:// at the start of URL please!!!!!!!!
     public String ingredientsB;
     public String directions;
        @Override
        protected String doInBackground(String... strings) {
            StringBuffer buffer = new StringBuffer();
            if( ! strings[0].contains("http://"))
                strings[0]= strings[0].replaceFirst("", "http://");
            try {
                Log.d("JSwa", "Connecting to [" + strings[0] + "]");
                Document doc = Jsoup.connect(strings[0]).get();
                Log.d("JSwa", "Connected to [" + strings[0] + "]");
                // Get document (HTML page) title
                String title = doc.title();
                Log.d("JSwA", "Title [" + title + "]");
                buffer.append("Title: " + title + "\r\n");

                // Get meta info
                Elements ingredients;
                boolean foodnetwork = strings[0].contains("foodnetwork");
                if (foodnetwork) {
                    //ingredients = doc.select("section");
                   importRecipe(doc, "section.recipe-ingredients", "section.recipeInstructions");
                }
                else
                   importRecipe(doc, "p.fl-ing", "span.plaincharacterwrap");


            } catch (Throwable t) {
                t.printStackTrace();
            }

            Log.d("hello", buffer.toString());
            return buffer.toString();
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //respText.setText(s);
        }



        private void importRecipe(Document doc, String ingredients, String directions) {

            Elements ingredientList = doc.select(ingredients);
            Elements directionList = doc.select(directions);
            StringBuffer ingredients_buffer = new StringBuffer();
            StringBuffer directions_buffer = new StringBuffer();

            for (Element p : ingredientList) {
                ingredients_buffer.append(p.text() + "\n");
            }
            ingredientsB = ingredients_buffer.toString();
            for (Element a:directionList) {
                directions_buffer.append(a.text()+ "\n");

            }
            directions = directions_buffer.toString();




           // ingredientsInput.setText(ingredients_buffer);
            //directionsInput.setText(directions_buffer);


        }

    }
}

