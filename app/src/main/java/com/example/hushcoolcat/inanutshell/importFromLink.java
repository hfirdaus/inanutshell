package com.example.hushcoolcat.inanutshell;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class importFromLink extends ActionBarActivity {
    String ingredients;
    String directions;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_import_from_link);
        intent = new Intent(this, edit.class);
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

    public void goToEdit(String title, String ingredients, String directions) {
        intent.putExtra("ingredients", ingredients);
        intent.putExtra("title", title);
        intent.putExtra("directions", directions);
        startActivity(intent);


    }

    public void buttonPushed(View view) {
        int id = view.getId();

        if (id == R.id.import_button) {
            EditText edtUrl = (EditText) findViewById(R.id.link_input);
            String siteUrl = edtUrl.getText().toString();
            ParseURL parse = new ParseURL();
            (parse).execute(new String[]{siteUrl});

            // startActivity(intent);
        }
    }

    public class ParseURL extends AsyncTask<String, Void, String> {
//ADD http:// at the start of URL please!!!!!!!!

        @Override
        public String doInBackground(String... strings) {
            StringBuffer buffer = new StringBuffer();
            if (!strings[0].contains("http://"))
                strings[0] = strings[0].replaceFirst("", "http://");
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
                } else
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

            String title = StringUtils.substringBetween(s, "Title", "Ingredients");
            String ingredients = StringUtils.substringBetween(s, "Ingredients", "Directions");
            String directions = StringUtils.substringBetween(s, "Directions", "END");
            //Log.d("pleasework", ingredients);
            goToEdit(title, ingredients, directions);


        }


        public StringBuffer importRecipe(Document doc, String ingredients, String directions) {

            Elements ingredientList = doc.select(ingredients);
            Elements directionList = doc.select(directions);
            StringBuffer ingredients_buffer = new StringBuffer();
            StringBuffer directions_buffer = new StringBuffer();

            ingredients_buffer.append("Ingredients");
            for (Element p : ingredientList) {
                ingredients_buffer.append(p.text() + "\n");
            }
            ingredients_buffer.append("Directions");
            for (Element a : directionList) {
                directions_buffer.append(a.text() + "\n");

            }
            directions_buffer.append("END");
            ingredients = ingredients_buffer.toString();
            directions = directions_buffer.toString();

            return ingredients_buffer.append(directions_buffer);
        }

    }
}

