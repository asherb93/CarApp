package com.example.carapp.Utilities;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

import static com.example.carapp.Utilities.MySp.SP_FILE_NAME;

import android.content.Context;
import android.os.Vibrator;

import com.example.carapp.Logic.Score;
import com.example.carapp.Logic.ScoresList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;

public class DataManager {

    private final int  NUM_OF_TOP_SCORES=10;

    private  ScoresList scoresList;

    private static DataManager instance=null;

    public DataManager( ) {
        scoresList = new ScoresList();
    }



    public static DataManager getInstance() {
        return instance;
    }

    public static void init() {
        if (instance == null) {
            instance = new DataManager();
        }
    }

    public int getNUM_OF_TOP_SCORES() {
        return NUM_OF_TOP_SCORES;
    }



    public void addSorted(Score score) {
        scoresList.getScoreList().add(score);
        scoresList.getScoreList().sort((pl1, pl2) -> pl2.getUserScore() - pl1.getUserScore());
    }

    public ScoresList getScoresList() {
        return scoresList;
    }

    public void saveJson()
    {
        String scoreListTemp = new Gson().toJson(scoresList);
        //my sp becomes null
        MySp.getInstance().putString(SP_FILE_NAME,scoreListTemp);
    }

    public void loadJson()
    {
       ScoresList l=new ScoresList();
        String fromJsonTemp=MySp.getInstance().getPrefs().getString(SP_FILE_NAME,"");
        if(!fromJsonTemp.isEmpty()){
            Gson gson=new Gson();
            l=new Gson().fromJson(fromJsonTemp,ScoresList.class);
        }
        scoresList=new Gson().fromJson(fromJsonTemp,ScoresList.class);
    }
}
