package com.example.carapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class GameOverActivity extends AppCompatActivity {

    final static String SCORE_KEY = "SCORE_KEY";

    TextView scoreTV;
    MaterialButton mainMenuMB;
    MediaPlayer mediaPlayerCrash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        mediaPlayerCrash=MediaPlayer.create(this,R.raw.crash_sound);
        mediaPlayerCrash.start();
        Intent prevIntent= getIntent();
        int Score=prevIntent.getIntExtra(SCORE_KEY,0);
        findViews();
        scoreTV.setText("Score: "+Score);
        onClickListeners();

    }

    private void onClickListeners() {
        mainMenuMB.setOnClickListener(view -> backToMenu());
    }

    private void backToMenu() {
        Intent menuIntent = new Intent(this,MenuActivity.class);
        startActivity(menuIntent);
        finish();
    }

    private void findViews() {
        mainMenuMB=findViewById(R.id.score_BTN_mainmenu);
        scoreTV=findViewById(R.id.score_LBL_score);
    }
}