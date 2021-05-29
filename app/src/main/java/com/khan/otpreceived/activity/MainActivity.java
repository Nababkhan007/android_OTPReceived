package com.khan.otpreceived.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.khan.otpreceived.R;
import com.khan.otpreceived.service.OTPReceiver;

public class MainActivity extends AppCompatActivity {
    private static final int SMS_REQUEST_CODE = 99;
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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS},
                    SMS_REQUEST_CODE);
        } else {
            setOtp();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            setOtp();
        }
    }

    private void setOtp() {
        new OTPReceiver().setOtp(otpEt);
    }

    private void init() {
        otpEt = findViewById(R.id.otpEt);
    }
}