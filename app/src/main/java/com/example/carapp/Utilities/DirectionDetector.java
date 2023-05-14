package com.example.carapp.Utilities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.example.carapp.interfaces.sensorCallback;


public class DirectionDetector  {

    private Sensor sensor;
    private SensorManager sensorManager;
    private sensorCallback sensorCallback;

    private long timestamp = 0;


    private SensorEventListener sensorEventListener;

    public DirectionDetector(Context context, sensorCallback stepCallback) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.sensorCallback = stepCallback;
        initEventListener();
    }

    private void initEventListener() {

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float x=sensorEvent.values[0];
                calculateDirection(x);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };


    }

    private void calculateDirection(float x) {
        if (System.currentTimeMillis() - timestamp > 500) {
            timestamp = System.currentTimeMillis();
            if (x > 2.0) {
                Log.d("timestampX",timestamp+"");
                if (sensorCallback != null)
                    sensorCallback.moveLeft();
            }
            if (x < -2.0) {
                Log.d("timestampX",timestamp+"");
                if (sensorCallback != null)
                    sensorCallback.moveRight();
            }

        }

    }

    public void start() {
        sensorManager.registerListener(
                sensorEventListener,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    public void stop() {
        sensorManager.unregisterListener(
                sensorEventListener,
                sensor
        );
    }



}
