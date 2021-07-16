package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private Button tempButton;
    private Button disAndMeasButton;
    private Button weightButton;
    private Button rateButton;
    private Button shoeSizeButton;
    private Button bakingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tempButton = findViewById(R.id.tempButton);
        disAndMeasButton = findViewById(R.id.distAndMeasButton);
        weightButton = findViewById(R.id.weightButton);
        rateButton = findViewById(R.id.rateButton);
        shoeSizeButton = findViewById(R.id.shoeButton);
        bakingButton = findViewById(R.id.bakingButton);
        tempButton.setOnClickListener(this);
        disAndMeasButton.setOnClickListener(this);
        weightButton.setOnClickListener(this);
        rateButton.setOnClickListener(this);
        shoeSizeButton.setOnClickListener(this);
        bakingButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tempButton:
                openActivityTemp();
                break;
            case R.id.distAndMeasButton:
                openActivityDis();
                break;
            case R.id.weightButton:
                openActivityWeight();
                break;
            case R.id.rateButton:
                openActivityRate();
                break;
            case R.id.shoeButton:
                openActivityShoe();
                break;
            case R.id.bakingButton:
                openActivityBake();
                break;
            default:
        }
    }

    public void openActivityTemp(){
    Intent intent = new Intent(this, TempActivity.class);
    startActivity(intent);
    }
    public void openActivityDis(){
        Intent intent1 = new Intent(this, DnmActivity.class);
        startActivity(intent1);
    }
    public void openActivityWeight(){
        Intent intent2 = new Intent(this, WeightActivity.class);
        startActivity(intent2);
    }
    public void openActivityRate(){
        Intent intent3 = new Intent(this, RateActivity.class);
        startActivity(intent3);
    }
    public void openActivityShoe(){
        Intent intent4 = new Intent(this, ShoeSizeActivity.class);
        startActivity(intent4);
    }
    public void openActivityBake(){
        Intent intent5 = new Intent(this, BakingActivity.class);
        startActivity(intent5);
    }
}
