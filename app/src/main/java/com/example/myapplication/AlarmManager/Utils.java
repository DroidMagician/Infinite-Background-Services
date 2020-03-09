package com.example.myapplication.AlarmManager;

import android.content.Intent;
import android.content.IntentFilter;

import com.example.myapplication.MyReceiver;

public class Utils {
    public static MyReceiver myReceiver;
    public static MyRebootReceiver myReceiverReboot;
    public static MyReceiver getReciverInstance()
    {
        if(myReceiver == null)
        {
            myReceiver = new MyReceiver();
        }
        return myReceiver;
    }
    public static MyRebootReceiver getRebootReciverInstance()
    {
        if(myReceiverReboot == null)
        {
            myReceiverReboot = new MyRebootReceiver();
        }
        return myReceiverReboot;
    }
}
