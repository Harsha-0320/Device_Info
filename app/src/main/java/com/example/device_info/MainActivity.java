package com.example.device_info;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton devicebtn,systembtn,batterybtn,sensorbtn,telephonybtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        devicebtn=findViewById(R.id.devicebtn);
        systembtn=findViewById(R.id.systembtn);
        //thermalbtn=findViewById(R.id.thermalbtn);
        batterybtn=findViewById(R.id.batterybtn);
        sensorbtn=findViewById(R.id.sensorbtn);
        telephonybtn=findViewById(R.id.telephonybtn);

        //Different modules to get different infromation
        devicebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Device.class);
                startActivity(intent);
            }
        });

        systembtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), System.class);
                startActivity(i);
            }
        });

        batterybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(getApplicationContext(), Battery.class);
                startActivity(ii);
            }
        });

        sensorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent is=new Intent(getApplicationContext(), Sensor.class);
                startActivity(is);
            }
        });

        telephonybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tel=new Intent(getApplicationContext(), Telephony.class);
                startActivity(tel);
            }
        });
    }
}