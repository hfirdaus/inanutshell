package com.example.hushcoolcat.inanutshell;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class convert extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);
        Spinner spinner1 = (Spinner) findViewById(R.id.from_spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.convert_options, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        Spinner spinner2 = (Spinner) findViewById(R.id.to_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.convert_options, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        Button button = (Button) findViewById(R.id.convertButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonPushed(v);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_convert, menu);
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
        switch (id) {
            case R.id.convertButton:
                String fromValue = ((Spinner) findViewById(R.id.from_spinner)).getSelectedItem().toString();
                String toValue = ((Spinner) findViewById(R.id.to_spinner)).getSelectedItem().toString();
                String stringAmount = ((EditText) findViewById(R.id.editText)).getText().toString();
                TextView result = (TextView) findViewById(R.id.convertResult);
                double resultAmount = convertValues(fromValue, toValue, stringAmount);
                if (resultAmount == 0) {
                result.setText("No amount entered.");
                }
                else {
                    if ((resultAmount % 1) == 0) {
                        result.setText(String.format("%d", ((int) resultAmount)));
                    }
                    else {
                        result.setText(String.format("%.2f", resultAmount));
                    }
                }
                break;
        }
    }

    public double convertValues(String fromValue, String toValue, String stringAmount) {
        if (stringAmount.equals("")) {
            return 0;
        }
        double amount = Double.parseDouble(stringAmount);
        return (amount*(1/toUnit(fromValue))*toUnit(toValue));
    }

    public double toUnit(String unit) {

        switch (unit) {
            case "cup":
                return 4;
            case "dash":
                return 1536;
            case "litre":
                return 0.9463529544;
            case "millilitre":
                return 946.3529544;
            case "ounce":
                return 32;
            case "pinch":
                return 3072;
            case "pint":
                return 2;
            case "quart":
                return 1;
            case "tbsp":
                return 64;
            case "tsp":
                return 192;
            default:
                //should never happen
                return 0;
        }
    }
}