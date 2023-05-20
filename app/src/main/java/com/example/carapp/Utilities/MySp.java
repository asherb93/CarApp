package com.example.carapp.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class MySp {

    public static final String SP_FILE_NAME = "MY_SP_FILE";
    private SharedPreferences prefs;

    public static final String SP_KEY_SCORE="SP_KEY_SCORE";
    private static MySp instance = null;


    private MySp(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
    }
    public static void init(Context context) {
        if (instance == null) {
            instance = new MySp(context);
        }
    }

    public SharedPreferences getPrefs() {
        return prefs;
    }

    public static MySp getInstance() {
        return instance;
    }

    public void putIntToSP(String key, int value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getIntFromSP(String key, int def) {
        return prefs.getInt(key, def);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }



    public String getString(String key, String def) {
        return prefs.getString(key, def);
    }





}
