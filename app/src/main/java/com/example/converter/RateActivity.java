package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

public class RateActivity extends AppCompatActivity implements View.OnClickListener  {
    private double finalValue;
    private double finalResult;
    private EditText et;
    private TextView result;
    private TextView currentRate;
    private Spinner fromSpinner;
    private Spinner toSpinner;
    private Button convertButton;
    private String baseCurrency= "USD";
    private String convertToCurrency="GBP";
    private String conversionRate;
    private AutoCompleteTextView fromSearch;
    private AutoCompleteTextView toSearch;


    String[] options = {"EUR", "USD", "ILS", "CZK", "GBP", "HKD", "CHF", "JPY", "RUB" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        fromSpinner = findViewById(R.id.spinner5);
        toSpinner = findViewById(R.id.spinner6);
        et = findViewById(R.id.editText);
        convertButton = findViewById(R.id.button);
        result = findViewById(R.id.textView3);
        fromSearch = findViewById(R.id.autoCompleteTextView);
        toSearch = findViewById(R.id.autoCompleteTextView2);
        currentRate = findViewById(R.id.currentRateView);

        //setting an adapter for both spinners
        convertButton.setOnClickListener(this);
        ArrayAdapter optionsArray = new ArrayAdapter(this, android.R.layout.simple_spinner_item,options);
        optionsArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(optionsArray);
        toSpinner.setAdapter(optionsArray);
        fromSearch.setAdapter(optionsArray);
        toSearch.setAdapter(optionsArray);

        //setting listener in order to update the base currency and the convert to currency
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){ baseCurrency = "EUR";}
                else if(position==1){baseCurrency = "USD";}
                else if(position==2){baseCurrency = "ILS";}
                else if(position==3){baseCurrency = "CZK";}
                else if(position==4){baseCurrency = "GBP";}
                else if(position==5){baseCurrency = "HKD";}
                else if(position==6){baseCurrency = "CHF";}
                else if(position==7){baseCurrency = "JPY";}
                else{baseCurrency = "RUB";}

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){ convertToCurrency = "EUR";}
                else if(position==1){convertToCurrency = "USD";}
                else if(position==2){convertToCurrency = "ILS";}
                else if(position==3){convertToCurrency = "CZK";}
                else if(position==4){convertToCurrency = "GBP";}
                else if(position==5){convertToCurrency = "HKD";}
                else if(position==6){convertToCurrency = "CHF";}
                else if(position==7){convertToCurrency = "JPY";}
                else{convertToCurrency = "RUB";}

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void onClick(View v){
        if(baseCurrency.equals(convertToCurrency)){
            Toast.makeText(getApplicationContext(), "CANNOT CONVERT", Toast.LENGTH_LONG).show();
        }
        else if((et.getText().toString()).equals("")){
            Toast.makeText(getApplicationContext(), "ENTER VALUE", Toast.LENGTH_LONG).show();
        }else{
            try{
                finalValue = Double.parseDouble(et.getText().toString());
            }catch(NumberFormatException e){
                Toast.makeText(getApplicationContext(), "CANNOT CONVERT", Toast.LENGTH_LONG).show();
            }
            new apiCall().execute(); //doing it in AsyncTask because it's a heavy operation
        }
    }

    private class apiCall extends AsyncTask<URL, Integer, String> {

        @Override
        //this method goes to the link and creates JSON object with all the info I asked
        protected String doInBackground(URL... urls) {
           String stringValue;
           try {
                URL url = new URL(" https://api.ratesapi.io/api/latest?base=" + baseCurrency + "&symbols=" + convertToCurrency);
                stringValue = getJson(url);
                JSONObject jsonObject =  new JSONObject(stringValue);
                conversionRate =jsonObject.getJSONObject("rates").getString(convertToCurrency);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }


            return conversionRate;
        }
        //this method takes all the info from the url and builds a string out of it
        public String getJson (URL url) throws IOException, JSONException {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            StringBuilder stringBuilder = new StringBuilder();
            int currentChar = 0;
            do{
                currentChar = inputStreamReader.read();
                stringBuilder.append((char) currentChar);
            }while(currentChar != -1);
            return stringBuilder.toString();
        }
        //this method takes the final result and set it on the screen
        protected void onPostExecute(String s){
           finalResult= finalValue * Double.parseDouble(conversionRate);
            result.setText(new DecimalFormat("##.###").format(finalResult));
           currentRate.setText("Current Rate: 1 " + baseCurrency+ "= " +conversionRate+" "+ convertToCurrency);
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
}



 /* try {
                    URL url = new URL("http://data.fixer.io/api/latest?access_key=596b6c74c43fb1172194d00b74434549");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    StringBuilder stringBuilder = new StringBuilder();
                    int currentChar = 0;
                    do{
                        currentChar = inputStreamReader.read();
                        stringBuilder.append((char) currentChar);
                    }while(currentChar != -1);

                    Log.i("API_GET", stringBuilder.toString());
                    //return(new JSONObject(stringBuilder.toString()));
                } catch (IOException e) {
                    e.printStackTrace();
                }


    try {
            URL url = new URL(" https://api.ratesapi.io/api/latest?base="+baseCurrency+"&symbols="+convertToCurrency);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            StringBuilder stringBuilder = new StringBuilder();
            int currentChar = 0;
            do{
            currentChar = inputStreamReader.read();
            stringBuilder.append((char) currentChar);
            }while(currentChar != -1);
            //connection.disconnect();
            finalValue = Double.parseDouble(String.valueOf(et.getText()));
            result.setText(stringBuilder.toString());
            // JSONObject jsonObject =  new JSONObject(stringBuilder.toString());
            //conversionRate =jsonObject.getJSONObject("rates").getString("GBP");
            //result.setText( String.valueOf(Float.parseFloat(et.getText().toString()) * Float.parseFloat(conversionRate)));

            } catch (IOException e) {
            e.printStackTrace();
            }*/
