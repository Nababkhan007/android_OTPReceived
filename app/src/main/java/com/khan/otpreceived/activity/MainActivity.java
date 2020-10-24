package com.khan.otpreceived.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.EditText;

import com.khan.otpreceived.R;
import com.khan.otpreceived.service.OTPReceiver;

public class MainActivity extends AppCompatActivity {
    private static final int SMS_REQUEST_CODE = 1;
    private EditText otpEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        requestSmsPermission();

        setOtp();
    }

    private void requestSmsPermission() {
        String permission = Manifest.permission.RECEIVE_SMS;
        int grant = ContextCompat.checkSelfPermission(this, permission);

        if (grant != PackageManager.PERMISSION_GRANTED) {
            String[] permissionList = new String[1];
            permissionList[0] = permission;
            ActivityCompat.requestPermissions(this, permissionList, SMS_REQUEST_CODE);
        }
    }

    private void setOtp() {
        new OTPReceiver().setOtp(otpEt);
    }

    private void init() {
        otpEt = findViewById(R.id.otpEt);
    }
}