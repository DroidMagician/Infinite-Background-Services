package com.example.myapplication.AlarmManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

public class MyRebootReceiver extends BroadcastReceiver {
    String TAG = getClass().getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Broadcast "+intent.getAction(), Toast.LENGTH_SHORT).show();
        //  Log.i(TAG, "*** onReceive:  Network change ");

        Log.i(TAG, "*** ACTION REBOOT"+"Action is:"+intent.getAction());

        PollReceiver.scheduleAlarms(context);
        // m_ScreenOffReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_USER_PRESENT);
////        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        context.registerReceiver(Utils.getReciverInstance(), intentFilter);

        // do something, e.g. send Intent to main app
    }
}
