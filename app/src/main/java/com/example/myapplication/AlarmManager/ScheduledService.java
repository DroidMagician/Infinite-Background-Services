package com.example.myapplication.AlarmManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import androidx.core.app.JobIntentService;

import com.example.myapplication.MyReceiver;

public class ScheduledService extends JobIntentService {
  private static final int UNIQUE_JOB_ID=1337;
  public static Context mcontext;
  static void enqueueWork(Context ctxt) {
    enqueueWork(ctxt, ScheduledService.class, UNIQUE_JOB_ID,

      new Intent(ctxt, ScheduledService.class));
    mcontext = ctxt;
  }

  @Override
  public void onHandleWork(Intent i) {

    try {
      if (Utils.getReciverInstance() != null) {
        getApplicationContext().unregisterReceiver(Utils.getReciverInstance());
      }
    } catch (IllegalArgumentException e) {
      // do nothing
    }

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_USER_PRESENT);
////        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
 //   BroadcastReceiver m_ScreenOffReceiver = new MyReceiver();
    getApplicationContext().registerReceiver(Utils.getReciverInstance(), intentFilter);
    Log.i(getClass().getSimpleName(), "*** I ran!");
  }

}
