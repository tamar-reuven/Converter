package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TempActivity extends AppCompatActivity implements View.OnClickListener,Defenitions {
    private int firstSelected = CELSIUS;
    private int secondSelected = CELSIUS;
    private double finalValue;
    private double finalResult;
    private EditText et;
    private TextView result;
    private Spinner fromSpinnerT;
    private Spinner toSpinnerT;
    private Button convertButton;
    String[] options = {"Celsius", "Fahrenheit", "Kelvin", "Rankine"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        fromSpinnerT = findViewById(R.id.fromSpinnerT);
        toSpinnerT = findViewById(R.id.toSpinnerT);
        convertButton = findViewById(R.id.convertB);
        et = findViewById(R.id.valueT);
        result = findViewById(R.id.resultT);


        //setting the array adapter for both spinners
        convertButton.setOnClickListener(this);
        ArrayAdapter optionsArray = new ArrayAdapter(this, android.R.layout.simple_spinner_item,options);
        optionsArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinnerT.setAdapter(optionsArray);
        toSpinnerT.setAdapter(optionsArray);

        //setting a listener for both spinners, I want to save the first selected and the second
        fromSpinnerT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){ firstSelected=CELSIUS;}
                else if(position==1){firstSelected=FAHRENHEIT;}
                else if(position==2){firstSelected=KELVIN;}
                else{firstSelected=RANKINE;}

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        toSpinnerT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){secondSelected=CELSIUS;}
                else if(position==1){ secondSelected=FAHRENHEIT; }
                else if(position==2){secondSelected=KELVIN;}
                else{secondSelected=RANKINE;}

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    @Override
    public void onClick(View v) {
        //on click method that calculates the conversion with formulas
             try{
                 finalValue = Double.parseDouble(et.getText().toString());
             }catch(NumberFormatException e){
                 Toast.makeText(getApplicationContext(), "CANNOT CONVERT", Toast.LENGTH_LONG).show();
             }
            if((et.getText().toString()).equals("") || firstSelected == secondSelected ||finalValue==0 ){
                Toast.makeText(getApplicationContext(), "ENTER VALUE", Toast.LENGTH_LONG).show();
            }else{

                if (firstSelected == CELSIUS ) {
                    if (secondSelected == FAHRENHEIT) {
                        finalResult = finalValue * 9 / 5 + 32;
                    } else if (secondSelected == KELVIN) {
                        finalResult = finalValue + 273.15;
                    } else {
                        finalResult = (finalValue + 273.15) * 9 / 5;
                    }
                }
                else if (firstSelected == FAHRENHEIT ) {
                    if (secondSelected == CELSIUS) {
                        finalResult = (finalValue - 32) * 5 / 9;
                    } else if (secondSelected == KELVIN) {
                        finalResult = (finalValue + 459.67) * 5 / 9;
                    } else {
                        finalResult = finalValue + 459.67;
                    }
                }
                else if (firstSelected == KELVIN) {
                    if (secondSelected == CELSIUS) {
                        finalResult = finalValue - 273.15;
                    } else if (secondSelected == FAHRENHEIT) {
                        finalResult = finalValue * 9 / 5 - 459.67;
                    } else {
                        finalResult = finalValue * 9 / 5;
                    }
                }
                else{
                    if (secondSelected == CELSIUS) {
                        finalResult = (finalValue - 491.67) * 5 / 9;
                    } else if(secondSelected==FAHRENHEIT){
                        finalResult = finalValue - 459.67;
                    } else {
                        finalResult = finalValue * 5/9;
                    }
                }
                printResult();
                closeKeyboard();
            }
    }



    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    //print the rounded result
    public void printResult(){
        double roundedResult = Math.round(finalResult);
        result.setText(String.valueOf(roundedResult));

    }




}
