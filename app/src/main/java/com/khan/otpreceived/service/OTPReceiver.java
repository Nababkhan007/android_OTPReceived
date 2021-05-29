package com.khan.otpreceived.service;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.EditText;

public class OTPReceiver extends BroadcastReceiver {
    @SuppressLint("StaticFieldLeak")
    private static EditText editText;

    public void setOtp(EditText editText) {
        OTPReceiver.editText = editText;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            SmsMessage[] smsMessage = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            for (SmsMessage sms : smsMessage) {
                String message = sms.getMessageBody();
                editText.setText(message);
            }
        }
    }
}
