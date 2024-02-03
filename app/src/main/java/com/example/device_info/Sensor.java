package com.example.device_info;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.net.Proxy;

public class Sensor extends AppCompatActivity {

    TextView txtTemprature,txtmagnetic,txtPressure,txthumidity,txtlight,txtproximity,
            txtmotiondetect,txtrotation,txtaccelerometer,txtgyroscope,txtgravity;

    private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
            float temperatureInCelsius = temperature / 10.0f;

            txtTemprature.setText("" + temperatureInCelsius + " °C");
        }
    };


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor);

        txtTemprature=findViewById(R.id.txtambienttemp);
        txtmagnetic=findViewById(R.id.txtmagnetic);
        txtPressure=findViewById(R.id.txtpressure);
        txthumidity=findViewById(R.id.txthumidity);
        txtlight=findViewById(R.id.txtlight);
        txtproximity=findViewById(R.id.txtproximity);
        txtaccelerometer=findViewById(R.id.txtaccelerometer);
        txtmotiondetect=findViewById(R.id.txtmotion);
        txtrotation=findViewById(R.id.txtrotation);
        txtgyroscope=findViewById(R.id.txtgyroscpe);
        txtgravity=findViewById(R.id.txtgravity);

        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryInfoReceiver, filter);

        // Delayed execution of battery temperature retrieval for better accuracy
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Request an update of battery information
                Intent batteryStatus = registerReceiver(null, filter);
                if (batteryStatus != null) {
                    batteryInfoReceiver.onReceive(Sensor.this, batteryStatus);
                }
            }
        }, 1000);

        SensorManager sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);

        android.hardware.Sensor sensorHumidity=sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_RELATIVE_HUMIDITY);
        android.hardware.Sensor sensorlight=sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_LIGHT);
        //android.hardware.Sensor sensortemp=sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_AMBIENT_TEMPERATURE);
        android.hardware.Sensor sensormagnetic=sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED);
        android.hardware.Sensor sensorproximity=sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_PROXIMITY);
        android.hardware.Sensor sensorpressure=sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_PRESSURE);
        android.hardware.Sensor sensoraccelerometer=sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_ACCELEROMETER);
        android.hardware.Sensor sensorgravity=sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_GRAVITY);
        android.hardware.Sensor sensorgyroscope=sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_GYROSCOPE_UNCALIBRATED);
        android.hardware.Sensor sensormotiondetect=sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_MOTION_DETECT);
        android.hardware.Sensor sensorrotation=sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_ROTATION_VECTOR);

        SensorEventListener sensorEventListeneraccelerometer=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                txtaccelerometer.setText("Accelerometer : " + "\n" + event.values[0] + "\n" + event.values[1] +"\n" + event.values[2]);
            }

            @Override
            public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {

            }
        };

        SensorEventListener sensorEventListenergravity=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                txtgravity.setText("" + event.values[0]);
            }

            @Override
            public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {

            }
        };

        SensorEventListener sensorEventListenergyroscope=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                txtgyroscope.setText("" + event.values[0]);
            }

            @Override
            public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {

            }
        };

        SensorEventListener sensorEventListenerrotation=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                txtrotation.setText("" + event.values[0]);
            }

            @Override
            public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {

            }
        };

        SensorEventListener sensorEventListenermotion=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                txtmotiondetect.setText("" + event.values[0]);
            }

            @Override
            public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {

            }
        };

        SensorEventListener sensorEventListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                txthumidity.setText("" + event.values[0]);
            }

            @Override
            public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {

            }
        };

        SensorEventListener sensorEventListenerlight=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                txtlight.setText("" + event.values[0]);
            }

            @Override
            public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {

            }
        };

        /*SensorEventListener sensorEventListenertemp=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                txtTemprature.setText("Ambient Temperature : " + event.values[0]);
            }

            @Override
            public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {

            }
        };*/

        SensorEventListener sensorEventListenermagnetic=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                txtmagnetic.setText("" + event.values[0]);
            }

            @Override
            public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {

            }
        };

        SensorEventListener sensorEventListenerproximity=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                txtproximity.setText("" + event.values[0]);
            }

            @Override
            public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {

            }
        };

        SensorEventListener sensorEventListenerpressure=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                txtPressure.setText("" + event.values[0]);
            }

            @Override
            public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {

            }
        };

        sensorManager.registerListener(sensorEventListener,sensorHumidity,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorEventListenerlight,sensorlight,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorEventListenermagnetic,sensormagnetic,SensorManager.SENSOR_DELAY_NORMAL);
        //sensorManager.registerListener(sensorEventListenertemp,sensortemp,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorEventListenerpressure,sensorpressure,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorEventListenerproximity,sensorproximity,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorEventListeneraccelerometer,sensoraccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorEventListenergyroscope,sensorgyroscope,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorEventListenergravity,sensorgravity,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorEventListenermotion,sensormotiondetect,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorEventListenerrotation,sensorrotation,SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onDestroy() {
        super.onDestroy();
        // Unregister the BroadcastReceiver when the activity is destroyed
        unregisterReceiver(batteryInfoReceiver);
    }
}
