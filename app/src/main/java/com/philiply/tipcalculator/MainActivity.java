package com.philiply.tipcalculator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void calculateTip(View v) {
        float tipPerc = Float.parseFloat(v.getTag().toString());

        EditText subtotalInput = (EditText) findViewById(R.id.subtotal_input);

        if (!subtotalInput.getText().toString().equals("")) {
            float subtotalAmount = Float.parseFloat(subtotalInput.getText().toString());
            TextView tipTextView = (TextView) findViewById(R.id.tip_amount_value);
            TextView totalTextView = (TextView) findViewById(R.id.final_total);
            NumberFormat formatter = NumberFormat.getCurrencyInstance();

            float tipAmount = subtotalAmount * tipPerc;

            float total = subtotalAmount + tipAmount;

            tipTextView.setText(formatter.format(tipAmount));
            totalTextView.setText(formatter.format(total));
        } else {
            makeToast("Empty subtotal");
        }





    }

    private void makeToast(String text) {
        Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        toast.show();
    }

}
