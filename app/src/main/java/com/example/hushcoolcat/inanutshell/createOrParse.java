package com.example.hushcoolcat.inanutshell;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;


public class createOrParse extends ActionBarActivity {
    private static final String DEBUG_TAG = "HttpExample";
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);*/
        setContentView(R.layout.activity_create_or_parse);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_or_parse, menu);
        return true;
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

            case R.id.action_home:
                goHome();

        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonPushed(View view)
    {

        Intent intent = null;
        int id = view.getId();
        if (id == R.id.write)
            intent = new Intent(this, edit.class);
        else
            intent = new Intent(this, importFromLink.class);

        startActivity(intent);

    }




    public void goHome()
    {
        Intent intent1 = new Intent(this, MainActivity.class);
        setContentView(R.layout.activity_main);
        startActivity(intent1);
    }




}
