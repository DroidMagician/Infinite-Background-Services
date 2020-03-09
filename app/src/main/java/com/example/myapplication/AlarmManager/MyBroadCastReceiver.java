package com.example.myapplication.AlarmManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadCastReceiver extends BroadcastReceiver {

    String TAG = getClass().getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
//        if (intent.getAction() != null && intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
//
//            Intent serviceIntent = new Intent(context, MyService.class);
//            context.startService(serviceIntent);
//        } else {
//            Toast.makeText(context.getApplicationContext(), "Alarm Manager just ran", Toast.LENGTH_LONG).show();
//        }
        Log.i(TAG, "***** onReceive: ");
        Toast.makeText(context.getApplicationContext(), "Alarm Manager just ran", Toast.LENGTH_LONG).show();

    }
}