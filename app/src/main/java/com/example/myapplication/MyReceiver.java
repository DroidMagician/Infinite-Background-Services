package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    String TAG = getClass().getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Broadcast "+intent.getAction(), Toast.LENGTH_SHORT).show();
        //  Log.i(TAG, "*** onReceive:  Network change ");

        Log.i(TAG, "*** ACTION_SCREEN_OFF"+"Action is:"+intent.getAction());
        // do something, e.g. send Intent to main app
    }
}
