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

import java.text.DecimalFormat;

public class DnmActivity extends AppCompatActivity implements View.OnClickListener,Defenitions {
    private int firstSelected = KILOMETER;
    private int secondSelected = KILOMETER;
    private double finalValue;
    private double finalResult;
    private EditText et;
    private TextView result;
    private Spinner fromSpinner;
    private Spinner toSpinner;
    private Button convertButton;

    String[] options = {"Kilometer", "Mile", "Meter", "Feet", "Cm", "Inches", "Millimeter"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dnm);

        fromSpinner = findViewById(R.id.spinner3);
        toSpinner = findViewById(R.id.spinner4);
        et = findViewById(R.id.editText2);
        result = findViewById(R.id.textView5);
        convertButton = findViewById(R.id.button2);

        //setting the array adapter for both spinners
        convertButton.setOnClickListener(this);
        ArrayAdapter optionsArray = new ArrayAdapter(this, android.R.layout.simple_spinner_item,options);
        optionsArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(optionsArray);
        toSpinner.setAdapter(optionsArray);

        //setting a listener for both spinners, I want to save the first selected and the second
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){ firstSelected=KILOMETER;}
                else if(position==1){firstSelected=MILE;}
                else if(position==2){firstSelected=METER;}
                else if(position==3){firstSelected=FEET;}
                else if(position==4){firstSelected=CM;}
                else if(position==5){firstSelected=INCHES;}
                else{firstSelected=MM;}

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){ secondSelected=KILOMETER;}
                else if(position==1){secondSelected=MILE;}
                else if(position==2){secondSelected=METER;}
                else if(position==3){secondSelected=FEET;}
                else if(position==4){secondSelected=CM;}
                else if(position==5){secondSelected=INCHES;}
                else{secondSelected=MM;}

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
        if((et.getText().toString()).equals("") || firstSelected==secondSelected || finalValue==0){
            Toast.makeText(getApplicationContext(), "CANNOT CONVERT", Toast.LENGTH_LONG).show();
        }
        else{

            if(firstSelected == KILOMETER){
                if(secondSelected == MILE){
                    finalResult = finalValue * 0.62137;
                }
                else if(secondSelected == METER){
                    finalResult = finalValue/ 0.0010000;
                }
                else if(secondSelected == FEET){
                    finalResult = finalValue * 3280.8;
                }
                else if(secondSelected == CM){
                    finalResult = finalValue / 0.000010000;
                }
                else if(secondSelected == INCHES){
                    finalResult = finalValue * 39370;
                }
                else{
                    finalResult = finalValue / 0.0000010000;
                }

            }
            else if(firstSelected == MILE ){
                if(secondSelected == KILOMETER){
                    finalResult = finalValue / 0.62137;
                }
                else if(secondSelected == METER){
                    finalResult = finalValue / 0.00062137;
                }
                else if(secondSelected == FEET){
                    finalResult = finalValue * 5280.0;
                }
                else if(secondSelected == CM){
                    finalResult = finalValue * 160934.4;
                }
                else if(secondSelected == INCHES){
                    finalResult = finalValue * 63360;
                }
                else{
                    finalResult = finalValue * 1609344;
                }
            }
            else if( firstSelected == METER ){
                if(secondSelected == KILOMETER){
                    finalResult = finalValue * 0.0010000;
                }
                else if(secondSelected == MILE){
                    finalResult = finalValue * 0.00062137;
                }
                else if(secondSelected == FEET){
                    finalResult = finalValue *  3.2808;
                }
                else if(secondSelected == CM){
                    finalResult = finalValue / 0.010000;
                }
                else if(secondSelected == INCHES){
                    finalResult = finalValue * 39.370;
                }
                else{
                    finalResult = finalValue / 0.0010000;
                }
            }
            else if( firstSelected == FEET ){
                if(secondSelected == MILE){
                    finalResult = finalValue / 5280.0;
                }
                else if(secondSelected == METER){
                    finalResult = finalValue /  3.2808;
                }
                else if(secondSelected == KILOMETER){
                    finalResult = finalValue / 3280.8;
                }
                else if(secondSelected == CM){
                    finalResult = finalValue /  0.032808;
                }
                else if(secondSelected == INCHES){
                    finalResult = finalValue * 12;
                }
                else{
                    finalResult = finalValue / 0.0032808;
                }
            }
            else if( firstSelected == CM){
                if(secondSelected == MILE){
                    finalResult = finalValue / 160934.4;
                }
                else if(secondSelected == METER){
                    finalResult = finalValue * 0.010000;
                }
                else if(secondSelected == FEET){
                    finalResult = finalValue *  0.032808;
                }
                else if(secondSelected == KILOMETER){
                    finalResult = finalValue * 0.000010000;
                }
                else if(secondSelected == INCHES){
                    finalResult = finalValue * 0.39370;
                }
                else{
                    finalResult = finalValue / 0.10000;
                }
            }
            else if( firstSelected == INCHES ){
                if(secondSelected == MILE){
                    finalResult = finalValue / 63360;
                }
                else if(secondSelected == METER){
                    finalResult = finalValue / 39.370;
                }
                else if(secondSelected == FEET){
                    finalResult = finalValue / 12;
                }
                else if(secondSelected == CM){
                    finalResult = finalValue * 2.54;
                }
                else if(secondSelected == KILOMETER){
                    finalResult = finalValue / 39370;
                }
                else{
                    finalResult = finalValue / 0.039370;
                }
            }
            else if( firstSelected == MM ){
                if(secondSelected == MILE){
                    finalResult = finalValue / 1609344;
                }
                else if(secondSelected == METER){
                    finalResult = finalValue * 0.0010000;
                }
                else if(secondSelected == FEET){
                    finalResult = finalValue * 0.0032808;
                }
                else if(secondSelected == CM){
                    finalResult = finalValue * 0.10000;
                }
                else if(secondSelected == INCHES){
                    finalResult = finalValue * 0.039370;
                }
                else{
                    finalResult = finalValue * 0.0000010000;
                }
            }
            printResult();
            closeKeyboard();
        }

    }
    public void printResult(){

        result.setText(new DecimalFormat("##.##").format(finalResult));
    }
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
