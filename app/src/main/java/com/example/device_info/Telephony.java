package com.example.device_info;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Telephony extends AppCompatActivity {
    private static final String TODO = "HELLO";
    int PHONESTATE_REQUEST_CODE = 101;
    int LOCATION_REQUEST_CODE = 102;
    TextView callstate, celllocation, softwareversion, networkcomapny, networkoperator,
            networkname, phonetype, simcountry, simoperator, simname, simstate;

    String callStateString;
    String deviceSWVersion,cellLocationString,nwCompanyISO,nwOperator,nwOperatorName,phoneTypeString,simCountryISO,
            simOperator,simOperatorName,simStateString;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telephony);
        callstate = findViewById(R.id.txtcallstate);
        celllocation = findViewById(R.id.txtcelllocation);
        softwareversion = findViewById(R.id.txtsoftwareversion);
        networkcomapny = findViewById(R.id.txtnetworkcompany);
        networkoperator = findViewById(R.id.txtnetworkoperator);
        networkname = findViewById(R.id.txtnetworkoperatorname);
        phonetype = findViewById(R.id.txtphonetype);
        simcountry = findViewById(R.id.txtsimcountry);
        simoperator = findViewById(R.id.txtsimoperator);
        simname = findViewById(R.id.txtsimoperatorname);
        simstate = findViewById(R.id.txtsimstate);


        checkPermission(android.Manifest.permission.READ_PHONE_STATE, PHONESTATE_REQUEST_CODE);
        //function to check amd request phone state permission
        checkPermission(android.Manifest.permission.ACCESS_FINE_LOCATION, LOCATION_REQUEST_CODE);
        //function to check amd request fine location permission
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        try {
            callStateString = "NA";
            int callState = telephonyManager.getCallState();

            switch (callState) {
                case TelephonyManager.CALL_STATE_IDLE:    //it means there is no activity or any recent call
                    callStateString = "IDLE";
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:  //call is going on but it maybe on hold or waiting condition
                    callStateString = "OFFHOOK";
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    callStateString = "RINGING";
                    break;
            }

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            GsmCellLocation location = (GsmCellLocation) telephonyManager.getCellLocation();
            cellLocationString = location.getLac() + " " + location.getCid();   //getLac method returns the area based on your current location
            //getCid method returns cell id

            deviceSWVersion = telephonyManager.getDeviceSoftwareVersion();
            nwCompanyISO = telephonyManager.getNetworkCountryIso();           //returns ISO code for country
            nwOperator = telephonyManager.getNetworkOperator();
            nwOperatorName = telephonyManager.getNetworkOperatorName();
            phoneTypeString = "NA";

            int phoneType = telephonyManager.getPhoneType();
            switch (phoneType) {
                case TelephonyManager.PHONE_TYPE_GSM:
                    phoneTypeString = "GSM";
                    break;
                case TelephonyManager.PHONE_TYPE_CDMA:
                    phoneTypeString = "CDMA";
                    break;
                case TelephonyManager.PHONE_TYPE_SIP:
                    phoneTypeString = "SIP";
                    break;
                case TelephonyManager.PHONE_TYPE_NONE:
                    phoneTypeString = "NONE";
                    break;
            }

            simCountryISO = telephonyManager.getSimCountryIso();
            simOperator = telephonyManager.getSimOperator();
            simOperatorName = telephonyManager.getSimOperatorName();
            simStateString = "NA";

            int simState = telephonyManager.getSimState();
            switch (simState) {
                case TelephonyManager.SIM_STATE_ABSENT:
                    simStateString = "ABSENT";
                    break;
                case TelephonyManager.SIM_STATE_NOT_READY:
                    simStateString = "NOT READY";
                    break;
                case TelephonyManager.SIM_STATE_READY:
                    simStateString = "READY";
                    break;
                case TelephonyManager.SIM_STATE_CARD_IO_ERROR:
                    simStateString = "CARD IO ERROR";
                    break;
                case TelephonyManager.SIM_STATE_CARD_RESTRICTED:
                    simStateString = "CARD RESTRICTED";
                    break;
                case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                    simStateString = "NETWORK LOCKED";
                    break;
                case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                    simStateString = "PIN REQUIRED";
                    break;
                case TelephonyManager.SIM_STATE_PERM_DISABLED:
                    simStateString = "PERM DISABLED";
                    break;
                case TelephonyManager.SIM_STATE_UNKNOWN:
                    simStateString = "UNKNOWN";
                    break;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        callstate.setText(callStateString);
        celllocation.setText(cellLocationString);
        softwareversion.setText(deviceSWVersion);
        networkcomapny.setText(nwCompanyISO);
        networkoperator.setText(nwOperator);
        networkname.setText(nwOperatorName);
        phonetype.setText(phoneTypeString);
        simcountry.setText(simCountryISO);
        simoperator.setText(simOperator);
        simname.setText(simOperatorName);
        simstate.setText(simStateString);
    }


    //function to check and request permission
    public void checkPermission(String permission, int requestcode) {
        //checking if permission is not granted
        if (ContextCompat.checkSelfPermission(Telephony.this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(Telephony.this, new String[]{permission}, requestcode);    //calls onRequestPermissionsResult method
        } else {
            Toast.makeText(Telephony.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRequestPermissionsResult(int requestcode, String[] permission, int[] grantResults) {

        super.onRequestPermissionsResult(requestcode, permission, grantResults);

        if (requestcode == PHONESTATE_REQUEST_CODE) {
            //checking whether user granted the permission or not
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(Telephony.this, "Phone State Permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Telephony.this, "Phone State Permission denied", Toast.LENGTH_LONG).show();
            }
        }

        if (requestcode == LOCATION_REQUEST_CODE) {
            //checking whether user granted the permission or not
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(Telephony.this, "Location Permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Telephony.this, "Location Permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
}
