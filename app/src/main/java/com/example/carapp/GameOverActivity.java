package com.example.carapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.carapp.Logic.Score;
import com.example.carapp.Logic.ScoresList;
import com.example.carapp.Utilities.DataManager;
import com.example.carapp.Utilities.GPS;
import com.example.carapp.Utilities.MySp;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.util.ArrayList;

public class GameOverActivity extends AppCompatActivity {

    final static String SCORE_KEY = "SCORE_KEY";
    private static int userScore;

    private MaterialButton saveNameMB;

    private String name;

    private TextView scoreTV;

    private GPS gps;

    private MaterialButton mainMenuMB;
    private MediaPlayer mediaPlayerCrash;
    private EditText nameED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        mediaPlayerCrash=MediaPlayer.create(this,R.raw.crash_sound);
        mediaPlayerCrash.start();
        Intent prevIntent= getIntent();
        userScore=prevIntent.getIntExtra(SCORE_KEY,0);
        gps=new GPS(this);
        findViews();
        scoreTV.setText("Score:"+userScore);
        setOnClickListeners();


    }


    public void setOnClickListeners(){
        saveNameMB.setOnClickListener(view -> {
            saveNameMB.setVisibility(View.INVISIBLE);
            saveScore();

        });
        mainMenuMB.setOnClickListener(view->{
            backToMenu();
        });


    }

    private void saveScore() {
        gps.sampleLocation(this);
        name=nameED.getText().toString();
        Score score= new Score().setUserName(name).setUserScore(userScore).setLag(gps.getLag()).setLat(gps.getLat());
        //Score score= new Score().setUserName(name).setUserScore(userScore).setLag(31).setLat(31);

        DataManager.getInstance().addSorted(score);
        DataManager.getInstance().saveJson();

    }


    private void backToMenu() {
        Intent menuIntent = new Intent(this,MenuActivity.class);
        startActivity(menuIntent);
        finish();
    }

    private void findViews() {
        mainMenuMB=findViewById(R.id.score_BTN_mainmenu);
        saveNameMB=findViewById(R.id.score_BTN_save);
        scoreTV=findViewById(R.id.score_LBL_score);
        nameED=findViewById(R.id.score_ETXT_name);

    }
}