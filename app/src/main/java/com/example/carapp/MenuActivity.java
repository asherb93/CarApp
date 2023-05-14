package com.example.carapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class MenuActivity extends AppCompatActivity {


    MaterialButton slowMB;
    MaterialButton fastMB;
    MaterialButton sensorMB;

    private final int slowSpeed=1000;

    private final int fastSpeed=500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViews();
        setOnClickListener();
    }

    public void findViews()
    {
        slowMB=findViewById(R.id.btn_slow_Buttons);
        fastMB=findViewById(R.id.btn_fast_Buttons);
        sensorMB=findViewById(R.id.sensor_mode_BTN);

    }

    public void setOnClickListener()
    {
        slowMB.setOnClickListener(view -> startGame(slowSpeed,"Buttons"));
        fastMB.setOnClickListener(view -> startGame(fastSpeed,"Buttons"));
        sensorMB.setOnClickListener(view -> startGame(slowSpeed,"NoButtons"));

    }

    public void startGame(int delay,String sensor){
        Intent gameIntent=new Intent(this, MainActivity.class);
        gameIntent.putExtra(MainActivity.GAME_SPEED,delay);
        gameIntent.putExtra(MainActivity.GAME_MODE,sensor);
        startActivity(gameIntent);
        finish();
    }




}