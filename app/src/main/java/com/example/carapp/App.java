package com.example.carapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.example.carapp.Utilities.DataManager;
import com.example.carapp.Utilities.MySp;
import com.example.carapp.Utilities.SignalGenerator;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MySp.init(this);
        SignalGenerator.init(this);
        DataManager.init();

    }
}