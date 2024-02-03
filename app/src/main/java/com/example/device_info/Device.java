package com.example.device_info;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Device extends AppCompatActivity {
    Intent intent;

    TextView model, manu, version, resolution, board,user,brand,host,product,type,finger,density;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device);
        intent = getIntent();

        model = findViewById(R.id.model);
        manu = findViewById(R.id.manu);
        version = findViewById(R.id.version);
        resolution = findViewById(R.id.resolution);
        //serial=findViewById(R.id.serial);
        user=findViewById(R.id.user);
        host=findViewById(R.id.host);
        product=findViewById(R.id.prod);
        type=findViewById(R.id.ty);
        brand=findViewById(R.id.brand);
        board=findViewById(R.id.boardd);
        finger=findViewById(R.id.finger);
        density=findViewById(R.id.density);

        String modell=Build.MODEL;
        model.setText(modell);

        String manuu=Build.MANUFACTURER;
        manu.setText(manuu);

        /*String ver=Build.VERSION.RELEASE;
        version.setText(ver);*/

        String res= String.valueOf(getResources().getDisplayMetrics().widthPixels);
        String resh= String.valueOf(getResources().getDisplayMetrics().heightPixels);
        resolution.setText("width: " + res + " x " +"height: " + resh + " pixels");

        /*@SuppressLint("HardwareIds")
        String seriall=Build.SERIAL;
        serial.setText(seriall);*/
        //String bb=Build.

        String bd=Build.BOARD;
        board.setText(bd);

        String bran=Build.BRAND;
        brand.setText(bran);


        String hostt=Build.HOST;
        host.setText(hostt);

        String ab=Build.PRODUCT;
        product.setText(ab);

        String typee=Build.TYPE;
        type.setText(typee);

        String bc=Build.USER;
        user.setText(bc);

        String fin=Build.FINGERPRINT;
        finger.setText(fin);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int densityDpi = displayMetrics.densityDpi;

        // Display the screen density
        //TextView densityTextView = findViewById(R.id.densityTextView);
        String densityInfo = "Screen Density: " + getDensityString(densityDpi);
        density.setText(densityInfo);

    }

    private String getDensityString(int densityDpi) {
        String densityCategory;

        switch (densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                densityCategory = "LDPI";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                densityCategory = "MDPI";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                densityCategory = "HDPI";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                densityCategory = "XHDPI";
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                densityCategory = "XXHDPI";
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                densityCategory = "XXXHDPI";
                break;
            case DisplayMetrics.DENSITY_TV:
                densityCategory = "TVDPI";
                break;
            default:
                densityCategory = "Unknown";
        }

        return densityCategory + " (" + densityDpi + " dpi)";
    }
    }


