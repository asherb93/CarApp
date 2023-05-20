package com.example.carapp.Logic;

import com.example.carapp.Utilities.SignalGenerator;

import java.util.ArrayList;

public class ScoresList {

    private ArrayList<Score> scoreList;

    public ScoresList() {
            scoreList = new ArrayList<>();
    }


    public ArrayList<Score> getScoreList() {
            return scoreList;
    }


    @Override
    public String toString() {
        return "ScoresList{" +
                "scoreList=" + scoreList +
                '}';
    }
}
