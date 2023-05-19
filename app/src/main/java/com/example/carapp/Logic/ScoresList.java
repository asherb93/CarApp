package com.example.carapp.Logic;

import java.util.ArrayList;

public class ScoresList {

    private ArrayList<Score> scoreList;

    public ScoresList() {
        scoreList = new ArrayList<Score>();
    }

    public  void addScore(Score score) {
        scoreList.add(score);
    }

    public ArrayList<Score> getScoreList() {
        return scoreList;
    }

    public void setScoreList(ArrayList<Score> scoreList) {
        this.scoreList = scoreList;
    }

    @Override
    public String toString() {
        return "ScoresList{" +
                "scoreList=" + scoreList +
                '}';
    }
}
