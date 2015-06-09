package com.philiply.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


public class MainActivity extends Activity {

    NumberPicker splitPicker;
    EditText subtotalInput;
    TextView tipTextView;
    TextView totalTextView;
    TextView amountPerPersonTextView;
    NumberFormat nf = NumberFormat.getCurrencyInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subtotalInput = (EditText) findViewById(R.id.subtotal_input);
        splitPicker = (NumberPicker) findViewById(R.id.split_picker);
        tipTextView = (TextView) findViewById(R.id.tip_amount_value);
        totalTextView = (TextView) findViewById(R.id.final_total);
        amountPerPersonTextView = (TextView) findViewById(R.id.amount_per_person);

        splitPicker.setMinValue(1);
        splitPicker.setMaxValue(20);
        splitPicker.setWrapSelectorWheel(false);

        splitPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                float total = getTotalFloat();
                amountPerPersonTextView.setText(nf.format(total/i2));
            }
        });

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
        switch (id) {
            //case R.id.action_settings:
            //    return true;
            case R.id.action_reset:
                resetValues();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void calculateTip(View v) {
        float tipPerc = Float.parseFloat(v.getTag().toString());

        if (!subtotalInput.getText().toString().equals("")) {
            float subtotalAmount = Float.parseFloat(subtotalInput.getText().toString());

            float tipAmount = subtotalAmount * tipPerc;

            float total = subtotalAmount + tipAmount;

            tipTextView.setText(nf.format(tipAmount));
            totalTextView.setText(nf.format(total));
            updateSplitAmount();
        } else {
            makeToast("Empty subtotal");
        }

    }

    private void updateSplitAmount() {
        float total = getTotalFloat();
        amountPerPersonTextView.setText(nf.format(total/splitPicker.getValue()));
    }

    private float getTotalFloat() {
        return Float.parseFloat(totalTextView.getText().toString().substring(1).replace(",",""));
    }

    private void makeToast(String text) {
        Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        toast.show();
    }

    private void resetValues() {
        subtotalInput.getText().clear();
        tipTextView.setText(R.string.default_zero_value);
        totalTextView.setText(R.string.default_zero_value);
        amountPerPersonTextView.setText(R.string.default_zero_value);
        splitPicker.setValue(1);
    }

}
