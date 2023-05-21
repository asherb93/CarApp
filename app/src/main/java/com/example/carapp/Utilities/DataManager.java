package com.example.carapp.Utilities;

import static com.example.carapp.Utilities.MySp.SP_FILE_NAME;

import android.util.Log;

import com.example.carapp.Logic.Score;
import com.example.carapp.Logic.ScoresList;
import com.google.gson.Gson;

import java.util.ArrayList;

public class DataManager {

    private final int  NUM_OF_TOP_SCORES=10;

    private  ScoresList scoresList;

    private static DataManager instance=null;




    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
            instance.loadJson();
        }
        return instance;
    }


    public DataManager( ) {
        scoresList = new ScoresList();
    }


    public int getNUM_OF_TOP_SCORES() {
        return NUM_OF_TOP_SCORES;
    }



    public void addSorted(Score score) {
        scoresList.getScoreList().add(score);
        scoresList.getScoreList().sort((pl1, pl2) -> pl2.getUserScore() - pl1.getUserScore());
        if(scoresList.getScoreList().size()>NUM_OF_TOP_SCORES)
        {
            trimScoreList();
        }
    }

    private void trimScoreList() {
        int i=NUM_OF_TOP_SCORES;
        while(scoresList.getScoreList().size()>NUM_OF_TOP_SCORES)
        {
            scoresList.getScoreList().remove(i);
        }

    }

    public ArrayList<Score> getScoresList() {
        return scoresList.getScoreList();
    }



    public void saveJson() {
        String scoreListTemp = new Gson().toJson(scoresList);

        Log.d("saveJson",scoreListTemp);

        //my sp becomes null
        MySp.getInstance().putString(SP_FILE_NAME, scoreListTemp);
    }

        public void loadJson() {
        ScoresList l = new ScoresList();
        String fromJsonTemp = MySp.getInstance().getPrefs().getString(SP_FILE_NAME, "");
        Log.d("loadJson",fromJsonTemp);
        Log.d("fromjsontemp",fromJsonTemp.isEmpty()+"");
        if (!fromJsonTemp.isEmpty()) {
            Gson gson = new Gson();
            l = gson.fromJson(fromJsonTemp, ScoresList.class);
        }
        scoresList=l;
    }

    public boolean isJsonFileEmpty() {
        String json = MySp.getInstance().getPrefs().getString(SP_FILE_NAME, "");
        return json.isEmpty();
    }



}
