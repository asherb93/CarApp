package com.example.carapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.carapp.Fragments.MapFragment;
import com.example.carapp.Fragments.ScoreListFragment;
import com.example.carapp.Logic.Score;
import com.example.carapp.Utilities.DataManager;
import com.example.carapp.interfaces.MapCallback;
import com.example.carapp.interfaces.ScoreCallback;
import com.google.android.material.button.MaterialButton;

public class ScoreboardActivity extends AppCompatActivity {

    private ScoreListFragment scoreListFragment;
    private MapFragment mapFragment;

    private MapCallback mapCallback=new MapCallback() {
        @Override
        public void getScoreCoordinates(double lat, double lag) {
            mapFragment.showLocation(lat,lag);

        }
    };

    private MaterialButton returnToMenuMB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        initFragments();
        beginTransactions();
        findViews();
        setOnClickListeners();

    }

    private void findViews() {
        returnToMenuMB=findViewById(R.id.backmenu_BTN);
    }

    private void setOnClickListeners() {
        returnToMenuMB.setOnClickListener(view->{
            Intent newIntent=new Intent(this, MenuActivity.class);
            startActivity(newIntent);
            finish();

        });
    }

    private void beginTransactions() {
        getSupportFragmentManager().beginTransaction().add(R.id.main_FRAME_list, scoreListFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.main_FRAME_map,mapFragment).commit();
    }

    private void initFragments() {
        scoreListFragment=new ScoreListFragment();
        scoreListFragment.setMapCallback(mapCallback);
        mapFragment=new MapFragment();
    }

    public void setMapCallback(MapCallback mapCallback) {
        this.mapCallback = mapCallback;
    }



}