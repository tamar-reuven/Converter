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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class ShoeSizeActivity extends AppCompatActivity implements View.OnClickListener,Defenitions {
    private int firstSelected = US;
    private int secondSelected = US;
    private double finalValue;
    private double finalResult;
    private EditText et;
    private TextView result;
    private Spinner fromSpinner;
    private Spinner toSpinner;
    private Button convertButton;
    private RadioGroup gender;
    private RadioButton female;
    private RadioButton male;
    String[] options = {"US", "UK", "EUR"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe_size);
        fromSpinner = findViewById(R.id.spinner7);
        toSpinner = findViewById(R.id.spinner8);
        et = findViewById(R.id.editText4);
        result = findViewById(R.id.textView6);
        convertButton = findViewById(R.id.button3);
        gender = findViewById(R.id.gender);
        female= findViewById(R.id.femaleButton);
        male = findViewById(R.id.maleButton);

        convertButton.setOnClickListener(this);
        ArrayAdapter optionsArray = new ArrayAdapter(this, android.R.layout.simple_spinner_item,options);
        optionsArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(optionsArray);
        toSpinner.setAdapter(optionsArray);



        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){ firstSelected=US;}
                else if(position==1){firstSelected=UK;}
                else{firstSelected=EUR;}

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){ secondSelected=US;}
                else if(position==1){secondSelected=UK;}
                else{secondSelected=EUR;}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        int radioId = gender.getCheckedRadioButtonId();
        try{
            finalValue = Double.parseDouble(et.getText().toString());
        }catch(NumberFormatException e){
            Toast.makeText(getApplicationContext(), "CANNOT CONVERT", Toast.LENGTH_LONG).show();
        }
        if((et.getText().toString()).equals("") || firstSelected == secondSelected ||finalValue==0 ){
            Toast.makeText(getApplicationContext(), "ENTER VALUE", Toast.LENGTH_LONG).show();
        }else{
            double temp = finalValue;
            if(radioId == female.getId()){ //female

                if(finalValue==4 || finalValue==4.5 && firstSelected == US && secondSelected==EUR){
                    finalResult=35;
                }
                else if(firstSelected==US && secondSelected==EUR){
                    if((temp *10)%10==0){finalResult = finalValue+31;}
                    else{finalResult = finalValue+31.5;}
                }
                else if(firstSelected==US && secondSelected==UK){
                    finalResult = finalValue - 2;
                }
                else if(firstSelected==UK && secondSelected==US){
                    finalResult = finalValue + 2;
                }
                else if(firstSelected==UK && secondSelected==EUR){
                    finalResult = finalValue + 32.5;
                }
                else if(firstSelected==EUR && secondSelected==US){
                    finalResult = finalValue - 30.5;
                }
                else{
                    finalResult = finalValue - 32.5;
                }
            }
            else{ //male
                if(firstSelected==US && secondSelected==EUR){
                    if((temp *10)%10==0){finalResult = finalValue+33;}
                    else{finalResult = finalValue+32.5;}
                }
                else if(firstSelected==US && secondSelected==UK){
                    finalResult = finalValue - 1;
                }
                else if(firstSelected==UK && secondSelected==US){
                    finalResult = finalValue + 1;
                }
                else if(firstSelected==UK && secondSelected==EUR){
                    temp=0;
                    if((temp *10)%10==0){finalResult = finalValue+33.5;}
                    else{finalResult = finalValue+33;}
                }
                else if(firstSelected==EUR && secondSelected==US){
                    finalResult = finalValue - 33;
                }
                else{
                    finalResult = finalValue - 33.5;
                }
            }
            result.setText(String.valueOf(finalResult));
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

     /*if(firstSelected == secondSelected || (et.getText().toString()).equals("") || Integer.valueOf(et.getText().toString())==0){
        Toast.makeText(getApplicationContext(), "CANNOT CONVERT", Toast.LENGTH_LONG).show();
    }
        else{
        try{
            finalValue = Double.parseDouble(et.getText().toString());
        }catch(NumberFormatException e){
            Toast.makeText(getApplicationContext(), "CANNOT CONVERT", Toast.LENGTH_LONG).show();
        }
        if(radioId == female.getId()){ //female

            if(finalValue==4 || finalValue==4.5 && firstSelected == US && secondSelected==EUR){
                finalResult=35;
            }
            else if(firstSelected==US && secondSelected==EUR){
                if((temp *10)%10==0){finalResult = finalValue+31;}
                else{finalResult = finalValue+31.5;}
            }
            else if(firstSelected==US && secondSelected==UK){
                finalResult = finalValue - 2;
            }
            else if(firstSelected==UK && secondSelected==US){
                finalResult = finalValue + 2;
            }
            else if(firstSelected==UK && secondSelected==EUR){
                finalResult = finalValue + 32.5;
            }
            else if(firstSelected==EUR && secondSelected==US){
                finalResult = finalValue - 30.5;
            }
            else{
                finalResult = finalValue - 32.5;
            }
        }
        else{ //male
            if(firstSelected==US && secondSelected==EUR){
                if((temp *10)%10==0){finalResult = finalValue+33;}
                else{finalResult = finalValue+32.5;}
            }
            else if(firstSelected==US && secondSelected==UK){
                finalResult = finalValue - 1;
            }
            else if(firstSelected==UK && secondSelected==US){
                finalResult = finalValue + 1;
            }
            else if(firstSelected==UK && secondSelected==EUR){
                temp=0;
                if((temp *10)%10==0){finalResult = finalValue+33.5;}
                else{finalResult = finalValue+33;}
            }
            else if(firstSelected==EUR && secondSelected==US){
                finalResult = finalValue - 33;
            }
            else{
                finalResult = finalValue - 33.5;
            }
        }
        closeKeyboard();
        result.setText(String.valueOf(finalResult));
    }*/
}
