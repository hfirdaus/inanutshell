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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_import_from_link);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_import_from_link, menu);
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
    public void goToEdit(ParseURL view)
    {
        /*
            EditText ingredientsInput = (EditText) findViewById(R.id.ingredients_input);
            EditText directionsInput = (EditText) findViewById(R.id.directions_input);
            ingredientsInput.setText(ingredients_buffer);
            directionsInput.setText(directions_buffer);
        */
        //goToEdit(this);
        Intent intent = new Intent(this,edit.class);
        //setContentView(R.layout.activity_edit);
        startActivity(intent);


    }
    public void buttonPushed(View view) {
        int id = view.getId();
        if (id == R.id.import_button) {
            EditText edtUrl = (EditText) findViewById(R.id.link_input);
            String siteUrl = edtUrl.getText().toString();
            (new ParseURL()).execute(new String[]{siteUrl});
           // Document document = null;
          /*  try {
                document = Jsoup.connect(siteUrl).get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Elements paragraphs = document.select("p");
/*
            for (Element p: paragraphs)
                System.out.println(p.text());
            System.out.print(document); */
            //(new ParseURL() ).execute(new String [] {siteUrl});
            /*try {
                URL url = new URL(siteUrl);
                HttpURLConnection con = (HttpURLConnection) url
                        .openConnection();
                readStream(con.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();

            }*/
        }
    }

    private class ParseURL extends AsyncTask<String, Void, String> {
//ADD http:// at the start of URL please!!!!!!!!
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
                    buffer.append(importRecipe(doc, "section.recipe-ingredients", "section.recipeInstructions"));
                }
                else
                    buffer.append(importRecipe(doc, "p.fl-ing", "span.plaincharacterwrap"));


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



        private StringBuffer importRecipe(Document doc, String ingredients, String directions) {
            Elements ingredientList = doc.select(ingredients);
            Elements directionList = doc.select(directions);
            StringBuffer ingredients_buffer = new StringBuffer();
            StringBuffer directions_buffer = new StringBuffer();
            StringBuffer ultimate = new StringBuffer();
            for (Element p : ingredientList) {
                ingredients_buffer.append(p.text() + "\n");
            }
            for (Element a:directionList) {
                directions_buffer.append(a.text()+ "\n");

            }


            ultimate = ingredients_buffer.append(directions_buffer);
            return ultimate;

        }
    }
}

