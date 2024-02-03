package com.example.device_info;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Battery extends AppCompatActivity {
    BroadcastReceiver batteryBroadcast;
    IntentFilter intentFilter;
    Intent ii;
    TextView level,health,voltage,batteryType,chargingSource,temperature,chargingStatus;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battery);
        ii=getIntent();

        //getSupportActionBar().getDisplayOptions();
        //getSupportActionBar().setCustomView(R.layout.activity_main);

        //getSupportActionBar().setTitle("Battery Demo");

        level=findViewById(R.id.txtlevel);
        health=findViewById(R.id.txthealth);
        voltage=findViewById(R.id.txtvoltage);
        batteryType=findViewById(R.id.txttype);
        chargingSource=findViewById(R.id.txtsource);
        temperature=findViewById(R.id.txttemp);
        chargingStatus=findViewById(R.id.txtstatus);
        //title=findViewById(R.id.txtTitle);

        intentFilterAndBroadcast();
    }

    private void intentFilterAndBroadcast() {
        intentFilter=new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        batteryBroadcast=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())){
                    level.setText(String.valueOf(intent.getIntExtra("level",0))+"%");

                    float voltTemp=(float) (intent.getIntExtra("voltage",0)*0.001);
                    voltage.setText(voltTemp+"v");

                    setHealth(intent);

                    batteryType.setText(intent.getStringExtra("technology"));

                    setChargingSource(intent);

                    float temp=(float) intent.getIntExtra("temperature",-1)/10;
                    temperature.setText(temp+"°");

                    setChargingStatus(intent);
                }
            }
        };
    }

    private void setChargingStatus(Intent intent) {
        int status=intent.getIntExtra("status",-1);
        switch (status){
            case BatteryManager.BATTERY_STATUS_CHARGING:
                chargingStatus.setText("Charging");
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                chargingStatus.setText("Discharging");
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                chargingStatus.setText("Full");
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                chargingStatus.setText("Not charging");
                break;
            case BatteryManager.BATTERY_STATUS_UNKNOWN:
                chargingStatus.setText("Unknown");
                break;
            default:
                chargingStatus.setText("Null");
        }
    }

    private void setChargingSource(Intent intent) {
        int source=intent.getIntExtra("plugged",-1);
        switch (source){
            case BatteryManager.BATTERY_PLUGGED_AC:
                chargingSource.setText("AC");
                break;
            case BatteryManager.BATTERY_PLUGGED_USB:
                chargingSource.setText("USB");
                break;
            case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                chargingSource.setText("Wireless");
                break;
            default:
                chargingSource.setText("Null");
        }
    }

    private void setHealth(Intent intent) {
        int val=intent.getIntExtra("health",0);
        switch (val){
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                health.setText("Unknown");
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                health.setText("Good");
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                health.setText("Overheat");
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                health.setText("Over voltage");
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                health.setText("Unspecified failure");
                break;
            case BatteryManager.BATTERY_HEALTH_DEAD:
                health.setText("Cold");
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(batteryBroadcast,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(batteryBroadcast);
    }
}
