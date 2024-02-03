package com.example.device_info;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class System extends AppCompatActivity {

    Intent i;
    TextView version,api,id,date,display,hardware,soc_mo,soc_manu,bootloader;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system);
        i=getIntent();
        long installTime = getAppInstallTime(this);

        version=findViewById(R.id.version);
        api=findViewById(R.id.apitxt);
        id=findViewById(R.id.idtxt);
        date=findViewById(R.id.timetxt);
        display=findViewById(R.id.displaytxt);
        hardware=findViewById(R.id.hardware);
        soc_mo=findViewById(R.id.modeltxt);
        soc_manu=findViewById(R.id.soc_manu);
        bootloader=findViewById(R.id.bootloadertxt);

        String v= Build.VERSION.RELEASE;
        version.setText(v);

        int ap=Build.VERSION.SDK_INT;
        api.setText(String.valueOf(ap));

        String idd=Build.ID;
        id.setText(idd);

        long datee=Build.TIME;
        date.setText(formatDate(installTime));
        //long installTime = getAppInstallTime(this);

        // Display the installation date
        //TextView dateTextView = findViewById(R.id.dateTextView);
        //dateTextView.setText("Installation Date: " + );
        String dis=Build.DISPLAY;
        display.setText(dis);

        String hard=Build.HARDWARE;
        hardware.setText(hard);

        String model= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            model = Build.SOC_MODEL;
        }
        soc_mo.setText(model);

        String manu=Build.MANUFACTURER;
        soc_manu.setText(manu);

        String boot=Build.BOOTLOADER;
        bootloader.setText(boot);


    }

    private long getAppInstallTime(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.firstInstallTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private String formatDate(long timeInMillis) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(timeInMillis));
    }


    }

