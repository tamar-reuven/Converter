package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BakingActivity extends AppCompatActivity implements View.OnClickListener, Defenitions, SeekBar.OnSeekBarChangeListener{
    private Spinner cupSizeSpinner;
    private SeekBar amount;
    private TextView result;
    private TextView amountNeeded, numerator, denominator, number, fractionLine;
    private Button convertButton;
    private String cupSize[] = {"180", "240"};
    private double sizeChosen;
    private double progressValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baking);

        cupSizeSpinner = findViewById(R.id.spinner9);
        amount = findViewById(R.id.seekBar);
        result = findViewById(R.id.textView10);
        amountNeeded = findViewById(R.id.textView7);
        convertButton = findViewById(R.id.button4);
        numerator = findViewById(R.id.textView13);
        denominator = findViewById(R.id.textView14);
        number = findViewById(R.id.textView16);
        fractionLine = findViewById(R.id.textView17);


        //setting adapter and listener
        convertButton.setOnClickListener(this);
        amount.setOnSeekBarChangeListener(this);

        ArrayAdapter optionsArray = new ArrayAdapter(this, android.R.layout.simple_spinner_item,cupSize);
        optionsArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cupSizeSpinner.setAdapter(optionsArray);
        //setting listener to whats been chosen on spinner
        cupSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){ sizeChosen = SMALL_CUP;}
                else{ sizeChosen = LARGE_CUP;}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    //on click that takes the cup size and the progress value and send it to change to fraction method
    public void onClick(View v) {
        if(progressValue != 0){
            double size;
            number.setText("");
            if(sizeChosen==SMALL_CUP){
                size = SMALL_CUP;
                 changeToFraction(size);
            }
            else{
                 size = LARGE_CUP;
                 changeToFraction(size);
            }
        } else{
            Toast.makeText(getApplicationContext(), "CHOOSE THE SIZE", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    //this method shows the progress in decimal number
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        amountNeeded.setText(""+progress);
        progressValue = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
    //calculate the numerator and denominator and prints them
    public void changeToFraction(double size){
        double temp = progressValue;
        if(temp>size){
         int wholeNum = (int) (temp / size);
         temp = temp - size;
         number.setText(String.valueOf(wholeNum));
        }
        for(double i=temp; i>1; i--){
            if((temp%i==0) && (size%i==0))
            {
                temp = temp / i;
                size =  size / i;
            }
        }
        numerator.setText(String.valueOf((int)temp));
        denominator.setText(String.valueOf((int)size));
        closeKeyboard();

    }
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
