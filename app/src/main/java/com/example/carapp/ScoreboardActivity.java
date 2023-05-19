package com.example.carapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.carapp.Fragments.MapFragment;
import com.example.carapp.Fragments.ScoreListFragment;

public class ScoreboardActivity extends AppCompatActivity {

    private Fragment scoreListFragment;
    private Fragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        initFragments();
        beginTransactions();
    }

    private void beginTransactions() {
        getSupportFragmentManager().beginTransaction().add(R.id.main_FRAME_list, scoreListFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.main_FRAME_map,mapFragment).commit();
    }

    private void initFragments() {
        scoreListFragment=new ScoreListFragment();
        mapFragment=new MapFragment();
    }
}