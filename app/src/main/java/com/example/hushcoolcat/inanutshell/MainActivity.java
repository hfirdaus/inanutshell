package com.example.hushcoolcat.inanutshell;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.content.Intent;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void buttonPushed(View view) {
        Intent intent = null;
        int id = view.getId();
        switch (id) {
            case R.id.write:
                intent= new Intent(this, edit.class);
                break;
            case R.id.import_recipe:
                intent = new Intent(this, importFromLink.class);
                break;
            case R.id.find:
                intent = new Intent(this, search.class);
                break;
            case R.id.convert:
                intent = new Intent(this, convert.class);
                break;
        }
        startActivity(intent);

        /*
        Intent intent= new Intent(this, createOrParse.class);
        startActivity(intent);

         */

    }
}
