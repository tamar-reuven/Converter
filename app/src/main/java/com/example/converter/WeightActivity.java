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

public class WeightActivity extends AppCompatActivity implements View.OnClickListener, Defenitions {
    private int firstSelected = GRAM;
    private int secondSelected = GRAM;
    private double finalValue;
    private double finalResult;
    private EditText et;
    private TextView result;
    private Spinner fromSpinner;
    private Spinner toSpinner;
    private Button convertButton;

    String[] options = {"Grams", "Kilograms", "Milligrams", "Ounces", "Pounds", "Tons"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        fromSpinner = findViewById(R.id.spinner);
        toSpinner = findViewById(R.id.spinner2);
        et = findViewById(R.id.editTextValue);
        result = findViewById(R.id.textViewResult);
        convertButton = findViewById(R.id.buttonConvert);


        //setting the adapter for both spinners
        convertButton.setOnClickListener(this);
        ArrayAdapter optionsArray = new ArrayAdapter(this, android.R.layout.simple_spinner_item,options);
        optionsArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(optionsArray);
        toSpinner.setAdapter(optionsArray);

        //setting the array adapter for both spinners
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){ firstSelected=GRAM;}
                else if(position==1){firstSelected=KILOGRAM;}
                else if(position==2){firstSelected=MILLIGRAM;}
                else if(position==3){firstSelected=OUNCES;}
                else if(position==4){firstSelected=POUNDS;}
                else{firstSelected=TON;}

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){ secondSelected=GRAM;}
                else if(position==1){secondSelected=KILOGRAM;}
                else if(position==2){secondSelected=MILLIGRAM;}
                else if(position==3){secondSelected=OUNCES;}
                else if(position==4){secondSelected=POUNDS;}
                else{secondSelected=TON;}

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
        } if((et.getText().toString()).equals("") || finalValue==0){
            Toast.makeText(getApplicationContext(), "ENTER VALUE", Toast.LENGTH_LONG).show(); //toast in case the user did not enter a value
        }
        else{

            if((firstSelected==GRAM && secondSelected==KILOGRAM) || (firstSelected==KILOGRAM && secondSelected == TON) || (firstSelected==MILLIGRAM && secondSelected == GRAM)){
            finalResult = finalValue / 1000;
            }
            else if((firstSelected==GRAM && secondSelected == MILLIGRAM) || ( firstSelected==KILOGRAM && secondSelected==GRAM) || (firstSelected==TON && secondSelected == KILOGRAM)){
            finalResult = finalValue * 1000;
            }
            else if(firstSelected==GRAM && secondSelected == OUNCES){
            finalResult = finalValue / 28.34952;
            }
            else if(firstSelected==GRAM && secondSelected == POUNDS){
            finalResult = finalValue / 453.59237;
            }
            else if((firstSelected==GRAM && secondSelected == TON) || (firstSelected==MILLIGRAM && secondSelected==KILOGRAM)){
            finalResult = finalValue /  1000000;
            }
            else if(firstSelected==KILOGRAM && secondSelected == MILLIGRAM){
            finalResult = finalValue * 1000000;
            }
            else if(firstSelected==KILOGRAM && secondSelected ==OUNCES ){
            finalResult = finalValue / 0.02834952;
            }
            else if(firstSelected==KILOGRAM && secondSelected == POUNDS){
            finalResult = finalValue / 0.45359237;
            }
            else if(firstSelected==MILLIGRAM && secondSelected == OUNCES){
            finalResult =finalValue * 0.000035274;
            }
            else if(firstSelected==MILLIGRAM && secondSelected == POUNDS ){
            finalResult =finalValue * 0.0000022046;
            }
            else if(firstSelected==MILLIGRAM && secondSelected == TON){
            finalResult = finalValue / 1000000000;
            }
            else if(firstSelected==OUNCES && secondSelected == GRAM){
            finalResult =finalValue * 28.34952;
            }
            else if(firstSelected==OUNCES && secondSelected == KILOGRAM){
            finalResult =finalValue * 0.02834952;
            }
            else if(firstSelected==OUNCES && secondSelected==MILLIGRAM){
            finalResult = finalValue / 0.000035274;
            }
            else if(firstSelected==OUNCES && secondSelected == POUNDS){
            finalResult = finalValue / 16;
            }
            else if(firstSelected== OUNCES && secondSelected == TON ){
            finalResult = finalValue / 35274;
            }
            else if(firstSelected==POUNDS && secondSelected == GRAM){
            finalResult = finalValue * 453.59237;
            }
            else if(firstSelected==POUNDS && secondSelected == KILOGRAM){
            finalResult = finalValue *  0.45359237;
            }
            else if(firstSelected==POUNDS && secondSelected == MILLIGRAM){
            finalResult =finalValue / 0.0000022046;
            }
            else if(firstSelected==POUNDS && secondSelected == OUNCES){
            finalResult = finalValue * 16;
            }
            else if(firstSelected==POUNDS && secondSelected == TON){
            finalResult = finalValue * 0.00045359237;
            }
            else if(firstSelected==TON && secondSelected == GRAM){
            finalResult =finalValue / 0.0000010000;
            }
            else if(firstSelected==TON && secondSelected == MILLIGRAM){
            finalResult =finalValue / 0.0000000010000;
            }
            else if(firstSelected==TON && secondSelected == OUNCES){
            finalResult = finalValue * 35274;
            }
            else if(firstSelected==TON && secondSelected == POUNDS){
            finalResult =finalValue / 0.00045359237;
            }
            else{
            Toast.makeText(getApplicationContext(), "CANNOT CONVERT", Toast.LENGTH_LONG).show();
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
    public void printResult(){
        result.setText(new DecimalFormat("##.##").format(finalResult));
    }
}
